package com.snakeladders.model.player;

import com.snakeladders.model.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    private HumanPlayer player;
    private Board board;

    @BeforeEach
    void setUp() {
        player = new HumanPlayer("Alice");
        board = new Board(10);
    }

    @Test
    void testPlayerCreation() {
        assertEquals("Alice", player.getName());
        assertEquals(0, player.getPosition());
        assertEquals(PlayerType.HUMAN, player.getPlayerType());
    }

    @Test
    void testMakeMove_NormalMove() {
        player.makeMove(5, board);
        assertEquals(5, player.getPosition());
    }

    @Test
    void testMakeMove_ExceedsBoardSize() {
        player.setPosition(95);
        player.makeMove(6, board); // Would go to 101, exceeds 100
        assertEquals(95, player.getPosition()); // Position unchanged
    }

    @Test
    void testMakeMove_WithinBoardSize() {
        player.setPosition(95);
        player.makeMove(5, board); // Goes to 100, exactly last cell
        assertEquals(100, player.getPosition());
    }

    @Test
    void testSetPosition() {
        player.setPosition(42);
        assertEquals(42, player.getPosition());
    }

    @Test
    void testResetPosition() {
        player.setPosition(50);
        player.resetPosition();
        assertEquals(0, player.getPosition());
    }

    @Test
    void testToString() {
        player.setPosition(25);
        assertEquals("HumanPlayer{name='Alice', position=25}", player.toString());
    }
}