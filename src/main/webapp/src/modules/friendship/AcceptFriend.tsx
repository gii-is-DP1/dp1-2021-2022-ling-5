import { faTimes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useState, useEffect } from "react";
import { Container, Button, Row, Col, Modal, Form } from "react-bootstrap";
import figures from "../../images/figures/figures";
import friendshipAPI from "./friendshipAPI";
import { MDBInput } from "mdbreact";
import { useBootstrapPrefix } from "react-bootstrap/esm/ThemeProvider";
import userAPI from "../user/userAPI";
import { get } from "http";

const AceptFriend = () => {

    var userData: any = localStorage.getItem("userData");
    if (userData !== null) userData = JSON.parse(userData)
    const playerId = userData.id

    const [username, setUsername] = useState<any>();


    const newFriend = async() => {

        
        const data = await userAPI.getPlayerByNickname(username);
        
        console.log(data);
        
        const friendship = {state: "REQUESTED",requester: userData, requested:data};

        if (friendship.requested != null) {
            let friendId = friendship.requested.id;
            
            friendshipAPI.addFriendship(friendship, playerId, friendId).then(res =>
                window.location.href = '/friends'
            ).catch(err => console.log(err));
        }
    }

    return <Container id="container" className="d-inline-block align-top">
        <Form>
        <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Add a friend</Form.Label>
            <Form.Control type="username" placeholder="enter username"  onChange={(e) => setUsername(e.target.value )}/>
        </Form.Group>

        <Button variant="dark" type="button" onClick={() => newFriend()}>
            Send invitation
        </Button>
        </Form>
    </Container>
}

export default AceptFriend