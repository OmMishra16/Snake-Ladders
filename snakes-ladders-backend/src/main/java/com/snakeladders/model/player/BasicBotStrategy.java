package com.snakeladders.model.player;

import com.snakeladders.model.game.Board;
import java.util.List;

public class BasicBotStrategy implements IBotStrategy {
    
    @Override
    public int decideMovePosition(int currentPosition, int diceRoll, Board board, List<IPlayer> otherPlayers) {
        return currentPosition + diceRoll;
    }
    
    @Override
    public boolean shouldRiskMove(int currentPosition, int diceRoll, Board board) {
        return true;
    }
    
    @Override
    public BotDifficulty getDifficulty() {
        return BotDifficulty.EASY;
    }
}