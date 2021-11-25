import React from 'react';
import { render, screen } from '@testing-library/react';
import NewGame from './NewGame';

test('renders learn react link', () => {
  render(<NewGame />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
