
import Button from 'react-bootstrap/Button';
import { Form } from 'react-bootstrap';

import './NewGame.css';
import { useEffect, useState } from 'react';


function NewGame() {

  var idplayer = 1;
  var fechainicio = Date.now;

  const [game, setGame] = useState<any>({
    name: null,
    state: "UNSTARTED",
    startTime: fechainicio,
    endTime: null,
    creator: idplayer,
  });

  const [minigame, setMinigame] = useState<String>();

  const [gameid, setGameid] = useState<number | undefined>(0);

  function createGame() {

    console.log("MINIGAME: " + minigame);

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(game)
    }
    return new Promise(function (resolve, reject) {
      fetch(`http://localhost:8080/api/games`, requestOptions)
        .then(res => {
          res.json().then((gameCreated: any) => {
            setGameid(gameCreated.id);
            const requestOptions = {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' }
            }
            if (minigame && gameCreated.id !== undefined) {
              console.log("hola")
              if (minigame !== '4' && minigame !== 'N/A') {
                fetch(`http://localhost:8080/api/games/${gameCreated.id}/minigames/${minigame}`, requestOptions).then(res => {
                  resolve(res.json())
                })
                  .catch(error => reject(console.error));
              } else {
                fetch(`http://localhost:8080/api/games/${gameCreated.id}/minigames/1`, requestOptions).then(res => {
                  resolve(res.json())
                })
                  .catch(error => reject(console.error));
                fetch(`http://localhost:8080/api/games/${gameCreated.id}/minigames/2`, requestOptions).then(res => {
                  resolve(res.json())
                })
                  .catch(error => reject(console.error));
                fetch(`http://localhost:8080/api/games/${gameCreated.id}/minigames/3`, requestOptions).then(res => {
                  resolve(res.json())
                })
                  .catch(error => reject(console.error));
              }
              window.location.href = `/startGame/${gameCreated.id}`
            }
          }).catch(error => console.log(error))
        }).catch(error => reject(console.error));
    })
  }


  return (

    <div className="NewGame-header">
      <p>NEW GAME</p>
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Game's name</Form.Label>
          <Form.Control placeholder="Enter name" onChange={(e) => setGame({ ...game, name: e.target.value })} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Choose minigame</Form.Label>

          <Form.Control as="select" onChange={(e) => setMinigame(e.target.value)}>
            <option value="N/A"> Choose game mode </option>
            <option value='1' >Minigame 1</option>
            <option value='2'>Minigame 2</option>
            <option value='3'>Minigame 3</option>
            <option value='4'>All minigames</option>
          </Form.Control>
        </Form.Group>

        <Button className="Button" size="lg" variant="dark" onClick={() => createGame()}>
          CREATE
        </Button>

        {gameid === undefined ? <p>This name already exists!</p> : <></>}
      </Form>
    </div>


  );

}

export default NewGame;
