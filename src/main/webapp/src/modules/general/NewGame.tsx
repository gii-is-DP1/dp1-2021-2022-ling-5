
import { Form, Button } from 'react-bootstrap';

import './NewGame.css';
import { useEffect, useState } from 'react';
import gameAPI from '../game/gameAPI';
import userAPI from '../user/userAPI';
import token from '../user/token';


function NewGame() {

  const [idPlayer, setIdPlayer] = useState<number>();
  var fechainicio = Date.now;

  const [game, setGame] = useState<any>({
    name: null,
    state: "UNSTARTED",
    startTime: fechainicio,
    endTime: null,
    creator: idPlayer,
  });

  const [minigame, setMinigame] = useState<string>();

  const [gameid, setGameid] = useState<number | undefined>(0);

  function createGame() {
      gameAPI.addNewGame(game).then((gameCreated: any) => {
        setGameid(gameCreated.id);
        if (minigame && gameCreated.id !== undefined) {
          if (minigame !== 'N/A') {
            var mg = parseInt(minigame);
            gameAPI.addNewMinigameToGame(gameCreated.id, mg);
          }
          window.location.href = `/startGame/${gameCreated.id}`;
        }
      }).catch((err) => console.log(err));

  }

  useEffect(() => {
    var userData = localStorage.getItem("userData");
    if (userData !== null) {
      setIdPlayer(JSON.parse(userData).id);
      setGame({ ...game, creator: JSON.parse(userData).id })
    }
  }, [])

  if (!idPlayer) return <></>
  return (

    <div className="NewGame-header">
      <p>NEW GAME</p>
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Game's name</Form.Label>
          <Form.Control placeholder="Enter name" required onChange={(e) => setGame({ ...game, name: e.target.value })} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Choose minigame</Form.Label>

          <Form.Control as="select" required onChange={(e) => setMinigame(e.target.value)}>
            <option value="N/A"> Choose game mode </option>
            <option value='1' >Torre Infernal</option>
            <option value='2'>Foso</option>
            <option value='3'>Regalo Envenenado</option>
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
