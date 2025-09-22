package com.snakeladders.service;

import com.snakeladders.model.game.Game;
import com.snakeladders.model.game.GameBuilder;
import com.snakeladders.model.player.BotPlayer;
import com.snakeladders.model.player.BotDifficulty;
import com.snakeladders.model.strategy.start.*;
import com.snakeladders.model.strategy.win.*;
import com.snakeladders.model.strategy.move.*;
import com.snakeladders.dto.*;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Service
public class GameService {
    
    private final Map<String, Game> games = new ConcurrentHashMap<>();
    
    public GameDto createGame(CreateGameRequest request) {
        GameBuilder builder = new GameBuilder()
                .setBoardSize(request.getBoardSize())
                .setStartStrategy(createStartStrategy(request.getStartStrategy()))
                .setWinStrategy(createWinStrategy(request.getWinStrategy()))
                .setMoveRule(createMoveRule(request.getMoveRule()));
        
        if (request.getHumanPlayers() != null) {
            for (String playerName : request.getHumanPlayers()) {
                builder.addHumanPlayer(playerName);
            }
        }
        
        if (request.getBotPlayers() != null) {
            for (CreateGameRequest.BotRequest botRequest : request.getBotPlayers()) {
                builder.addBot(botRequest.getName(), botRequest.getDifficulty());
            }
        }
        
        Game game = builder.build();
        games.put(game.getGameId(), game);
        
        return convertToDto(game);
    }
    
    public GameDto getGame(String gameId) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new RuntimeException("Game not found: " + gameId);
        }
        return convertToDto(game);
    }
    
    public GameDto startGame(String gameId) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new RuntimeException("Game not found: " + gameId);
        }
        
        game.startGame();
        return convertToDto(game);
    }
    
    public GameDto makeMove(String gameId) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new RuntimeException("Game not found: " + gameId);
        }
        
        game.playTurn();
        return convertToDto(game);
    }
    
    private IStartStrategy createStartStrategy(String strategy) {
        return switch (strategy.toUpperCase()) {
            case "SIX" -> new SixStartStrategy();
            default -> new DefaultStartStrategy();
        };
    }
    
    private IWinStrategy createWinStrategy(String strategy) {
        return switch (strategy.toUpperCase()) {
            case "EXACT" -> new ExactWinStrategy();
            default -> new DefaultWinStrategy();
        };
    }
    
    private IMoveRuleStrategy createMoveRule(String rule) {
        return switch (rule.toUpperCase()) {
            case "KICK" -> new KickPlayerRule();
            case "SHARE" -> new ShareCellRule();
            default -> new DefaultMoveRule();
        };
    }
    
    private GameDto convertToDto(Game game) {
        BoardDto boardDto = new BoardDto(
                game.getBoard().getSize(),
                convertSnakesToDto(game.getBoard().getSnakes()),
                convertLaddersToDto(game.getBoard().getLadders())
        );
        
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (var player : game.getPlayers()) {
            BotDifficulty botDifficulty = null;
            if (player instanceof BotPlayer) {
                botDifficulty = ((BotPlayer) player).getDifficulty();
            }
            playerDtos.add(new PlayerDto(
                    player.getName(),
                    player.getPosition(),
                    player.getPlayerType(),
                    botDifficulty
            ));
        }
        
        PlayerDto winnerDto = null;
        if (game.getWinner() != null) {
            var winner = game.getWinner();
            BotDifficulty winnerBotDifficulty = null;
            if (winner instanceof BotPlayer) {
                winnerBotDifficulty = ((BotPlayer) winner).getDifficulty();
            }
            winnerDto = new PlayerDto(
                    winner.getName(),
                    winner.getPosition(),
                    winner.getPlayerType(),
                    winnerBotDifficulty
            );
        }
        
        return new GameDto(
                game.getGameId(),
                game.getGameState(),
                boardDto,
                playerDtos,
                game.getCurrentPlayerIndex(),
                winnerDto,
                game.getTurnCount(),
                game.getStartStrategy().getDescription(),
                game.getWinStrategy().getDescription(),
                game.getMoveRule().getDescription()
        );
    }
    
    private List<EntityDto> convertSnakesToDto(List<com.snakeladders.model.entity.Snake> snakes) {
        List<EntityDto> dtos = new ArrayList<>();
        for (var snake : snakes) {
            dtos.add(new EntityDto(snake.getFrom(), snake.getTo(), snake.getEntityType()));
        }
        return dtos;
    }
    
    private List<EntityDto> convertLaddersToDto(List<com.snakeladders.model.entity.Ladder> ladders) {
        List<EntityDto> dtos = new ArrayList<>();
        for (var ladder : ladders) {
            dtos.add(new EntityDto(ladder.getFrom(), ladder.getTo(), ladder.getEntityType()));
        }
        return dtos;
    }
}