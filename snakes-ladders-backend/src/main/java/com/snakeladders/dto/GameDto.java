package com.snakeladders.dto;

import com.snakeladders.model.game.GameState;
import java.util.List;

public class GameDto {
    private String gameId;
    private GameState gameState;
    private BoardDto board;
    private List<PlayerDto> players;
    private int currentPlayerIndex;
    private PlayerDto winner;
    private int turnCount;
    private String startStrategyDescription;
    private String winStrategyDescription;
    private String moveRuleDescription;
    
    public GameDto() {}
    
    public GameDto(String gameId, GameState gameState, BoardDto board, 
                   List<PlayerDto> players, int currentPlayerIndex, 
                   PlayerDto winner, int turnCount, String startStrategyDescription,
                   String winStrategyDescription, String moveRuleDescription) {
        this.gameId = gameId;
        this.gameState = gameState;
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = currentPlayerIndex;
        this.winner = winner;
        this.turnCount = turnCount;
        this.startStrategyDescription = startStrategyDescription;
        this.winStrategyDescription = winStrategyDescription;
        this.moveRuleDescription = moveRuleDescription;
    }
    
    // Getters and setters
    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }
    
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState gameState) { this.gameState = gameState; }
    
    public BoardDto getBoard() { return board; }
    public void setBoard(BoardDto board) { this.board = board; }
    
    public List<PlayerDto> getPlayers() { return players; }
    public void setPlayers(List<PlayerDto> players) { this.players = players; }
    
    public int getCurrentPlayerIndex() { return currentPlayerIndex; }
    public void setCurrentPlayerIndex(int currentPlayerIndex) { this.currentPlayerIndex = currentPlayerIndex; }
    
    public PlayerDto getWinner() { return winner; }
    public void setWinner(PlayerDto winner) { this.winner = winner; }
    
    public int getTurnCount() { return turnCount; }
    public void setTurnCount(int turnCount) { this.turnCount = turnCount; }
    
    public String getStartStrategyDescription() { return startStrategyDescription; }
    public void setStartStrategyDescription(String startStrategyDescription) { this.startStrategyDescription = startStrategyDescription; }
    
    public String getWinStrategyDescription() { return winStrategyDescription; }
    public void setWinStrategyDescription(String winStrategyDescription) { this.winStrategyDescription = winStrategyDescription; }
    
    public String getMoveRuleDescription() { return moveRuleDescription; }
    public void setMoveRuleDescription(String moveRuleDescription) { this.moveRuleDescription = moveRuleDescription; }
}