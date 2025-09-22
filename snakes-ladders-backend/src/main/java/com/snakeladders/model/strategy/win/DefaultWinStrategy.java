package com.snakeladders.model.strategy.win;

public class DefaultWinStrategy implements IWinStrategy {
    
    @Override
    public boolean hasWon(int position, int boardSize) {
        return position >= boardSize * boardSize;
    }
    
    @Override
    public String getDescription() {
        return "Win by reaching or crossing the last cell";
    }
}