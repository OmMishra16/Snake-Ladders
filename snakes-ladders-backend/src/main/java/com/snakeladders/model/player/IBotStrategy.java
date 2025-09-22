package com.snakeladders.model.player;

import com.snakeladders.model.game.Board;
import java.util.List;

public interface IBotStrategy {
    int decideMovePosition(int currentPosition, int diceRoll, Board board, List<IPlayer> otherPlayers);
    boolean shouldRiskMove(int currentPosition, int diceRoll, Board board);
    BotDifficulty getDifficulty();
}