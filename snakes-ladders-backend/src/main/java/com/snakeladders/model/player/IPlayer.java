package com.snakeladders.model.player;

import com.snakeladders.model.game.Board;

public interface IPlayer {
    String getName();
    int getPosition();
    void setPosition(int position);
    void makeMove(int diceRoll, Board board);
    PlayerType getPlayerType();
    
    default void resetPosition() {
        setPosition(0);
    }
}