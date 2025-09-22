package com.snakeladders.model.game;

import com.snakeladders.model.player.IPlayer;
import com.snakeladders.model.player.HumanPlayer;
import com.snakeladders.model.player.BotPlayer;
import com.snakeladders.model.player.BotFactory;
import com.snakeladders.model.player.BotDifficulty;
import com.snakeladders.model.entity.Snake;
import com.snakeladders.model.entity.Ladder;
import com.snakeladders.model.strategy.start.IStartStrategy;
import com.snakeladders.model.strategy.start.DefaultStartStrategy;
import com.snakeladders.model.strategy.win.IWinStrategy;
import com.snakeladders.model.strategy.win.DefaultWinStrategy;
import com.snakeladders.model.strategy.move.IMoveRuleStrategy;
import com.snakeladders.model.strategy.move.DefaultMoveRule;
import java.util.List;
import java.util.ArrayList;

public class GameBuilder {
    private int boardSize = 10;
    private List<IPlayer> players = new ArrayList<>();
    private List<Snake> snakes = new ArrayList<>();
    private List<Ladder> ladders = new ArrayList<>();
    private Dice dice = new Dice();
    private IStartStrategy startStrategy = new DefaultStartStrategy();
    private IWinStrategy winStrategy = new DefaultWinStrategy();
    private IMoveRuleStrategy moveRule = new DefaultMoveRule();
    
    public GameBuilder setBoardSize(int size) {
        this.boardSize = size;
        return this;
    }
    
    public GameBuilder addPlayer(IPlayer player) {
        this.players.add(player);
        return this;
    }
    
    public GameBuilder addHumanPlayer(String name) {
        this.players.add(new HumanPlayer(name));
        return this;
    }
    
    public GameBuilder addBot(String name, BotDifficulty difficulty) {
        BotPlayer bot = BotFactory.createBot(name, difficulty);
        this.players.add(bot);
        return this;
    }
    
    public GameBuilder addRandomBots(int count) {
        for (int i = 0; i < count; i++) {
            BotPlayer bot = BotFactory.createRandomBot("Bot" + (i + 1));
            this.players.add(bot);
        }
        return this;
    }
    
    public GameBuilder addSnake(int from, int to) {
        this.snakes.add(new Snake(from, to));
        return this;
    }
    
    public GameBuilder addLadder(int from, int to) {
        this.ladders.add(new Ladder(from, to));
        return this;
    }
    
    public GameBuilder setDice(Dice dice) {
        this.dice = dice;
        return this;
    }
    
    public GameBuilder setStartStrategy(IStartStrategy startStrategy) {
        this.startStrategy = startStrategy;
        return this;
    }
    
    public GameBuilder setWinStrategy(IWinStrategy winStrategy) {
        this.winStrategy = winStrategy;
        return this;
    }
    
    public GameBuilder setMoveRule(IMoveRuleStrategy moveRule) {
        this.moveRule = moveRule;
        return this;
    }
    
    public Game build() {
        if (players.size() < 2) {
            throw new IllegalStateException("Game requires at least 2 players");
        }
        
        Board board;
        if (snakes.isEmpty() && ladders.isEmpty()) {
            board = new Board(boardSize);
        } else {
            board = new Board(boardSize, snakes, ladders);
        }
        
        return new Game(board, players, dice, startStrategy, winStrategy, moveRule);
    }
    
    public static GameBuilder createDefault() {
        return new GameBuilder()
                .addHumanPlayer("Player 1")
                .addBot("Bot 1", BotDifficulty.MEDIUM);
    }
    
    public static GameBuilder createQuickGame(int humanPlayers, int botPlayers) {
        GameBuilder builder = new GameBuilder();
        
        for (int i = 1; i <= humanPlayers; i++) {
            builder.addHumanPlayer("Player " + i);
        }
        
        for (int i = 1; i <= botPlayers; i++) {
            builder.addBot("Bot " + i, BotDifficulty.MEDIUM);
        }
        
        return builder;
    }
}