import { useState, useEffect } from "react";
import { Container, Button, Toast } from "react-bootstrap";
import friendshipAPI from "./friendshipAPI";
import userAPI from "../user/userAPI";
import invitationAPI from "./InvitationAPI";

const Notification = () => {

    var userData: any = localStorage.getItem("userData");
    if (userData !== null) userData = JSON.parse(userData)
    const playerId = userData.id

    const requestedList: any[] = []
    const ivitationList: any[] = []
    const [friendship, setFriendship] = useState<any[]>([]);
    const [invitation, setInvitation] = useState<any[]>([]);

    useEffect(() => {
        friendshipAPI.getAllFriendshipsByRequested(playerId)
            .then((frs: any[]) => {
                for (let i = 0; i < frs.length; i++) {
                    const fr = frs[i];
                    if (fr.state === "REQUESTED") {
                        requestedList.push(fr)
                    }
                } setFriendship(requestedList)
            }).catch(err => console.log(err));
        invitationAPI.getAllInvitations()
            .then((inv: any[]) => {
                for (let i = 0; i < inv.length; i++) {
                    const invi = inv[i];
                    if (invi.requester.id.equals(playerId) || (invi.requested.id.equals(playerId))) {
                        ivitationList.push(invi)
                    }
                } setInvitation(ivitationList)
        }).catch(err => console.log(err));
    }, [])


    const acceptFriend = async (id: any, username: String) => {
        const data = await userAPI.getPlayerByNickname(username);
        const newfriendship = { state: "FRIENDS", requester: data, requested: userData };
        console.log(newfriendship);
        if (newfriendship != null) {
            friendshipAPI.updateFriendship(newfriendship, playerId, id)
                .then((res) => window.location.href = '/friends')
                .catch((err) => console.log(err));
        }
    }

    const rejectFriend = async (id: any) => {
        friendshipAPI.deleteFriendship(id)
            .then((res) => window.location.href = '/friends')
            .catch((err) => console.log(err));
    }

    return <Container id="container" >
        {friendship.map((e, ind) => (
            <Toast>
                <Toast.Header >
                    <i className="fas fa-user-plus"></i>
                    <strong className="me-auto">New friend</strong>
                    <small>Friendship {ind + 1}</small>
                </Toast.Header>
                <Toast.Body>"{e.requester.nickname}" has requested to be friends!</Toast.Body>
                <Button variant="outline-danger" type="button" onClick={() => rejectFriend(e.id)}>
                    Decline
                </Button>
                <Button variant="dark" type="button" onClick={() => acceptFriend(e.id, e.requester.nickname)}>
                    Accept
                </Button>
            </Toast>
        ))}
        {invitation.map((e, ind) => (
            <Toast>
                <Toast.Header >
                    <i className="fas fa-user-plus"></i>
                    <strong className="me-auto">You have a new invitation to join a game</strong>
                    <small>{e.createdDate}</small>
                </Toast.Header>
                <Toast.Body>"{e.requester.nickname}" has requested to join the game {e.game.name}</Toast.Body>
            </Toast>
        ))}



    </Container>
}

export default Notification