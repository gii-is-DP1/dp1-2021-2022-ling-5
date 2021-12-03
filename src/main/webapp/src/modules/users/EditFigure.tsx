import { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import figureImg from '../../images/figures/figures';


const EditFigure = (props: any) => {

    const edit = (id: number) => {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' }
        };
        fetch(`http://localhost:8080/api/figures/${id}/players/${props.idUser}`, requestOptions)
            .then(res => {
                window.location.href = '/users';
            })
            .catch(error => console.log(error));
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
export default EditFigure;