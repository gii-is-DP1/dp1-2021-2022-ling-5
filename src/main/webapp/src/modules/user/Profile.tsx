import { useState, useEffect } from "react";
import { Button, Card, Col, Modal, Row } from "react-bootstrap";
import figures from "../../images/figures/figures";
import icons from "../../images/icons/icons";
import userAPI from "./userAPI";
import './Profile.css';
import EditProfile from "./EditProfile";

function Profile(){

    const [username, setUsername] = useState<string>("");
    const [name, setName] = useState<string>("");
    const [surname, setSurname] = useState<string>("");
    const [email, setEmail] = useState<String>("");
    const [figure, setFigure] = useState<number>(0);
    const [id, setId] = useState<number>();

    const [modalShow, setModalShow] = useState<string>("0 0");

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const removePlayer = () => {
        var userData: any = localStorage.getItem("userData");
        if (userData !== null) userData = JSON.parse(userData)
        const id = userData.id
        userAPI.deleteUser("player", id).then(() => {
            localStorage.removeItem("sessionId");
            localStorage.removeItem("rol");
            localStorage.removeItem("userData");
            window.location.href = '/';
        }).catch(err => console.log(err));;
    }
    
    var role = null;
    useEffect(() => {
        var idStr = localStorage.getItem("sessionId");
        var role = localStorage.getItem("rol")

        if (idStr && idStr !== null && role !== null) {
            setId(parseInt(idStr))
            if (id) {
                userAPI.getUser(id, role).then((user: any) => {
                    setUsername(user.nickname)
                    setName(user.name)
                    setSurname(user.surname)
                    setEmail(user.email)
                    setFigure(user.figure.id - 1)
                }).catch(err => console.log(err));
            }
        }
    }, [id])

    const src = role === "Admin" ? icons(3) : figures(figure);
    const alt = role === "Admin" ? "Dobble logo" : "Profile image";

    if (!username && !figure && !id) return <></>
    
    return(
    
    <div className="container m-3">
        <Row>
        <Col>
            <Card>
            <Card.Body>
                <div className="d-flex m-3 ">
                    <a href="/" id="img"><img src={src} width="150" height="150" className=" " alt={alt}/></a>
                    <div className="m-3">
                        <h4 className="text-center">{username}</h4>
                        <p className="text-muted font-size-sm">Name: {name}</p>
                        <p className="text-muted font-size-sm">Surname: {surname}</p>
                        <p className="text-muted font-size-sm">Nickname: {username}</p>
                        <p className="text-muted font-size-sm">Email: {email}</p>
                        <button className="btn btn-outline-danger m-1" onClick={handleShow} >Delete account</button>
                        <button className="btn btn-secondary m-1" onClick={() => setModalShow(`1 ${id}`)}>Edit profile </button>
                    </div>
                </div>
            </Card.Body>
            </Card>
        </Col>
        </Row>

        <EditProfile
                idUser={parseInt(modalShow.split(" ")[1])}
                show={parseInt(modalShow.split(" ")[0])}
                onHide={() => setModalShow("0 0")}
            />

        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton></Modal.Header>
            <Modal.Body>Are you sure you want to delete your account?</Modal.Body>
            <Modal.Footer>
                <Button variant="outline-danger" onClick={() => removePlayer()}> Delete account </Button>
                <Button variant="secondary" onClick={handleClose}> Cancel </Button>
            </Modal.Footer>
        </Modal>
    </div>

    );
}

export default Profile;