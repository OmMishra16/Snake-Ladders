package com.snakeladders.model.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BotFactoryTest {

    @Test
    void testCreateBot_Easy() {
        BotPlayer bot = BotFactory.createBot("EasyBot", BotDifficulty.EASY);
        assertEquals("EasyBot", bot.getName());
        assertEquals(PlayerType.BOT, bot.getPlayerType());
        assertEquals(BotDifficulty.EASY, bot.getDifficulty());
        assertInstanceOf(BasicBotStrategy.class, bot.getBotStrategy());
    }

    @Test
    void testCreateBot_Medium() {
        BotPlayer bot = BotFactory.createBot("MediumBot", BotDifficulty.MEDIUM);
        assertEquals("MediumBot", bot.getName());
        assertEquals(BotDifficulty.MEDIUM, bot.getDifficulty());
        assertInstanceOf(SmartBotStrategy.class, bot.getBotStrategy());
    }

    @Test
    void testCreateBot_Hard() {
        BotPlayer bot = BotFactory.createBot("HardBot", BotDifficulty.HARD);
        assertEquals("HardBot", bot.getName());
        assertEquals(BotDifficulty.HARD, bot.getDifficulty());
        assertInstanceOf(AggressiveBotStrategy.class, bot.getBotStrategy());
    }

    @Test
    void testCreateBot_Expert() {
        BotPlayer bot = BotFactory.createBot("ExpertBot", BotDifficulty.EXPERT);
        assertEquals("ExpertBot", bot.getName());
        assertEquals(BotDifficulty.EXPERT, bot.getDifficulty());
        assertInstanceOf(DefensiveBotStrategy.class, bot.getBotStrategy());
    }

    @Test
    void testCreateRandomBot() {
        BotPlayer bot = BotFactory.createRandomBot("RandomBot");
        assertEquals("RandomBot", bot.getName());
        assertEquals(PlayerType.BOT, bot.getPlayerType());
        assertNotNull(bot.getDifficulty());
        assertNotNull(bot.getBotStrategy());
    }
}