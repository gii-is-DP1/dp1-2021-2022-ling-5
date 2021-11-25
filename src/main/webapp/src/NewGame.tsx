
import Button from 'react-bootstrap/Button';
import { ButtonGroup, Form, ToggleButton, ToggleButtonGroup } from 'react-bootstrap';

import './NewGame.css';
import { useState } from 'react';


function NewGame() {

  const [game, setGame] = useState<any>({
      name: null,
      state: "UNSTARTED",
      startTime: null,
      endTime: null,
      creator: null,
  });

  const [minigame, setMinigame] = useState<number>();
  

  function createGame(){
      var idplayer = 1;
      var fechainicio = Date.now;
      setGame({ ...game, creator: idplayer, startTime:fechainicio })
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(game)
    }
    return new Promise(function (resolve, reject) {
      fetch(`http://localhost:8080/api/games`, requestOptions)
        .then(res => {
          res.json().then((game:any) => {
            const gameid = game.id;
            const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
          }
          if (minigame != 4){
            fetch(`/games/${gameid}/minigames/${minigame}`, requestOptions).then(res => {
                resolve(res.json())
            })
            .catch(error => reject(console.error));
          }else{
            fetch(`/games/${gameid}/minigames/1`, requestOptions).then(res => {
                resolve(res.json())
            })
            .catch(error => reject(console.error));
            fetch(`/games/${gameid}/minigames/2`, requestOptions).then(res => {
                resolve(res.json())
            })
            .catch(error => reject(console.error));
            fetch(`/games/${gameid}/minigames/3`, requestOptions).then(res => {
                resolve(res.json())
            })
            .catch(error => reject(console.error));
          }
        })
        .catch(error => reject(console.error));
      })
    })
  }

  return (

    <div className="NewGame-header">
      <p>NEW GAME</p>
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Game's name</Form.Label>
          <Form.Control placeholder="Enter name" onChange={(e) => setGame({ ...game, name: e.target.value })}/>
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Choose minigame</Form.Label>

          <Form.Control as="select" onChange={(e) => setMinigame(parseInt(e.target.value))}>
              <option value='1'>Minigame 1</option>
              <option value='2'>Minigame 2</option>
              <option value='3'>Minigame 3</option>
              <option value='4'>All minigames</option>
          </Form.Control>
        </Form.Group>

        <Button className="Button" size="lg" variant="dark" onClick={() => createGame()}>
          CREATE
        </Button>
      </Form>
    </div>


  );

}

export default NewGame;
