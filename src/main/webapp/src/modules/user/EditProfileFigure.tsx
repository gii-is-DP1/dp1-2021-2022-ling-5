import { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import figureImg from '../../images/figures/figures';
import achievementAPI from "../achievement/achievementAPI";
import figureAPI from "../figure/figureAPI";
import userAPI from "./userAPI";


const EditProfileFigure = (props: any) => {

    const [figures, setFigures] = useState<any[]>([])

    const edit = (id: number) => {
        userAPI.updateFigureUser(props.idUser, id, "player");
        props.onChange(id - 1);
    }

    useEffect(() => {
        var userData: any = localStorage.getItem("userData");
        if (userData !== null) userData = JSON.parse(userData)
        const playerId = userData.id
        // figureAPI.getFigureById(2)
        //     .then(data => setFigures(data))
        //     .catch((err) => console.log(err));
        achievementAPI.getAllFigureAchivementsPlayer(playerId)
            .then((f: any[]) => {
                setFigures(f);
            }).catch((err) => console.log(err));
    }, [figures])

    return (
        <Modal
            show={props.show}
            onHide={props.onHide}
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Edit user figure
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>

                {figures.map((el, ind) =>
                    <Button style={{backgroundColor:"transparent", border:"none", color:"black"}} onClick={() => edit(ind + 1)} key={ind}><img src={figureImg(ind)} width="50" height="50" alt="" /></Button>
                )}

            </Modal.Body>
            <Modal.Footer>
                <Button onClick={props.onHide}>Close</Button>
            </Modal.Footer>
        </Modal >
    )
}
export default EditProfileFigure;