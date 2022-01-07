import { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import figureImg from '../../images/figures/figures';
import achievementAPI from "./achievementAPI";


const EditFigureAward = (props: any) => {

    const edit = (id: number) => {
        achievementAPI.updateFigureAchievement(props.idAch, id)
            .then((res) => window.location.href = '/adminAwards')
            .catch((err) => console.log(err));
    }

    return (
        <Modal
            show={props.show}
            onHide={props.onHide}
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Edit figure
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>

                {[...Array(57)].map((el, ind) =>
                    <a style={{ cursor: "pointer" }} onClick={() => edit(ind + 1)} key={ind}><img src={figureImg(ind)} width="50" height="50" alt="" /></a>
                )}

            </Modal.Body>
            <Modal.Footer>
                <Button onClick={props.onHide}>Close</Button>
            </Modal.Footer>
        </Modal >
    )
}
export default EditFigureAward;