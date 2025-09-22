package com.snakeladders.dto;

import com.snakeladders.model.player.PlayerType;
import com.snakeladders.model.player.BotDifficulty;

public class PlayerDto {
    private String name;
    private int position;
    private PlayerType playerType;
    private BotDifficulty botDifficulty;
    
    public PlayerDto() {}
    
    public PlayerDto(String name, int position, PlayerType playerType, BotDifficulty botDifficulty) {
        this.name = name;
        this.position = position;
        this.playerType = playerType;
        this.botDifficulty = botDifficulty;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    
    public PlayerType getPlayerType() { return playerType; }
    public void setPlayerType(PlayerType playerType) { this.playerType = playerType; }
    
    public BotDifficulty getBotDifficulty() { return botDifficulty; }
    public void setBotDifficulty(BotDifficulty botDifficulty) { this.botDifficulty = botDifficulty; }
}