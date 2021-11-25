
import Button from 'react-bootstrap/Button';
import { ButtonGroup, Form, ToggleButton, ToggleButtonGroup } from 'react-bootstrap';

import './NewGame.css';
import { useState } from 'react';


function JoinGame() {
  const [namegame, setNamegame] = useState<string>();
  var idplayer = 1;

  function joinGame(){
    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    }
    
    return new Promise(function (resolve, reject) {
      fetch(`http://localhost:8080/api/games/names/${namegame}`, requestOptions)
      .then(res => {
        res.json().then((game:any) => {
          const gameid = game.id;
          const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' }
          }

          fetch(`/games/${gameid}/players/${idplayer}`, requestOptions).then(res => {
              resolve(res.json())
          })
          .catch(error => reject(console.error));
          window.location.href = '/startGame'
      })
      .catch(error => reject(console.error))
      })
    })
  }

  return (
  
    <div className="NewGame-header"> 
      <p>JOIN GAME</p>
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Game's name you want to join</Form.Label>
          <Form.Control placeholder="Enter game's name" onChange={(e) => setNamegame(e.target.value )}/>
        </Form.Group>

        
        <Button className="Button" size="lg" variant="dark" onClick={() => joinGame()}>
          JOIN
        </Button>
      </Form>
    </div>

      
  );
}


export default JoinGame ;
