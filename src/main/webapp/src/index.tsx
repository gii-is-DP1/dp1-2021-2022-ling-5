import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Links from './Links';
import 'bootstrap/dist/css/bootstrap.min.css';
import Patata from './Patata';
import Register from './modules/general/register';
import Login from './modules/general/login';
import { Col, Row } from 'react-bootstrap';


ReactDOM.render(
  <React.StrictMode>
    {localStorage.getItem("sessionId") ? <Links /> : <Row>
      <Col><h3 style={{ textAlign: "center" }}>Login</h3><Login /></Col>
      <Col><h3 style={{ textAlign: "center" }}>Register</h3><Register /></Col>
    </Row>}
  </React.StrictMode >,
  document.getElementById('root')
);

