package com.snakeladders.model.player;

import com.snakeladders.model.game.Board;

public class HumanPlayer implements IPlayer {
    private final String name;
    private int position;
    
    public HumanPlayer(String name) {
        this.name = name;
        this.position = 0;
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
    
    @Override
    public PlayerType getPlayerType() {
        return PlayerType.HUMAN;
    }
    
    @Override
    public String toString() {
        return String.format("HumanPlayer{name='%s', position=%d}", name, position);
    }
}