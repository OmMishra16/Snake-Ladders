package com.snakeladders.model.player;

import com.snakeladders.model.game.Board;
import com.snakeladders.model.entity.BoardEntity;
import com.snakeladders.model.entity.EntityType;
import java.util.List;

public class DefensiveBotStrategy implements IBotStrategy {
    
    @Override
    public int decideMovePosition(int currentPosition, int diceRoll, Board board, List<IPlayer> otherPlayers) {
        int newPosition = currentPosition + diceRoll;
        
        BoardEntity entity = board.getEntityAt(newPosition);
        if (entity != null && entity.getEntityType() == EntityType.SNAKE) {
            if (Math.random() > 0.7) {
                return currentPosition;
            }
        }
        
        return newPosition;
    }
    
    @Override
    public boolean shouldRiskMove(int currentPosition, int diceRoll, Board board) {
        BoardEntity entity = board.getEntityAt(currentPosition + diceRoll);
        if (entity != null && entity.getEntityType() == EntityType.SNAKE) {
            return Math.random() > 0.8;
        }
        return true;
    }
    
    @Override
    public BotDifficulty getDifficulty() {
        return BotDifficulty.EXPERT;
    }
}