import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Links from './Links';
import 'bootstrap/dist/css/bootstrap.min.css';
import Register from './modules/general/register';
import Login from './modules/general/login';
import { Card, Col, Row } from 'react-bootstrap';


ReactDOM.render(
  
  <React.StrictMode>
    
          
    {localStorage.getItem("sessionId") ? <Links /> : <Row>
      <Row><p id="title">
            Dobble
          </p>
        </Row>
        <Row>
      <Col className="m-5"><Card><Card.Body><h3 style={{ textAlign: "center" }}>Login</h3><Login /></Card.Body></Card></Col>
      <Col className="m-5"><Card><Card.Body><h3 style={{ textAlign: "center" }}>Register</h3><Register /></Card.Body></Card></Col>
      </Row>
    </Row>}
  </React.StrictMode >,
  document.getElementById('root')
);

