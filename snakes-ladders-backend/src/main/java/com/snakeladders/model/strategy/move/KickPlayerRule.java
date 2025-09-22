package com.snakeladders.model.strategy.move;

import com.snakeladders.model.player.IPlayer;
import java.util.List;

public class KickPlayerRule implements IMoveRuleStrategy {
    
    @Override
    public int applyRules(IPlayer player, int diceRoll, List<IPlayer> allPlayers) {
        int newPosition = player.getPosition() + diceRoll;
        
        for (IPlayer otherPlayer : allPlayers) {
            if (otherPlayer != player && otherPlayer.getPosition() == newPosition) {
                otherPlayer.resetPosition();
                break;
            }
        }
        
        return newPosition;
    }
    
    @Override
    public String getDescription() {
        return "Kick other players back to start when landing on same cell";
    }
}