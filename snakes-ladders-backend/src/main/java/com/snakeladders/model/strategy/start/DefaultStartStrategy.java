package com.snakeladders.model.strategy.start;

public class DefaultStartStrategy implements IStartStrategy {
    
    @Override
    public boolean canStart(int roll) {
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Can start with any dice roll";
    }
}