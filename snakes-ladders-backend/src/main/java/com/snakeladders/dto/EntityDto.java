package com.snakeladders.dto;

import com.snakeladders.model.entity.EntityType;

public class EntityDto {
    private int from;
    private int to;
    private EntityType entityType;
    
    public EntityDto() {}
    
    public EntityDto(int from, int to, EntityType entityType) {
        this.from = from;
        this.to = to;
        this.entityType = entityType;
    }
    
    // Getters and setters
    public int getFrom() { return from; }
    public void setFrom(int from) { this.from = from; }
    
    public int getTo() { return to; }
    public void setTo(int to) { this.to = to; }
    
    public EntityType getEntityType() { return entityType; }
    public void setEntityType(EntityType entityType) { this.entityType = entityType; }
}