import React from 'react';
import './GameControls.css';

const GameControls = ({ 
  game, 
  onStartGame, 
  onMakeMove, 
  onNewGame,
  isLoading 
}) => {
  const { gameState, players, currentPlayerIndex, winner, turnCount } = game;
  const currentPlayer = players[currentPlayerIndex];

  const canMakeMove = () => {
    return gameState === 'IN_PROGRESS' && !isLoading;
  };

  const canStartGame = () => {
    return gameState === 'WAITING_TO_START' && !isLoading;
  };

  const renderGameStatus = () => {
    switch (gameState) {
      case 'WAITING_TO_START':
        return (
          <div className="status waiting">
            <h3>Game Ready to Start</h3>
            <p>Players: {players.map(p => p.name).join(', ')}</p>
          </div>
        );
      case 'IN_PROGRESS':
        return (
          <div className="status playing">
            <h3>Game in Progress</h3>
            <p className="current-player">
              Current Player: <strong>{currentPlayer.name}</strong>
              {currentPlayer.playerType === 'BOT' && (
                <span className="bot-difficulty">({currentPlayer.botDifficulty})</span>
              )}
            </p>
            <p className="turn-count">Turn: {turnCount}</p>
          </div>
        );
      case 'FINISHED':
        return (
          <div className="status finished">
            <h3>🎉 Game Finished!</h3>
            <p className="winner">
              Winner: <strong>{winner.name}</strong>
              {winner.playerType === 'BOT' && (
                <span className="bot-difficulty">({winner.botDifficulty})</span>
              )}
            </p>
            <p className="final-position">Final Position: {winner.position}</p>
            <p className="total-turns">Total Turns: {turnCount}</p>
          </div>
        );
      default:
        return null;
    }
  };

  return (
    <div className="game-controls">
      {renderGameStatus()}
      
      <div className="control-buttons">
        {canStartGame() && (
          <button 
            className="btn btn-primary"
            onClick={onStartGame}
            disabled={isLoading}
          >
            {isLoading ? 'Starting...' : 'Start Game'}
          </button>
        )}
        
        {canMakeMove() && (
          <button 
            className="btn btn-success"
            onClick={onMakeMove}
            disabled={isLoading}
          >
            {isLoading ? 'Rolling...' : 'Roll Dice & Move'}
          </button>
        )}
        
        {gameState === 'FINISHED' && (
          <button 
            className="btn btn-secondary"
            onClick={onNewGame}
            disabled={isLoading}
          >
            New Game
          </button>
        )}
      </div>

      <div className="game-rules">
        <h4>Game Rules</h4>
        <p><strong>Start:</strong> {game.startStrategyDescription}</p>
        <p><strong>Win:</strong> {game.winStrategyDescription}</p>
        <p><strong>Move:</strong> {game.moveRuleDescription}</p>
      </div>
    </div>
  );
};

export default GameControls;