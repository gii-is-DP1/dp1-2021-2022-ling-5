import { faEdit, faPlus, faTrash, faUserEdit, faUserMinus, faUserPlus } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { useEffect, useState } from "react"
import { Button, Col, Container, Modal, Row } from "react-bootstrap"
import achievementAPI from "./achievementAPI"
import EditAchievement from "./EditAchievement"


const AdminAchievement = () => {

    const [achievements, setAchievements] = useState<any[]>([])
    const [modalShow, setModalShow] = useState<string>("0 0");

    const removeAchievement= (id: number) => {
        achievementAPI.deleteAchievement(id)
            .then((res) => window.location.href = '/adminAwards')
            .catch(error => console.log(error));
    }

    useEffect(() => {
        achievementAPI.getAllAchievements()
            .then((ach: any[]) => {
                setAchievements(ach);
            }).catch((err) => console.log(err));
    }, [])

    return (

        <Container id="container">
            <div id="createUser" style={{ textAlign: "right" }}>
                <Button variant="dark" onClick={() => window.location.href = '/createAchievement'}>Create new achievement <FontAwesomeIcon icon={faPlus} /></Button>
            </div>

            {achievements.map((e, ind) =>
                <Row key={ind}>
                    <Col><h4>{e.name}</h4></Col>
                    <Col>
                        <a style={{ cursor: "pointer" }} onClick={() => setModalShow(`1 ${e.id}`)}><FontAwesomeIcon icon={faEdit} /></a>&nbsp;&nbsp;
                        <a style={{ cursor: "pointer" }} onClick={() => removeAchievement(e.id)}><FontAwesomeIcon icon={faTrash} /></a>
                    </Col>
                </Row>)}
            <EditAchievement
                idUser={parseInt(modalShow.split(" ")[1])}
                show={parseInt(modalShow.split(" ")[0])}
                onHide={() => setModalShow("0 0")}
            />
        </Container>
    )
}

export default AdminAchievement