package com.snakeladders.controller;

import com.snakeladders.dto.CreateGameRequest;
import com.snakeladders.dto.GameDto;
import com.snakeladders.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "*")
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody CreateGameRequest request) {
        try {
            GameDto game = gameService.createGame(request);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{gameId}")
    public ResponseEntity<GameDto> getGame(@PathVariable String gameId) {
        try {
            GameDto game = gameService.getGame(gameId);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{gameId}/start")
    public ResponseEntity<GameDto> startGame(@PathVariable String gameId) {
        try {
            GameDto game = gameService.startGame(gameId);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{gameId}/move")
    public ResponseEntity<GameDto> makeMove(@PathVariable String gameId) {
        try {
            GameDto game = gameService.makeMove(gameId);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/quick")
    public ResponseEntity<GameDto> createQuickGame(@RequestParam(defaultValue = "1") int humanPlayers,
                                                   @RequestParam(defaultValue = "1") int botPlayers) {
        try {
            CreateGameRequest request = new CreateGameRequest();
            request.setHumanPlayers(java.util.List.of("Player 1"));
            request.setBotPlayers(java.util.List.of(
                    new CreateGameRequest.BotRequest("Bot 1", 
                            com.snakeladders.model.player.BotDifficulty.MEDIUM)
            ));
            
            GameDto game = gameService.createGame(request);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}