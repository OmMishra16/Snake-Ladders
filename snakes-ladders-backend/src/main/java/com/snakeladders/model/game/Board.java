package com.snakeladders.model.game;

import com.snakeladders.model.entity.BoardEntity;
import com.snakeladders.model.entity.Snake;
import com.snakeladders.model.entity.Ladder;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;
    private final Map<Integer, BoardEntity> entityMap;
    
    public Board(int size) {
        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.entityMap = new HashMap<>();
        initializeDefaultBoard();
    }
    
    public Board(int size, List<Snake> snakes, List<Ladder> ladders) {
        this.size = size;
        this.snakes = new ArrayList<>(snakes);
        this.ladders = new ArrayList<>(ladders);
        this.entityMap = new HashMap<>();
        buildEntityMap();
    }
    
    private void initializeDefaultBoard() {
        if (size == 10) {
            addSnake(new Snake(99, 54));
            addSnake(new Snake(70, 55));
            addSnake(new Snake(52, 42));
            addSnake(new Snake(25, 2));
            
            addLadder(new Ladder(6, 25));
            addLadder(new Ladder(11, 40));
            addLadder(new Ladder(60, 85));
            addLadder(new Ladder(46, 90));
        }
        buildEntityMap();
    }
    
    private void addSnake(Snake snake) {
        if (snake.isValidEntity(size * size)) {
            snakes.add(snake);
        }
    }
    
    private void addLadder(Ladder ladder) {
        if (ladder.isValidEntity(size * size)) {
            ladders.add(ladder);
        }
    }
    
    private void buildEntityMap() {
        entityMap.clear();
        for (Snake snake : snakes) {
            entityMap.put(snake.getFrom(), snake);
        }
        for (Ladder ladder : ladders) {
            entityMap.put(ladder.getFrom(), ladder);
        }
    }
    
    public int getNewPosition(int position) {
        BoardEntity entity = entityMap.get(position);
        if (entity != null) {
            return entity.getTo();
        }
        return position;
    }
    
    public BoardEntity getEntityAt(int position) {
        return entityMap.get(position);
    }
    
    public int getSize() {
        return size;
    }
    
    public List<Snake> getSnakes() {
        return new ArrayList<>(snakes);
    }
    
    public List<Ladder> getLadders() {
        return new ArrayList<>(ladders);
    }
    
    public int getTotalCells() {
        return size * size;
    }
}