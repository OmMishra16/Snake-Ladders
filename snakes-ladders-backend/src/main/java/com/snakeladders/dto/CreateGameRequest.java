package com.snakeladders.dto;

import com.snakeladders.model.player.BotDifficulty;
import java.util.List;

public class CreateGameRequest {
    private int boardSize = 10;
    private List<String> humanPlayers;
    private List<BotRequest> botPlayers;
    private String startStrategy = "DEFAULT";
    private String winStrategy = "DEFAULT";
    private String moveRule = "DEFAULT";
    
    public CreateGameRequest() {}
    
    // Getters and setters
    public int getBoardSize() { return boardSize; }
    public void setBoardSize(int boardSize) { this.boardSize = boardSize; }
    
    public List<String> getHumanPlayers() { return humanPlayers; }
    public void setHumanPlayers(List<String> humanPlayers) { this.humanPlayers = humanPlayers; }
    
    public List<BotRequest> getBotPlayers() { return botPlayers; }
    public void setBotPlayers(List<BotRequest> botPlayers) { this.botPlayers = botPlayers; }
    
    public String getStartStrategy() { return startStrategy; }
    public void setStartStrategy(String startStrategy) { this.startStrategy = startStrategy; }
    
    public String getWinStrategy() { return winStrategy; }
    public void setWinStrategy(String winStrategy) { this.winStrategy = winStrategy; }
    
    public String getMoveRule() { return moveRule; }
    public void setMoveRule(String moveRule) { this.moveRule = moveRule; }
    
    public static class BotRequest {
        private String name;
        private BotDifficulty difficulty;
        
        public BotRequest() {}
        
        public BotRequest(String name, BotDifficulty difficulty) {
            this.name = name;
            this.difficulty = difficulty;
        }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public BotDifficulty getDifficulty() { return difficulty; }
        public void setDifficulty(BotDifficulty difficulty) { this.difficulty = difficulty; }
    }
}