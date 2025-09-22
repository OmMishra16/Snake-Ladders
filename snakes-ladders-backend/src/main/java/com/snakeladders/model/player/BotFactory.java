package com.snakeladders.model.player;

public class BotFactory {
    
    public static BotPlayer createBot(String name, BotDifficulty difficulty) {
        IBotStrategy strategy = switch (difficulty) {
            case EASY -> new BasicBotStrategy();
            case MEDIUM -> new SmartBotStrategy();
            case HARD -> new AggressiveBotStrategy();
            case EXPERT -> new DefensiveBotStrategy();
        };
        
        return new BotPlayer(name, strategy);
    }
    
    public static BotPlayer createRandomBot(String name) {
        BotDifficulty[] difficulties = BotDifficulty.values();
        BotDifficulty randomDifficulty = difficulties[(int) (Math.random() * difficulties.length)];
        return createBot(name, randomDifficulty);
    }
}