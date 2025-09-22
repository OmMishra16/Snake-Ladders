import React, { useState } from 'react';
import './GameSetup.css';

const GameSetup = ({ onCreateGame, isLoading }) => {
  const [gameConfig, setGameConfig] = useState({
    boardSize: 10,
    humanPlayers: ['Player 1'],
    botPlayers: [{ name: 'Bot 1', difficulty: 'MEDIUM' }],
    startStrategy: 'DEFAULT',
    winStrategy: 'DEFAULT',
    moveRule: 'DEFAULT'
  });

  const handleHumanPlayerChange = (index, value) => {
    const newHumanPlayers = [...gameConfig.humanPlayers];
    newHumanPlayers[index] = value;
    setGameConfig(prev => ({ ...prev, humanPlayers: newHumanPlayers }));
  };

  const addHumanPlayer = () => {
    setGameConfig(prev => ({
      ...prev,
      humanPlayers: [...prev.humanPlayers, `Player ${prev.humanPlayers.length + 1}`]
    }));
  };

  const removeHumanPlayer = (index) => {
    if (gameConfig.humanPlayers.length > 1 || gameConfig.botPlayers.length > 0) {
      setGameConfig(prev => ({
        ...prev,
        humanPlayers: prev.humanPlayers.filter((_, i) => i !== index)
      }));
    }
  };

  const handleBotPlayerChange = (index, field, value) => {
    const newBotPlayers = [...gameConfig.botPlayers];
    newBotPlayers[index] = { ...newBotPlayers[index], [field]: value };
    setGameConfig(prev => ({ ...prev, botPlayers: newBotPlayers }));
  };

  const addBotPlayer = () => {
    setGameConfig(prev => ({
      ...prev,
      botPlayers: [...prev.botPlayers, { 
        name: `Bot ${prev.botPlayers.length + 1}`, 
        difficulty: 'MEDIUM' 
      }]
    }));
  };

  const removeBotPlayer = (index) => {
    if (gameConfig.botPlayers.length > 1 || gameConfig.humanPlayers.length > 0) {
      setGameConfig(prev => ({
        ...prev,
        botPlayers: prev.botPlayers.filter((_, i) => i !== index)
      }));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (gameConfig.humanPlayers.length + gameConfig.botPlayers.length < 2) {
      alert('Game requires at least 2 players');
      return;
    }
    onCreateGame(gameConfig);
  };

  const createQuickGame = () => {
    onCreateGame({
      boardSize: 10,
      humanPlayers: ['Player 1'],
      botPlayers: [{ name: 'Bot 1', difficulty: 'MEDIUM' }],
      startStrategy: 'DEFAULT',
      winStrategy: 'DEFAULT',
      moveRule: 'DEFAULT'
    });
  };

  return (
    <div className="game-setup">
      <h2>Create New Game</h2>
      
      <div className="quick-start">
        <button 
          className="btn btn-primary quick-game-btn"
          onClick={createQuickGame}
          disabled={isLoading}
        >
          Quick Start (1 Human vs 1 Bot)
        </button>
      </div>

      <div className="divider">
        <span>OR</span>
      </div>

      <form onSubmit={handleSubmit} className="setup-form">
        <div className="form-group">
          <label>Board Size:</label>
          <select 
            value={gameConfig.boardSize}
            onChange={(e) => setGameConfig(prev => ({ ...prev, boardSize: parseInt(e.target.value) }))}
          >
            <option value={10}>10x10 (Standard)</option>
            <option value={8}>8x8 (Small)</option>
            <option value={12}>12x12 (Large)</option>
          </select>
        </div>

        <div className="players-section">
          <h3>Human Players</h3>
          {gameConfig.humanPlayers.map((player, index) => (
            <div key={index} className="player-input">
              <input
                type="text"
                value={player}
                onChange={(e) => handleHumanPlayerChange(index, e.target.value)}
                placeholder={`Player ${index + 1} name`}
              />
              <button 
                type="button" 
                className="btn btn-danger btn-small"
                onClick={() => removeHumanPlayer(index)}
                disabled={gameConfig.humanPlayers.length === 1 && gameConfig.botPlayers.length === 0}
              >
                Remove
              </button>
            </div>
          ))}
          <button 
            type="button" 
            className="btn btn-secondary"
            onClick={addHumanPlayer}
          >
            Add Human Player
          </button>
        </div>

        <div className="players-section">
          <h3>Bot Players</h3>
          {gameConfig.botPlayers.map((bot, index) => (
            <div key={index} className="bot-input">
              <input
                type="text"
                value={bot.name}
                onChange={(e) => handleBotPlayerChange(index, 'name', e.target.value)}
                placeholder={`Bot ${index + 1} name`}
              />
              <select
                value={bot.difficulty}
                onChange={(e) => handleBotPlayerChange(index, 'difficulty', e.target.value)}
              >
                <option value="EASY">Easy</option>
                <option value="MEDIUM">Medium</option>
                <option value="HARD">Hard</option>
                <option value="EXPERT">Expert</option>
              </select>
              <button 
                type="button" 
                className="btn btn-danger btn-small"
                onClick={() => removeBotPlayer(index)}
                disabled={gameConfig.botPlayers.length === 1 && gameConfig.humanPlayers.length === 0}
              >
                Remove
              </button>
            </div>
          ))}
          <button 
            type="button" 
            className="btn btn-secondary"
            onClick={addBotPlayer}
          >
            Add Bot Player
          </button>
        </div>

        <div className="rules-section">
          <h3>Game Rules</h3>
          
          <div className="form-group">
            <label>Start Strategy:</label>
            <select 
              value={gameConfig.startStrategy}
              onChange={(e) => setGameConfig(prev => ({ ...prev, startStrategy: e.target.value }))}
            >
              <option value="DEFAULT">Any roll to start</option>
              <option value="SIX">Must roll 6 to start</option>
            </select>
          </div>

          <div className="form-group">
            <label>Win Strategy:</label>
            <select 
              value={gameConfig.winStrategy}
              onChange={(e) => setGameConfig(prev => ({ ...prev, winStrategy: e.target.value }))}
            >
              <option value="DEFAULT">Reach or cross last cell</option>
              <option value="EXACT">Must land exactly on last cell</option>
            </select>
          </div>

          <div className="form-group">
            <label>Cell Occupation Rule:</label>
            <select 
              value={gameConfig.moveRule}
              onChange={(e) => setGameConfig(prev => ({ ...prev, moveRule: e.target.value }))}
            >
              <option value="DEFAULT">Standard rules</option>
              <option value="KICK">Kick other players back to start</option>
              <option value="SHARE">Allow multiple players on same cell</option>
            </select>
          </div>
        </div>

        <button 
          type="submit" 
          className="btn btn-success create-btn"
          disabled={isLoading || (gameConfig.humanPlayers.length + gameConfig.botPlayers.length < 2)}
        >
          {isLoading ? 'Creating Game...' : 'Create Game'}
        </button>
      </form>
    </div>
  );
};

export default GameSetup;