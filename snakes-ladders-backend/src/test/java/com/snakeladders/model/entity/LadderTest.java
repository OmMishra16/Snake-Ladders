package com.snakeladders.model.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LadderTest {

    @Test
    void testValidLadderCreation() {
        Ladder ladder = new Ladder(6, 25);
        assertEquals(6, ladder.getFrom());
        assertEquals(25, ladder.getTo());
        assertEquals(EntityType.LADDER, ladder.getEntityType());
    }

    @Test
    void testInvalidLadderCreation_FromHigherThanTo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ladder(50, 10);
        });
    }

    @Test
    void testInvalidLadderCreation_FromEqualToTo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ladder(25, 25);
        });
    }

    @Test
    void testIsValidEntity() {
        Ladder ladder = new Ladder(6, 25);
        assertTrue(ladder.isValidEntity(100));
        assertFalse(ladder.isValidEntity(20));
    }

    @Test
    void testToString() {
        Ladder ladder = new Ladder(6, 25);
        assertEquals("Ladder{from=6, to=25}", ladder.toString());
    }
}