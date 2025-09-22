import React from 'react';
import './PlayerList.css';

const PlayerList = ({ players, currentPlayerIndex, winner }) => {
  const getPlayerColor = (index) => {
    const colors = ['#ff6b6b', '#4ecdc4', '#45b7d1', '#f9ca24', '#f0932b', '#eb4d4b'];
    return colors[index % colors.length];
  };

  const getPlayerStatus = (player, index) => {
    if (winner && winner.name === player.name) {
      return 'winner';
    }
    if (index === currentPlayerIndex) {
      return 'current';
    }
    return 'normal';
  };

  return (
    <div className="player-list">
      <h3>Players</h3>
      <div className="players">
        {players.map((player, index) => {
          const status = getPlayerStatus(player, index);
          return (
            <div 
              key={player.name} 
              className={`player-card ${status} ${player.playerType.toLowerCase()}`}
            >
              <div 
                className="player-avatar"
                style={{ backgroundColor: getPlayerColor(index) }}
              >
                {player.name.charAt(0)}
              </div>
              <div className="player-info">
                <div className="player-name">
                  {player.name}
                  {player.playerType === 'BOT' && (
                    <span className="player-type">
                      (Bot - {player.botDifficulty})
                    </span>
                  )}
                </div>
                <div className="player-position">
                  Position: {player.position}
                </div>
                {status === 'current' && (
                  <div className="current-indicator">👆 Current Turn</div>
                )}
                {status === 'winner' && (
                  <div className="winner-indicator">🏆 Winner!</div>
                )}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default PlayerList;