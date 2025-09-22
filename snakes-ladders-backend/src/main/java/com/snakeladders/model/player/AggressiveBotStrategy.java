package com.snakeladders.model.player;

import com.snakeladders.model.game.Board;
import com.snakeladders.model.entity.BoardEntity;
import com.snakeladders.model.entity.EntityType;
import java.util.List;
import java.util.ArrayList;

public class AggressiveBotStrategy implements IBotStrategy {
    
    @Override
    public int decideMovePosition(int currentPosition, int diceRoll, Board board, List<IPlayer> otherPlayers) {
        List<Integer> possibleMoves = generatePossibleMoves(currentPosition, diceRoll);
        
        for (int move : possibleMoves) {
            BoardEntity entity = board.getEntityAt(move);
            if (entity != null && entity.getEntityType() == EntityType.LADDER) {
                return move;
            }
        }
        
        return analyzeCompetitiveMove(currentPosition, diceRoll, otherPlayers, board);
    }
    
    private List<Integer> generatePossibleMoves(int currentPosition, int diceRoll) {
        List<Integer> moves = new ArrayList<>();
        moves.add(currentPosition + diceRoll);
        return moves;
    }
    
    private int analyzeCompetitiveMove(int currentPosition, int diceRoll, List<IPlayer> otherPlayers, Board board) {
        return currentPosition + diceRoll;
    }
    
    @Override
    public boolean shouldRiskMove(int currentPosition, int diceRoll, Board board) {
        return Math.random() > 0.2;
    }
    
    @Override
    public BotDifficulty getDifficulty() {
        return BotDifficulty.HARD;
    }
}