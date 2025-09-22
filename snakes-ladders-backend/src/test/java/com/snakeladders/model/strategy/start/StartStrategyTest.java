package com.snakeladders.model.strategy.start;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StartStrategyTest {

    @Test
    void testDefaultStartStrategy() {
        DefaultStartStrategy strategy = new DefaultStartStrategy();
        
        assertTrue(strategy.canStart(1));
        assertTrue(strategy.canStart(3));
        assertTrue(strategy.canStart(6));
        assertEquals("Can start with any dice roll", strategy.getDescription());
    }

    @Test
    void testSixStartStrategy() {
        SixStartStrategy strategy = new SixStartStrategy();
        
        assertFalse(strategy.canStart(1));
        assertFalse(strategy.canStart(3));
        assertFalse(strategy.canStart(5));
        assertTrue(strategy.canStart(6));
        assertEquals("Must roll 6 to start", strategy.getDescription());
    }
}