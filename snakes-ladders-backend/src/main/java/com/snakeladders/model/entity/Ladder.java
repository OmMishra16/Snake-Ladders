package com.snakeladders.model.entity;

public class Ladder implements BoardEntity {
    private final int from;
    private final int to;
    
    public Ladder(int from, int to) {
        if (from >= to) {
            throw new IllegalArgumentException("Ladder bottom must be lower than top");
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
        return EntityType.LADDER;
    }
    
    @Override
    public boolean isValidEntity(int boardSize) {
        return BoardEntity.super.isValidEntity(boardSize) && from < to;
    }
    
    @Override
    public String toString() {
        return String.format("Ladder{from=%d, to=%d}", from, to);
    }
}