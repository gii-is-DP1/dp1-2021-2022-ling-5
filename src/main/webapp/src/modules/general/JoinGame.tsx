
import Button from 'react-bootstrap/Button';
import { Form } from 'react-bootstrap';

import './NewGame.css';
import { useState } from 'react';
import gameAPI from '../game/gameAPI';


function JoinGame() {
  const [namegame, setNamegame] = useState<string>();
  const [game, setGame] = useState<String | null>();
  var idplayer = 2;

  function joinGame() {
    if (namegame) {
      gameAPI.getGameByName(namegame)
        .then((game: any) => {
          setGame(game);
          if (game !== null) {
            gameAPI.addNewPlayerToGame(game.id, idplayer)
              .then((res) => window.location.href = `/startGame/${game.id}`)
              .catch(err => console.log(err));
          }
        }).catch((err) => console.log(err));
    }
  }

  return (

    <div className="NewGame-header">
      <p>JOIN GAME</p>
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Game's name you want to join</Form.Label>
          <Form.Control placeholder="Enter game's name" onChange={(e) => setNamegame(e.target.value)} />
        </Form.Group>


        <Button className="Button" size="lg" variant="dark" onClick={() => joinGame()}>
          JOIN
        </Button>

        {game === null ? <p>This game doesn't exist</p> : ""}
      </Form>
    </div>


  );
}


export default JoinGame;
