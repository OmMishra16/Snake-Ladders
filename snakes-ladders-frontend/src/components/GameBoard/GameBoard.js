import React from 'react';
import './GameBoard.css';

const GameBoard = ({ board, players, onCellClick }) => {
  const { size, snakes, ladders } = board;
  
  const getCellNumber = (row, col) => {
    if (row % 2 === 0) {
      return row * size + col + 1;
    } else {
      return row * size + (size - col);
    }
  };

  const getPlayersOnCell = (cellNumber) => {
    return players.filter(player => player.position === cellNumber);
  };

  const getEntityOnCell = (cellNumber) => {
    const snake = snakes.find(s => s.from === cellNumber);
    const ladder = ladders.find(l => l.from === cellNumber);
    return snake || ladder;
  };

  const renderCell = (row, col) => {
    const cellNumber = getCellNumber(row, col);
    const playersOnCell = getPlayersOnCell(cellNumber);
    const entity = getEntityOnCell(cellNumber);
    
    return (
      <div
        key={`${row}-${col}`}
        className={`cell ${entity ? entity.entityType.toLowerCase() : ''} ${playersOnCell.length > 0 ? 'has-players' : ''}`}
        onClick={() => onCellClick && onCellClick(cellNumber)}
      >
        <div className="cell-number">{cellNumber}</div>
        
        {/* Snake or Ladder visual */}
        {entity && (
          <div className="entity-visual">
            {entity.entityType === 'SNAKE' ? (
              <div className="snake">
                <div className="snake-body">🐍</div>
                <div className="entity-destination">→ {entity.to}</div>
              </div>
            ) : (
              <div className="ladder">
                <div className="ladder-body">🪜</div>
                <div className="entity-destination">→ {entity.to}</div>
              </div>
            )}
          </div>
        )}
        
        {/* Player pieces */}
        {playersOnCell.length > 0 && (
          <div className="player-pieces">
            {playersOnCell.map((player, index) => (
              <div
                key={player.name}
                className={`player-piece ${player.playerType.toLowerCase()}`}
                style={{
                  backgroundColor: getPlayerColor(players.indexOf(player)),
                  transform: `translate(${(index % 2) * 15}px, ${Math.floor(index / 2) * 15}px)`,
                  zIndex: 10 + index
                }}
                title={`${player.name} (Position: ${player.position})`}
              >
                <div className="player-avatar">
                  {player.playerType === 'BOT' ? '🤖' : '👤'}
                </div>
                <div className="player-name">{player.name.charAt(0)}</div>
              </div>
            ))}
          </div>
        )}
      </div>
    );
  };

  const getPlayerColor = (index) => {
    const colors = ['#ff6b6b', '#4ecdc4', '#45b7d1', '#f9ca24', '#f0932b', '#eb4d4b'];
    return colors[index % colors.length];
  };

  return (
    <div className="game-board">
      <div className="board-grid" style={{ gridTemplateColumns: `repeat(${size}, 1fr)` }}>
        {Array.from({ length: size }, (_, row) =>
          Array.from({ length: size }, (_, col) =>
            renderCell(size - 1 - row, col)
          )
        )}
      </div>
    </div>
  );
};

export default GameBoard;