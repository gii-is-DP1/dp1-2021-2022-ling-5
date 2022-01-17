import { useEffect, useState } from "react";
import { Button, Col, Dropdown, DropdownButton, Form, Modal, Row } from "react-bootstrap";
import figures from '../../images/figures/figures';
import achievementAPI from "./achievementAPI";
import EditFigureAward from "./EditFigureAward";


const EditAchievement = (props: any) => {
    const [achievement, setAchievement] = useState<any>({});
    const [figure, setFigure] = useState<number>(0);
    const [modalShow, setModalShow] = useState<boolean>(false);

    const edit = () => {
        console.log(achievement);
        achievementAPI.updateAchievement(achievement, props.idUser)
            .then((res) => window.location.href = '/adminAwards')
            .catch((err) => console.log(err));
    }

    useEffect(() => {
        achievementAPI.getAchievementById(props.idUser)
            .then((pl: any) => {
                setAchievement(pl);
                setFigure(pl.figure.id - 1);
            }).catch((err) => console.log(err));

    }, [props.idUser])

    return (
        <Modal
            show={props.show}
            onHide={props.onHide}
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Edit achievement {achievement ? achievement.nickname : ""}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {achievement ? (<>
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
                            <EditFigureAward idAchievement={achievement.id}
                                show={modalShow}
                                onHide={() => setModalShow(false)} />
                        </Col>
                        <Col>
                            <Form.Group className="mb-3" controlId="formBasicName">
                                <Form.Label>Name</Form.Label>
                                <Form.Control type="text" placeholder={achievement.name} onChange={(e) => setAchievement({ ...achievement, name: e.target.value })} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicDescription">
                                <Form.Label>Description</Form.Label>
                                <Form.Control type="text" placeholder={achievement.description} onChange={(e) => setAchievement({ ...achievement, description: e.target.value })} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicType">
                                <Form.Label>Type of achievement</Form.Label>
                                    <DropdownButton title="Dropdown right" id="dropdown-menu-align-right"  variant="secondary" size="sm" onSelect={(e) => setAchievement({ ...achievement, achievementTypes: e })}>
                                        <Dropdown.Item eventKey="POINTS">Points</Dropdown.Item>
                                        <Dropdown.Item eventKey="POINTSTORRE">Points Torre</Dropdown.Item>
                                        <Dropdown.Item eventKey="POINTSFOSO">Points Foso</Dropdown.Item>
                                        <Dropdown.Item eventKey="POINTSREGALO">Points Regalo</Dropdown.Item>
                                    </DropdownButton>
                                <p className="text-muted">You selected {achievement.achievementTypes}</p>                            
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicRequirement">
                                <Form.Label>Requirement points</Form.Label>
                                <Form.Control type="text" placeholder={achievement.requirement} onChange={(e) => setAchievement({ ...achievement, requirement: e.target.value })} />
                            </Form.Group>


                        </Col>
                    </Row>



                </>) : <p>Loading...</p>}
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={() => edit()}>Submit changes</Button>
                <Button onClick={props.onHide}>Close</Button>
            </Modal.Footer>
        </Modal >
    )


}

export default EditAchievement