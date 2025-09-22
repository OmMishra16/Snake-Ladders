package com.snakeladders.model.strategy.win;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WinStrategyTest {

    @Test
    void testDefaultWinStrategy() {
        DefaultWinStrategy strategy = new DefaultWinStrategy();
        
        assertFalse(strategy.hasWon(99, 10));
        assertTrue(strategy.hasWon(100, 10));
        assertTrue(strategy.hasWon(101, 10));
        assertEquals("Win by reaching or crossing the last cell", strategy.getDescription());
    }

    @Test
    void testExactWinStrategy() {
        ExactWinStrategy strategy = new ExactWinStrategy();
        
        assertFalse(strategy.hasWon(99, 10));
        assertTrue(strategy.hasWon(100, 10));
        assertFalse(strategy.hasWon(101, 10));
        assertEquals("Must land exactly on the last cell to win", strategy.getDescription());
    }
}