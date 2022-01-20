import { Form,Button } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import {  withRouter } from 'react-router-dom';
import gameAPI from '../game/gameAPI';
import userAPI from '../user/userAPI';
import NewOnGoingGame from '../game/OnGoingFoso/OnGoingFosoNew';

function StartGame(props: any) {
  const id = props.match.params.id;
  const [players, setPlayers] = useState<any[]>([]);
  const [minigame, setMinigame] = useState<any>();
  const [foso, SetFoso] = useState<any>();

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

  useEffect(()=>{
    gameAPI.getGameMinigame(id)
    .then((m: any)=>setMinigame(m))
    .catch((err)=>console.log(err));
  },[])

  const info = {"gameId": id};

  useEffect(()=>{
    NewOnGoingGame(info)
    .then((f:any)=>SetFoso(f))
    .catch((err)=>console.log(err));
  },[])

  if (!players || !minigame) return <p>Loading...</p>
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

        <Button className="Button" size="lg" variant="dark" onClick={()=>window.location.href=`/game/${id}/${minigame.id}`}>
          START
        </Button>
      </Form>
    </div>


  );
}


export default withRouter(StartGame);
