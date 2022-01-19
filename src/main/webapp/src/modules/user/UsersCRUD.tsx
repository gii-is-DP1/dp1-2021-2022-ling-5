import { faUserEdit, faUserMinus, faUserPlus } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { useEffect, useState } from "react"
import { Button, Col, Container,  Row } from "react-bootstrap"
import EditUser from "./EditUser"
import userAPI from "./userAPI"

const UsersCRUD = () => {

    const [players, setPlayers] = useState<any[]>([])
    const [modalShow, setModalShow] = useState<string>("0 0");

    const removePlayer = (id: number) => {
        userAPI.deleteUser("player", id);
    }

    useEffect(() => {
        userAPI.getAllUsers("player")
            .then((pls: any[]) => {
                setPlayers(pls);
            }).catch((err) => console.log(err));
    }, [players])

    return (

        <Container id="container">
            <div id="createUser" style={{ textAlign: "right" }}>
                <Button variant="dark" onClick={() => window.location.href = '/createUser'}>Create new player <FontAwesomeIcon icon={faUserPlus} /></Button>
            </div>

            {players.map((e, ind) =>
                <Row key={ind}>
                    <Col><h4>{e.nickname}</h4></Col>
                    <Col>
                        <Button style={{backgroundColor:"transparent", border:"none", color:"black"}} onClick={() => setModalShow(`1 ${e.id}`)}><FontAwesomeIcon icon={faUserEdit} /></Button>&nbsp;&nbsp;
                        <Button style={{backgroundColor:"transparent", border:"none", color:"black"}} onClick={() => removePlayer(e.id)}><FontAwesomeIcon icon={faUserMinus} /></Button>
                    </Col>
                </Row>)}
            <EditUser
                idUser={parseInt(modalShow.split(" ")[1])}
                show={parseInt(modalShow.split(" ")[0])}
                onHide={() => setModalShow("0 0")}
            />
        </Container>
    )
}

export default UsersCRUD;