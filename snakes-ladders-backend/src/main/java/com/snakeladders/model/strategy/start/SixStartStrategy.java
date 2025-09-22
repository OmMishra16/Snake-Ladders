package com.snakeladders.model.strategy.start;

public class SixStartStrategy implements IStartStrategy {
    
    @Override
    public boolean canStart(int roll) {
        return roll == 6;
    }
    
    @Override
    public String getDescription() {
        return "Must roll 6 to start";
    }
}