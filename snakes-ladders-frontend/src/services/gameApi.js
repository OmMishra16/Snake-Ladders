import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const gameApi = {
  createGame: async (gameConfig) => {
    const response = await api.post('/games', gameConfig);
    return response.data;
  },

  getGame: async (gameId) => {
    const response = await api.get(`/games/${gameId}`);
    return response.data;
  },

  startGame: async (gameId) => {
    const response = await api.post(`/games/${gameId}/start`);
    return response.data;
  },

  makeMove: async (gameId) => {
    const response = await api.post(`/games/${gameId}/move`);
    return response.data;
  },

  createQuickGame: async () => {
    const response = await api.post('/games/quick');
    return response.data;
  },
};