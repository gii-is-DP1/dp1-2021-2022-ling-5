import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import friendshipAPI from "./friendshipAPI";
import figures from '../../images/figures/figures'
import { faTimes, faUserPlus } from "@fortawesome/free-solid-svg-icons";

const Friends = () => {

    const [playerId, setPlayerId] = useState<number>();
    const [friends, setFriends] = useState<any[]>([]);

    const getFriends = async () => {
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
            const friendsByRequested = await friendshipAPI.getAllFriendshipsByRequested(id);
            const friendsByRequester = await friendshipAPI.getAllFriendshipsByRequester(id);
            friendsByRequested.map((el: any) => {
                if (el.state === "FRIENDS") friendsList.push(el);
            });
            friendsByRequester.map((el: any) => {
                if (el.state === "FRIENDS") friendsList.push(el);
            });
            setFriends(friendsList);
        }
    }
    useEffect(() => {
        getFriends()

    }, [friends])

    const removeFriend = (id: any) => {
        friendshipAPI.deleteFriendship(id)
            .then((res) => window.location.href = '/friends')
            .catch(error => console.log(error));
    }

    if (!friends) return <></>

    return <Container id="container">
        <div id="addFriend" style={{ textAlign: "right" }}>
            <Button className="m-2" variant="dark" onClick={() => window.location.href = '/addFriend'}>Add a Friend <FontAwesomeIcon icon={faUserPlus} /></Button>
        </div>
        <Row>
            <h3>Online </h3>
            {friends.map((el, ind) => {
                if (el.requested.id !== playerId) {
                    if (el.requested.playerState !== "OFFLINE") {
                        return (<Row key={ind}><Col>
                            <h4><img
                                src={el.requested.id !== playerId ? figures(el.requested.figure.id - 1) : figures(el.requester.figure.id - 1)}
                                width="30"
                                height="30"
                                className="d-inline-block align-top"
                                alt="Profile"
                            />&nbsp;&nbsp;{el.requested.id !== playerId ? el.requested.nickname : el.requester.nickname}</h4>
                        </Col>
                            <Col>
                                <h4><Button style={{backgroundColor:"transparent", border:"none", color:"black"}} onClick={() => removeFriend(el.id)} ><FontAwesomeIcon icon={faTimes} /></Button></h4>
                            </Col> </Row>);
                    }
                    return <></>
                }
                if (el.requester.playerState !== "OFFLINE") {
                    return (<Row key={ind}><Col>
                        <h4><img
                            src={el.requested.id !== playerId ? figures(el.requested.figure.id - 1) : figures(el.requester.figure.id - 1)}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                            alt="Profile"
                        />&nbsp;&nbsp;{el.requested.id !== playerId ? el.requested.nickname : el.requester.nickname}</h4>
                    </Col>
                        <Col>
                            <h4><Button style={{backgroundColor:"transparent", border:"none", color:"black"}} onClick={() => removeFriend(el.id)} ><FontAwesomeIcon icon={faTimes} /></Button></h4>
                        </Col> </Row>);
                }
                return <></>
            })}
        </Row>
        <br />
        <Row>
            <h3>Offline</h3>
            {friends.map((el, ind) => {
                if (el.requested.id !== playerId) {
                    if (el.requested.playerState === "OFFLINE") {
                        return (<Row key={ind}><Col>
                            <h4><img
                                src={el.requested.id !== playerId ? figures(el.requested.figure.id - 1) : figures(el.requester.figure.id - 1)}
                                width="30"
                                height="30"
                                className="d-inline-block align-top"
                                alt="Profile"
                            />&nbsp;&nbsp;{el.requested.id !== playerId ? el.requested.nickname : el.requester.nickname}</h4>
                        </Col>
                            <Col>
                                <h4><Button style={{backgroundColor:"transparent", border:"none", color:"black"}} onClick={() => removeFriend(el.id)} ><FontAwesomeIcon icon={faTimes} /></Button></h4>
                            </Col> </Row>);
                    }
                    return <></>
                }
                if (el.requester.playerState === "OFFLINE") {
                    return (<Row key={ind}><Col>
                        <h4><img
                            src={el.requested.id !== playerId ? figures(el.requested.figure.id - 1) : figures(el.requester.figure.id - 1)}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                            alt="Profile"
                        />&nbsp;&nbsp;{el.requested.id !== playerId ? el.requested.nickname : el.requester.nickname}</h4>
                    </Col>
                        <Col>
                            <h4><Button style={{backgroundColor:"transparent", border:"none", color:"black"}} onClick={() => removeFriend(el.id)} ><FontAwesomeIcon icon={faTimes} /></Button></h4>
                        </Col> </Row>);
                }
                return <></>
            })}
        </Row>
    </Container>
}

export default Friends;