import { render, screen } from '@testing-library/react';
import App from './App';

test('renders snake and ladders heading', () => {
  render(<App />);
  const linkElement = screen.getByText(/Snake and Ladders/i);
  expect(linkElement).toBeInTheDocument();
});

test('renders game setup initially', () => {
  render(<App />);
  const quickStartButton = screen.getByText(/Quick Start/i);
  expect(quickStartButton).toBeInTheDocument();
});