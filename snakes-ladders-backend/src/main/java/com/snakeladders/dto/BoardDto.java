package com.snakeladders.dto;

import java.util.List;

public class BoardDto {
    private int size;
    private List<EntityDto> snakes;
    private List<EntityDto> ladders;
    
    public BoardDto() {}
    
    public BoardDto(int size, List<EntityDto> snakes, List<EntityDto> ladders) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
    }
    
    // Getters and setters
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    
    public List<EntityDto> getSnakes() { return snakes; }
    public void setSnakes(List<EntityDto> snakes) { this.snakes = snakes; }
    
    public List<EntityDto> getLadders() { return ladders; }
    public void setLadders(List<EntityDto> ladders) { this.ladders = ladders; }
}