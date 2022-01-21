import { useState } from "react";
import { Container, Button, Form } from "react-bootstrap";
import friendshipAPI from "./friendshipAPI";
import userAPI from "../user/userAPI";

const AddFriend = () => {

    var userData: any = localStorage.getItem("userData");
    if (userData !== null) userData = JSON.parse(userData)
    const playerId = userData.id

    const [username, setUsername] = useState<any>();


    const newFriend = async(event:any) => {

        
        const data = await userAPI.getPlayerByNickname(username);
        
        console.log(data);
        
        const friendship = {state: "REQUESTED",requester: userData, requested:data};

        if (friendship.requested != null) {
            let friendId = friendship.requested.id;
            
            friendshipAPI.addFriendship(friendship, playerId, friendId).then(res =>
                window.location.href = '/friends'
            ).catch(err => console.log(err));

        }
        event.preventDefault();

    }

    return <Container id="container" className="d-inline-block align-top">
        <Form onSubmit={newFriend}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Add a friend</Form.Label>
            <Form.Control type="username" required maxLength={50} minLength={3} placeholder="enter username"  onChange={(e) => setUsername(e.target.value )}/>
        </Form.Group>

        <Button variant="dark" type="submit">
            Send invitation
        </Button>
        </Form>
    </Container>
}

export default AddFriend