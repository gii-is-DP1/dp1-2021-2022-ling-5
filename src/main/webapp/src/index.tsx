import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Links from './Links';
import OnGoingTest from './modules/games/OnGoingTorreInfernal/OnGoingTorreInfernalTest';
import {GetCardTest, GetGameTest, GetPlayerCardTest, GetPointsTest} from './modules/games/OnGoingTorreInfernal/OnGoingTestGet';

ReactDOM.render(
  <React.StrictMode>
    <GetGameTest />
    <GetPlayerCardTest />
    <GetCardTest />
    <GetPointsTest />
    <OnGoingTest />
    <Links />
  </React.StrictMode>,
  document.getElementById('root')
);

