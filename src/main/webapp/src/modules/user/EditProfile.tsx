import { useEffect, useState } from "react";
import { Button, Col, Form, Modal, Row } from "react-bootstrap";
import figures from '../../images/figures/figures';
import EditProfileFigure from "./EditProfileFigure";
import userAPI from "./userAPI";

const EditProfile = (props: any) => {
    const [player, setPlayer] = useState<any>({});
    const [figure, setFigure] = useState<number>(0);
    const [modalShow, setModalShow] = useState<boolean>(false);

    function handleChange(newFigure: number) {
        setFigure(newFigure);
        setModalShow(false);
    }

    const edit = (event:any) => {
        console.log(player);
        userAPI.updateUser(player, props.idUser, "player")
            .then(props.onHide)
            .catch((err) => console.log(err));
        event.preventDefault();
    }

    useEffect(() => {
        userAPI.getUser(props.idUser, "player")
            .then((pl: any) => {
                setPlayer(pl);
                setFigure(pl.figure.id - 1);
            }).catch((err) => console.log(err));

    }, [props.idUser])

    return (
        <Form onSubmit={edit}>
        <Modal
            show={props.show}
            onHide={props.onHide}
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Edit user {player ? player.nickname : ""}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {player ? (<>
                    <Row>
                        <Col style={{ textAlign: "center" }}>
                            <img
                                src={figures(figure)}
                                width="100vh"
                                height="100vh"
                                className="d-inline-block align-top"
                                alt="Profile img"
                            />
                            <Button onClick={() => setModalShow(true)}>Change figure</Button>
                            <EditProfileFigure idUser={player.id}
                                figure={figure}
                                onChange={handleChange}
                                show={modalShow}
                                onHide={() => setModalShow(false)} />
                        </Col>
                        <Col>
                            <Form.Group className="mb-3" controlId="formBasicName">
                                <Form.Label>Name</Form.Label>
                                <Form.Control type="text" required minLength={3} maxLength={50} placeholder={player.name} onChange={(e) => setPlayer({ ...player, name: e.target.value })} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicSurname">
                                <Form.Label>Surname</Form.Label>
                                <Form.Control type="text" required minLength={3} maxLength={50} placeholder={player.surname} onChange={(e) => setPlayer({ ...player, surname: e.target.value })} />
                            </Form.Group>
                        </Col>
                    </Row>



                </>) : <p>Loading...</p>}
            </Modal.Body>
            <Modal.Footer>
                <Button type="submit">Submit changes</Button>
                <Button onClick={props.onHide}>Close</Button>
            </Modal.Footer>
        </Modal >
        </Form>
    )


}

export default EditProfile