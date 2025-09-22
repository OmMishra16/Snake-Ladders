package com.snakeladders.model.strategy.win;

public class ExactWinStrategy implements IWinStrategy {
    
    @Override
    public boolean hasWon(int position, int boardSize) {
        return position == boardSize * boardSize;
    }
    
    @Override
    public String getDescription() {
        return "Must land exactly on the last cell to win";
    }
}