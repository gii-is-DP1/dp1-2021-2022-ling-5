import { faTimes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useState, useEffect } from "react";
import { Container, Button, Row, Col, Modal, Form } from "react-bootstrap";
import figures from "../../images/figures/figures";
import friendshipAPI from "./friendshipAPI";
import { MDBInput } from "mdbreact";

const AddFriend = () => {

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
    return <Container id="container" className="d-inline-block align-top">
        <Form>
        <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Add a friend</Form.Label>
            <Form.Control type="email" placeholder="Enter username" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Add a message!</Form.Label>
            <MDBInput type="textarea" outline />
        </Form.Group>
        <Button variant="dark" type="submit">
            Send invitation
        </Button>
        </Form>
    </Container>
}

export default AddFriend;