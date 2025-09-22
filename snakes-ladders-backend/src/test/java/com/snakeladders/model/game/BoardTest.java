package com.snakeladders.model.game;

import com.snakeladders.model.entity.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testDefaultBoardCreation() {
        Board board = new Board(10);
        assertEquals(10, board.getSize());
        assertEquals(100, board.getTotalCells());
        assertFalse(board.getSnakes().isEmpty());
        assertFalse(board.getLadders().isEmpty());
    }

    @Test
    void testCustomBoardCreation() {
        List<Snake> snakes = List.of(new Snake(16, 6));
        List<Ladder> ladders = List.of(new Ladder(4, 14));
        
        Board board = new Board(10, snakes, ladders);
        assertEquals(10, board.getSize());
        assertEquals(1, board.getSnakes().size());
        assertEquals(1, board.getLadders().size());
    }

    @Test
    void testGetNewPosition_WithSnake() {
        List<Snake> snakes = List.of(new Snake(16, 6));
        List<Ladder> ladders = new ArrayList<>();
        
        Board board = new Board(10, snakes, ladders);
        assertEquals(6, board.getNewPosition(16)); // Snake takes you down
        assertEquals(5, board.getNewPosition(5)); // Normal position unchanged
    }

    @Test
    void testGetNewPosition_WithLadder() {
        List<Snake> snakes = new ArrayList<>();
        List<Ladder> ladders = List.of(new Ladder(4, 14));
        
        Board board = new Board(10, snakes, ladders);
        assertEquals(14, board.getNewPosition(4)); // Ladder takes you up
        assertEquals(5, board.getNewPosition(5)); // Normal position unchanged
    }

    @Test
    void testGetEntityAt() {
        List<Snake> snakes = List.of(new Snake(16, 6));
        List<Ladder> ladders = List.of(new Ladder(4, 14));
        
        Board board = new Board(10, snakes, ladders);
        
        BoardEntity snake = board.getEntityAt(16);
        assertNotNull(snake);
        assertEquals(EntityType.SNAKE, snake.getEntityType());
        
        BoardEntity ladder = board.getEntityAt(4);
        assertNotNull(ladder);
        assertEquals(EntityType.LADDER, ladder.getEntityType());
        
        assertNull(board.getEntityAt(5));
    }
}