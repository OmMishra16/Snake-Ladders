package com.snakeladders.model.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    @Test
    void testValidSnakeCreation() {
        Snake snake = new Snake(99, 54);
        assertEquals(99, snake.getFrom());
        assertEquals(54, snake.getTo());
        assertEquals(EntityType.SNAKE, snake.getEntityType());
    }

    @Test
    void testInvalidSnakeCreation_FromLowerThanTo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Snake(10, 50);
        });
    }

    @Test
    void testInvalidSnakeCreation_FromEqualToTo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Snake(25, 25);
        });
    }

    @Test
    void testIsValidEntity() {
        Snake snake = new Snake(99, 54);
        assertTrue(snake.isValidEntity(100));
        assertFalse(snake.isValidEntity(50));
    }

    @Test
    void testToString() {
        Snake snake = new Snake(99, 54);
        assertEquals("Snake{from=99, to=54}", snake.toString());
    }
}