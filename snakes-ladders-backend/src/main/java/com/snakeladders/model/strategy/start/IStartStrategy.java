package com.snakeladders.model.strategy.start;

public interface IStartStrategy {
    boolean canStart(int roll);
    String getDescription();
}