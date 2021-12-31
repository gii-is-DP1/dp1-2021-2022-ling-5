import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import userAPI from "../user/userAPI";
import friendshipAPI from "./friendshipAPI";
import figures from '../../images/figures/figures'
import { faCross, faTimes } from "@fortawesome/free-solid-svg-icons";

const Friends = () => {

    const [playerId, setPlayerId] = useState<number>();
    const [friends, setFriends] = useState<any[]>([]);

    useEffect(() => {
        var userData: any = localStorage.getItem("userData");
        var id = 0;
        if (userData !== null) {
            userData = JSON.parse(userData);
            if (userData !== null) {
                setPlayerId(userData.id)
                id = userData.id
            }
        }
        if (id !== 0) {
            const friendsList: any[] = []
            friendshipAPI.getAllFriendshipsByRequester(id)
                .then((frs: any[]) => {
                    for (let i = 0; i < frs.length; i++) {
                        const fr = frs[i];
                        if (fr.state === "FRIENDS") {
                            console.log(fr)
                            friendsList.push(fr)
                        }
                        friendshipAPI.getAllFriendshipsByRequested(id)
                            .then((frs: any[]) => {
                                for (let i = 0; i < frs.length; i++) {
                                    const fr = frs[i];
                                    if (fr.state === "FRIENDS") {
                                        console.log(fr)
                                        friendsList.push(fr)
                                    }
                                }
                                setFriends(friendsList)
                            }).catch(err => console.log(err));
                    }
                }).catch(err => console.log(err));
        }

    }, [])
    if (!friends) return <></>
    return <Container id="container">
        <div id="addFriend" style={{ textAlign: "right" }}>
            <Button variant="dark" >Add a Friend </Button>
        </div>
        <Row>
            <h3>Online</h3>
        </Row>
        <br />
        <Row>
            <h3>Offline</h3>
            {friends.map((el, ind) =>
                <Row key={ind}>

                    <Col>
                        <h4><img
                            src={el.requested.id !== playerId ? figures(el.requested.figure.id - 1) : figures(el.requester.figure.id - 1)}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                            alt="Profile image"
                        />&nbsp;&nbsp;{el.requested.id !== playerId ? el.requested.nickname : el.requester.nickname}</h4>
                    </Col>
                    <Col>
                        <h4><a style={{ cursor: "pointer" }} ><FontAwesomeIcon icon={faTimes} /></a></h4>
                    </Col>
                </Row>)}
        </Row>
    </Container>
}

export default Friends;