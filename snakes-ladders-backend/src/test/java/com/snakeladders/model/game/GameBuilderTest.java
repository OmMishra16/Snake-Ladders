package com.snakeladders.model.game;

import com.snakeladders.model.player.*;
import com.snakeladders.model.strategy.start.SixStartStrategy;
import com.snakeladders.model.strategy.win.ExactWinStrategy;
import com.snakeladders.model.strategy.move.KickPlayerRule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameBuilderTest {

    @Test
    void testDefaultGameCreation() {
        Game game = GameBuilder.createDefault().build();
        
        assertNotNull(game.getGameId());
        assertEquals(GameState.WAITING_TO_START, game.getGameState());
        assertEquals(2, game.getPlayers().size());
        assertEquals(10, game.getBoard().getSize());
        assertEquals(0, game.getTurnCount());
        assertNull(game.getWinner());
    }

    @Test
    void testCustomGameCreation() {
        Game game = new GameBuilder()
                .setBoardSize(8)
                .addHumanPlayer("Alice")
                .addBot("SmartBot", BotDifficulty.HARD)
                .setStartStrategy(new SixStartStrategy())
                .setWinStrategy(new ExactWinStrategy())
                .setMoveRule(new KickPlayerRule())
                .build();
        
        assertEquals(8, game.getBoard().getSize());
        assertEquals(2, game.getPlayers().size());
        assertEquals("Alice", game.getPlayers().get(0).getName());
        assertEquals("SmartBot", game.getPlayers().get(1).getName());
        assertEquals("Must roll 6 to start", game.getStartStrategy().getDescription());
        assertEquals("Must land exactly on the last cell to win", game.getWinStrategy().getDescription());
    }

    @Test
    void testQuickGameCreation() {
        Game game = GameBuilder.createQuickGame(2, 1).build();
        
        assertEquals(3, game.getPlayers().size());
        assertEquals("Player 1", game.getPlayers().get(0).getName());
        assertEquals("Player 2", game.getPlayers().get(1).getName());
        assertEquals("Bot 1", game.getPlayers().get(2).getName());
    }

    @Test
    void testInsufficientPlayers() {
        assertThrows(IllegalStateException.class, () -> {
            new GameBuilder().build();
        });
    }

    @Test
    void testAddRandomBots() {
        Game game = new GameBuilder()
                .addHumanPlayer("Alice")
                .addRandomBots(2)
                .build();
        
        assertEquals(3, game.getPlayers().size());
        assertEquals(PlayerType.HUMAN, game.getPlayers().get(0).getPlayerType());
        assertEquals(PlayerType.BOT, game.getPlayers().get(1).getPlayerType());
        assertEquals(PlayerType.BOT, game.getPlayers().get(2).getPlayerType());
    }
}