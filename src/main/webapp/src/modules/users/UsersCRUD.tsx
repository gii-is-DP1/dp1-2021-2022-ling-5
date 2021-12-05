import { faTrash, faUserEdit, faUserMinus, faUserPlus } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { useEffect, useState } from "react"
import { Button, Col, Container, Modal, Row } from "react-bootstrap"
import EditUser from "./EditUser"

const UsersCRUD = () => {

    const [players, setPlayers] = useState<any[]>([])
    const [modalShow, setModalShow] = useState<string>("0 0");

    const getPlayers = () => {
        const requestOptions = {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        };
        fetch(`http://localhost:8080/api/players`, requestOptions)
            .then(res => {
                res.json().then((pls) => {
                    setPlayers(pls);
                }).catch(e => console.log(e));
            })
            .catch(error => console.log(error));
    }

    const removePlayer = (id: number) => {
        const requestOptions = {
            method: 'DELETE'
        };
        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/players/${id}`, requestOptions)
                .then(res => {
                    window.location.href = '/users'
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    }

    useEffect(() => {
        getPlayers();
    }, [])

    return (

        <Container id="container">
            <div id="createUser" style={{ textAlign: "right" }}>
                <Button variant="dark" onClick={() => window.location.href = '/createUser'}>Create new player <FontAwesomeIcon icon={faUserPlus} /></Button>
            </div>

            {players.map((e, ind) =>
                <Row key={ind}>
                    <Col><h4>{e.nickname}</h4></Col>
                    <Col>
                        <a style={{ cursor: "pointer" }} onClick={() => setModalShow(`1 ${e.id}`)}><FontAwesomeIcon icon={faUserEdit} /></a>&nbsp;&nbsp;
                        <a style={{ cursor: "pointer" }} onClick={() => removePlayer(e.id)}><FontAwesomeIcon icon={faUserMinus} /></a>
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