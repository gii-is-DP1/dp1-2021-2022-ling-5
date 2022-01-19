import { Form,Button } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import {  withRouter } from 'react-router-dom';
import gameAPI from '../game/gameAPI';
import userAPI from '../user/userAPI';

function StartGame(props: any) {
  const id = props.match.params.id;
  const [players, setPlayers] = useState<any[]>([]);

  useEffect(() => {
    gameAPI.getPlayersByGame(id)
      .then((pls: any[]) => {
        setPlayers(pls);
        for (let i = 0; i < pls.length; i++) {
          var pl = pls[i];
          pl.playerState = "WAITING_TO_PLAY";
          userAPI.updateUser(pl, pl.id, "player");
        }
      }).catch((err) => console.log(err));
  }, [])

  if (!players) return <p>Loading...</p>
  return (

    <div className="NewGame-header">
      <p>GAME</p>
      <Form>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Waiting for players</Form.Label>

        </Form.Group>

        <div>
          {players.map((el, index) =>
            <p key={index}>Player {el.id} {el.nickname}</p>
          )}

        </div>

        <Button className="Button" size="lg" variant="dark">
          START
        </Button>
      </Form>
    </div>


  );
}


export default withRouter(StartGame);
