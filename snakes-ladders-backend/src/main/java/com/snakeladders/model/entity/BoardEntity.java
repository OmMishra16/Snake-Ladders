package com.snakeladders.model.entity;

public interface BoardEntity {
    int getFrom();
    int getTo();
    EntityType getEntityType();
    
    default boolean isValidEntity(int boardSize) {
        return getFrom() > 0 && getFrom() <= boardSize && 
               getTo() > 0 && getTo() <= boardSize && 
               getFrom() != getTo();
    }
}