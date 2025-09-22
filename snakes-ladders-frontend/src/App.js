import React, { useState, useEffect } from 'react';
import GameBoard from './components/GameBoard/GameBoard';
import GameControls from './components/GameControls/GameControls';
import PlayerList from './components/Player/PlayerList';
import GameSetup from './components/GameSetup/GameSetup';
import { gameApi } from './services/gameApi';
import './App.css';

function App() {
  const [game, setGame] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [showSetup, setShowSetup] = useState(true);

  const handleCreateGame = async (gameConfig) => {
    setIsLoading(true);
    setError(null);
    try {
      const newGame = await gameApi.createGame(gameConfig);
      setGame(newGame);
      setShowSetup(false);
    } catch (err) {
      setError('Failed to create game: ' + err.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleStartGame = async () => {
    if (!game) return;
    
    setIsLoading(true);
    setError(null);
    try {
      const updatedGame = await gameApi.startGame(game.gameId);
      setGame(updatedGame);
    } catch (err) {
      setError('Failed to start game: ' + err.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleMakeMove = async () => {
    if (!game) return;
    
    setIsLoading(true);
    setError(null);
    try {
      const updatedGame = await gameApi.makeMove(game.gameId);
      setGame(updatedGame);
    } catch (err) {
      setError('Failed to make move: ' + err.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleNewGame = () => {
    setGame(null);
    setShowSetup(true);
    setError(null);
  };

  const handleCellClick = (cellNumber) => {
    console.log(`Clicked cell: ${cellNumber}`);
  };

  useEffect(() => {
    let interval;
    if (game && game.gameState === 'IN_PROGRESS') {
      interval = setInterval(async () => {
        try {
          const updatedGame = await gameApi.getGame(game.gameId);
          setGame(updatedGame);
        } catch (err) {
          console.error('Failed to refresh game state:', err);
        }
      }, 2000); // Refresh every 2 seconds during active game
    }
    return () => {
      if (interval) {
        clearInterval(interval);
      }
    };
  }, [game]);

  if (showSetup || !game) {
    return (
      <div className="App">
        <header className="App-header">
          <h1>🐍 Snake and Ladders 🪜</h1>
        </header>
        <main>
          {error && <div className="error-message">{error}</div>}
          <GameSetup onCreateGame={handleCreateGame} isLoading={isLoading} />
        </main>
      </div>
    );
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>🐍 Snake and Ladders 🪜</h1>
      </header>
      
      {error && <div className="error-message">{error}</div>}
      
      <main className="game-container">
        <div className="game-left">
          <GameControls 
            game={game}
            onStartGame={handleStartGame}
            onMakeMove={handleMakeMove}
            onNewGame={handleNewGame}
            isLoading={isLoading}
          />
          <PlayerList 
            players={game.players}
            currentPlayerIndex={game.currentPlayerIndex}
            winner={game.winner}
          />
        </div>
        
        <div className="game-center">
          <GameBoard 
            board={game.board}
            players={game.players}
            onCellClick={handleCellClick}
          />
        </div>
      </main>
    </div>
  );
}

export default App;