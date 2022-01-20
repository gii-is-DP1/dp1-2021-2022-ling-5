import { Form,Button, ListGroup, Modal } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import {  withRouter } from 'react-router-dom';
import gameAPI from '../game/gameAPI';
import userAPI from '../user/userAPI';
import NewOnGoingGame from '../game/OnGoingFoso/OnGoingFosoNew';
import {default as NewTorre} from '../game/OnGoingTorreInfernal/OnGoingTorreInfernalNew';
import {default as NewRegalo} from '../game/OnGoingRegaloEnvenenado/OnGoingRegaloEnvenenadoNew';
import { faUserFriends, faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import './StartGame.css';
import friendshipAPI from '../friendship/friendshipAPI';
import invitationAPI from '../friendship/InvitationAPI';




function StartGame(props: any) {
  const id = props.match.params.id;
  const [players, setPlayers] = useState<any[]>([]);
  const [minigame, setMinigame] = useState<any>();
  const [playerId, setPlayerId] = useState<number>();
  const [username, setUsername] = useState<any>();
  const [friends, setFriends] = useState<any[]>([]);
  const [nameGame, setNameGame] = useState<any>();



  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const getFriends = async () => {
    var userData: any = localStorage.getItem("userData");
    var id = 0;
    if (userData !== null) {
      userData = JSON.parse(userData);
      if (userData !== null) {
        setPlayerId(userData.id)
        id = userData.id
        console.log(id)
      }
    }
    if (id !== 0) {
      const friendsList: any[] = []
      const friendsByRequested = await friendshipAPI.getAllFriendshipsByRequested(id);
      const friendsByRequester = await friendshipAPI.getAllFriendshipsByRequester(id);
      console.log(friendsByRequested);
      console.log(friendsByRequester);
      friendsByRequested.map((el: any) => {
        if (el.state === "FRIENDS") friendsList.push(el);
        });
        console.log(friendsList)
      friendsByRequester.map((el: any) => {
        if (el.state === "FRIENDS") friendsList.push(el);
        });
      setFriends(friendsList);
      console.log(friendsList)      
    }
  }
  
  useEffect(() => {
    getFriends()
    gameAPI.getPlayersByGame(id)
      .then((pls: any[]) => {
        setPlayers(pls);
        for (let i = 0; i < pls.length; i++) {
          var pl = pls[i];
          pl.playerState = "WAITING_TO_PLAY";
          userAPI.updateUser(pl, pl.id, "player");
        }
      }).catch((err) => console.log(err));
    gameAPI.getGameById(id)
      .then((game: any) => {
        setNameGame(game.name);
      }).catch((err) => console.log(err));
  }, [])

  const info = {"gameId": id};

  useEffect(()=>{
    gameAPI.getGameMinigame(id)
    .then((m:any)=>{
      setMinigame(m)
      switch(m.id){
        case 1:
          NewTorre(info)
          .catch((err:any)=>console.log(err));
          break;
        case 2:
          NewOnGoingGame(info)
          .catch((err:any)=>console.log(err));
          break;
        case 3:
          NewRegalo(info)
          .catch((err:any)=>console.log(err));
          break;
      }
    })
    .catch((err)=>console.log(err));
  },[])

  if (!players || !minigame) return <p>Loading...</p>
  const inviteFriend = async () => {
    var userData: any = localStorage.getItem("userData");
    if (userData !== null) userData = JSON.parse(userData)
    const playerId = userData.id

    const data = await userAPI.getPlayerByNickname(username);
    const game = await gameAPI.getGameById(id);
    const event = new Date(Date.now());
    const creationDate = event.toJSON();
    const invitation = {createdDate: creationDate , game: game, requester: userData, requested:data};

    for (let i = 0; i < friends.length; i++) {
      if (friends[i].requester.nickname === username || friends[i].requested.nickname === username){
          invitationAPI.addInvitation(invitation, playerId, data.id).then(res =>
                window.location.href = '/friends'
            ).catch(err => console.log(err));
      }
    }
  }


  if (!players) return <p>Loading...</p>
  return (

    

    <div className="NewGame-header">

      <p>GAME "{nameGame}"</p>

      <Form>
        <div id="startGame" style={{ textAlign: "right" }}>
          <Button  variant="outline-dark" onClick={handleShow}>Invite a friend to join <FontAwesomeIcon icon={faUserFriends} /></Button>
      </div>
        <Form.Group className="mb-3" controlId="formBasicPassword">
          <h2>Waiting for players ...</h2>
        </Form.Group>

        <ListGroup variant="flush">
          <ListGroup.Item>
          {players.map((el, index) =>
            <h4 key={index}>Player {el.id} {el.nickname}</h4>
          )}

        <Button className="Button" size="lg" variant="dark" onClick={()=>window.location.href=`/game/${id}/${minigame.id}`}>
          START
        </Button>
        </ListGroup.Item >
        </ListGroup>
        
      </Form>

      <Button className="Button" size="lg" variant="dark"> START </Button>

      <Modal show={show} onHide={handleClose} >
            <Modal.Header closeButton>            
              <Modal.Title>Invite a friend <FontAwesomeIcon icon={faUserFriends} /></Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form.Group controlId="formBasicEmail">
                  <Form.Label>Enter your friend's username</Form.Label>
                  <Form.Control type="username" placeholder="enter username"  onChange={(e) => setUsername(e.target.value )}/>
              </Form.Group>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="outline-secondary" onClick={handleClose}> Cancel </Button>
                <Button variant="dark" onClick={() => inviteFriend()}> Send invitation </Button>
            </Modal.Footer>
        </Modal>

    </div>


  );
}


export default withRouter(StartGame);
