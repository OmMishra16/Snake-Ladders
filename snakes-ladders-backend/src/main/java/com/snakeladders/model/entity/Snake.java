package com.snakeladders.model.entity;

public class Snake implements BoardEntity {
    private final int from;
    private final int to;
    
    public Snake(int from, int to) {
        if (from <= to) {
            throw new IllegalArgumentException("Snake head must be higher than tail");
        }
        this.from = from;
        this.to = to;
    }
    
    @Override
    public int getFrom() {
        return from;
    }
    
    @Override
    public int getTo() {
        return to;
    }
    
    @Override
    public EntityType getEntityType() {
        return EntityType.SNAKE;
    }
    
    @Override
    public boolean isValidEntity(int boardSize) {
        return BoardEntity.super.isValidEntity(boardSize) && from > to;
    }
    
    @Override
    public String toString() {
        return String.format("Snake{from=%d, to=%d}", from, to);
    }
}