package com.snakeladders.model.strategy.move;

import com.snakeladders.model.player.IPlayer;
import java.util.List;

public class DefaultMoveRule implements IMoveRuleStrategy {
    
    @Override
    public int applyRules(IPlayer player, int diceRoll, List<IPlayer> allPlayers) {
        return player.getPosition() + diceRoll;
    }
    
    @Override
    public String getDescription() {
        return "Standard movement rules";
    }
}