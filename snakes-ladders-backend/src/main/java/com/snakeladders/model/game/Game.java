package com.snakeladders.model.game;

import com.snakeladders.model.player.IPlayer;
import com.snakeladders.model.player.BotPlayer;
import com.snakeladders.model.strategy.start.IStartStrategy;
import com.snakeladders.model.strategy.win.IWinStrategy;
import com.snakeladders.model.strategy.move.IMoveRuleStrategy;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Game {
    private final String gameId;
    private final Board board;
    private final List<IPlayer> players;
    private final Dice dice;
    private final IStartStrategy startStrategy;
    private final IWinStrategy winStrategy;
    private final IMoveRuleStrategy moveRule;
    
    private GameState gameState;
    private int currentPlayerIndex;
    private IPlayer winner;
    private int turnCount;
    
    public Game(Board board, List<IPlayer> players, Dice dice, 
                IStartStrategy startStrategy, IWinStrategy winStrategy, 
                IMoveRuleStrategy moveRule) {
        this.gameId = UUID.randomUUID().toString();
        this.board = board;
        this.players = new ArrayList<>(players);
        this.dice = dice;
        this.startStrategy = startStrategy;
        this.winStrategy = winStrategy;
        this.moveRule = moveRule;
        this.gameState = GameState.WAITING_TO_START;
        this.currentPlayerIndex = 0;
        this.turnCount = 0;
    }
    
    public void startGame() {
        if (players.size() < 2) {
            throw new IllegalStateException("Game requires at least 2 players");
        }
        gameState = GameState.IN_PROGRESS;
    }
    
    public boolean playTurn() {
        if (gameState != GameState.IN_PROGRESS) {
            return false;
        }
        
        IPlayer currentPlayer = getCurrentPlayer();
        int diceRoll = dice.roll();
        turnCount++;
        
        if (currentPlayer.getPosition() == 0 && !startStrategy.canStart(diceRoll)) {
            nextPlayer();
            return false;
        }
        
        if (currentPlayer instanceof BotPlayer) {
            BotPlayer bot = (BotPlayer) currentPlayer;
            bot.analyzeAndMove(diceRoll, board, players);
        } else {
            int newPosition = moveRule.applyRules(currentPlayer, diceRoll, players);
            if (newPosition <= board.getTotalCells()) {
                currentPlayer.setPosition(board.getNewPosition(newPosition));
            }
        }
        
        if (winStrategy.hasWon(currentPlayer.getPosition(), board.getSize())) {
            winner = currentPlayer;
            gameState = GameState.FINISHED;
            return true;
        }
        
        if (diceRoll != 6) {
            nextPlayer();
        }
        
        return true;
    }
    
    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    public IPlayer getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    
    public String getGameId() {
        return gameId;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public List<IPlayer> getPlayers() {
        return new ArrayList<>(players);
    }
    
    public Dice getDice() {
        return dice;
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
    public IPlayer getWinner() {
        return winner;
    }
    
    public int getTurnCount() {
        return turnCount;
    }
    
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    
    public IStartStrategy getStartStrategy() {
        return startStrategy;
    }
    
    public IWinStrategy getWinStrategy() {
        return winStrategy;
    }
    
    public IMoveRuleStrategy getMoveRule() {
        return moveRule;
    }
}