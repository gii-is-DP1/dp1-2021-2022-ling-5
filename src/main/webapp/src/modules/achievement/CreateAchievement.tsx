import { useState } from "react"
import { Button, Col, Dropdown, DropdownButton, Form, Modal, Row } from "react-bootstrap"
import figureImg from "../../images/figures/figures"
import achievementAPI from "./achievementAPI"


const CreateAchievement = () => {
 

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [figureId, setFigureId] = useState<any>('...');

    const [achievement, setAchievement] = useState<any>({
        name: null,
        description: null,
        achievementTypes: null,
        requirement: null
    })

    const newAchievement = (event:any) => {

        if (achievement.achievementTypes != null ) {
            console.log(achievement);

            achievementAPI.createAchievementWithFigure(achievement, figureId-1).then(res =>
                window.location.href = '/adminAwards'
            ).catch(err => console.log(err));
        }
        event.preventDefault();
    }

    return (
        <Form onSubmit={newAchievement}>
            <Row>
                 <h3>New achievement</h3>
                <Col>
                    <Form.Group className="mb-3" controlId="formBasicName">
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" placeholder="Enter name" onChange={(e) => setAchievement({ ...achievement, name: e.target.value })} />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicDescription">
                        <Form.Label>Description</Form.Label>
                        <Form.Control type="text" placeholder="Enter description" onChange={(e) => setAchievement({ ...achievement, description: e.target.value })} />
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
                        <Form.Control type="text" placeholder="Enter number of requirement" onChange={(e) => setAchievement({ ...achievement, requirement: e.target.value })} />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicNickname">
                        <Form.Label>Figure won</Form.Label>
                        <div><Button variant="secondary" onClick={handleShow}>
                            Select figure
                        </Button></div>

                        <Modal show={show} onHide={handleClose}>
                            <Modal.Header closeButton>
                            <Modal.Title>Figures</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                {[...Array(57)].map((el, ind) =>
                                    <Button style={{backgroundColor:"transparent", border:"none"}} onClick={() => setFigureId(ind + 1)} key={ind}><img src={figureImg(ind)} width="50" height="50" alt="" /></Button>
                                )}
                            </Modal.Body>
                            <Modal.Footer>
                            <Button variant="secondary" onClick={handleClose}>
                                Close
                            </Button>
                            <Button variant="primary" onClick={handleClose}>
                                Save Changes
                            </Button>
                            </Modal.Footer>
                        </Modal>
                        <p className="text-muted">You selected figure {figureId-1}: <img src={figureImg(figureId-1)} width="50" height="50" alt="" /></p>                       
                    </Form.Group>
                    
                </Col>
            </Row>
            <Row>
                <div style={{ textAlign: "center" }} >
                    <Button variant="dark" type="submit">
                        Submit
                    </Button>
                </div>
            </Row>

        </Form>)
}

export default CreateAchievement