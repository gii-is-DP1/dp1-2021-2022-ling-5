import { faTimes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useState, useEffect } from "react";
import { Container, Button, Row, Col, Modal, Form, Toast, ToastContainer } from "react-bootstrap";
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

    const requestedList: any[] = []
    const [friendship, setFriendship] = useState<any[]>([]);

    useEffect(() => {
        friendshipAPI.getAllFriendshipsByRequested(playerId)
        .then((frs: any[]) => {
            for (let i = 0; i < frs.length; i++) {
                const fr = frs[i];
                if (fr.state === "REQUESTED") {
                    console.log(fr)
                    requestedList.push(fr)
                }
            }setFriendship(requestedList)
            }).catch(err => console.log(err));
    }, [])


    const acceptFriend = async(id: any, username: String) => {
        const data = await userAPI.getPlayerByNickname(username);
        const friendship = {state: "FRIENDS",requester: data, requested:userData};
 
        friendshipAPI.updateFriendship(friendship, playerId, id)
            .then((res) => window.location.href = '/friends')
            .catch((err) => console.log(err)); 
    }

    const rejectFriend = async(id: any) => {
        friendshipAPI.deleteFriendship(id)
            .then((res) => window.location.href = '/friends')
            .catch((err) => console.log(err)); 
    }

    return <Container id="container" className="d-inline-block align-top">
        {friendship.map((e, ind) => (
        <Toast>
        <Toast.Header >
            <i className="fas fa-user-plus"></i>
            <strong className="me-auto">New friend</strong>
            <small>Friendship {ind+1}</small>
        </Toast.Header>
        <Toast.Body>"{e.requester.nickname}" has requested to be friends!</Toast.Body>
        <Button variant="outline-danger" type="button" onClick={() => acceptFriend(ind, e.requester.nickname)}>
            Decline
        </Button>
        <Button variant="dark" type="button" onClick={() => rejectFriend(ind)}>
            Accept
        </Button>
        </Toast>
        ))}
    </Container>
}

export default AceptFriend