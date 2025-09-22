package com.snakeladders.model.strategy.win;

public interface IWinStrategy {
    boolean hasWon(int position, int boardSize);
    String getDescription();
}