package com.snakeladders.model.player;

import com.snakeladders.model.game.Board;
import java.util.List;

public class BotPlayer implements IPlayer {
    private final String name;
    private int position;
    private final IBotStrategy botStrategy;
    
    public BotPlayer(String name, IBotStrategy strategy) {
        this.name = name;
        this.position = 0;
        this.botStrategy = strategy;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getPosition() {
        return position;
    }
    
    @Override
    public void setPosition(int position) {
        this.position = position;
    }
    
    @Override
    public void makeMove(int diceRoll, Board board) {
        int newPosition = position + diceRoll;
        if (newPosition <= board.getSize() * board.getSize()) {
            setPosition(board.getNewPosition(newPosition));
        }
    }
    
    public void analyzeAndMove(int diceRoll, Board board, List<IPlayer> allPlayers) {
        if (botStrategy.shouldRiskMove(position, diceRoll, board)) {
            int newPosition = botStrategy.decideMovePosition(position, diceRoll, board, allPlayers);
            if (newPosition <= board.getSize() * board.getSize()) {
                setPosition(board.getNewPosition(newPosition));
            }
        }
    }
    
    @Override
    public PlayerType getPlayerType() {
        return PlayerType.BOT;
    }
    
    public IBotStrategy getBotStrategy() {
        return botStrategy;
    }
    
    public BotDifficulty getDifficulty() {
        return botStrategy.getDifficulty();
    }
    
    @Override
    public String toString() {
        return String.format("BotPlayer{name='%s', position=%d, difficulty=%s}", 
                name, position, getDifficulty());
    }
}