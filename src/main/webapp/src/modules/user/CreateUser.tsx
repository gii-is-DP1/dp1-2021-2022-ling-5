import { useState } from "react"
import { Button, Col, Form, Row } from "react-bootstrap"
import { propTypes } from "react-bootstrap/esm/Image"
import './createUser.css'
import userAPI from "./userAPI"

const EditUser = () => {
    const [player, setPlayer] = useState<any>({
        name: null,
        surname: null,
        password: null,
        email: null,
        nickname: null,
        playerState: "NO_PLAY"
    })

    const newUser = () => {

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }

        userAPI.addNewUser(player, "player").then((pl: any) => {
            fetch('http://localhost:8080/api/players/' + pl.id + '/addfigures', requestOptions)
                .then(res => console.log(res))
                .catch(error => console.log(error));
            window.location.href = '/users';
        }).catch(err => console.log(err));

    }

    return (
        <Form>
            <Row>
                <h3>New player</h3>
                <Col>
                    <Form.Group className="mb-3" controlId="formBasicName">
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" placeholder="Enter name" onChange={(e) => setPlayer({ ...player, name: e.target.value })} />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicSurname">
                        <Form.Label>Surname</Form.Label>
                        <Form.Control type="text" placeholder="Enter surname" onChange={(e) => setPlayer({ ...player, surname: e.target.value })} />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicNickname">
                        <Form.Label>Nickname</Form.Label>
                        <Form.Control type="text" placeholder="Enter nickname" onChange={(e) => setPlayer({ ...player, nickname: e.target.value })} />
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" onChange={(e) => setPlayer({ ...player, email: e.target.value })} />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" onChange={(e) => setPlayer({ ...player, password: e.target.value })} />
                    </Form.Group>
                </Col>
            </Row>
            <Row>
                <div style={{ textAlign: "center" }} >
                    <Button variant="primary" type="button" onClick={() => newUser()}>
                        Submit
                    </Button>
                </div>
            </Row>

        </Form>)
}

export default EditUser