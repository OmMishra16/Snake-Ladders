package com.snakeladders.model.strategy.move;

import com.snakeladders.model.player.IPlayer;
import java.util.List;

public interface IMoveRuleStrategy {
    int applyRules(IPlayer player, int diceRoll, List<IPlayer> allPlayers);
    String getDescription();
}