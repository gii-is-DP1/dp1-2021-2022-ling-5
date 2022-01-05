import { Container, Nav, Navbar, NavDropdown, Button } from "react-bootstrap"
import React, { useEffect, useState } from 'react'
import figures from '../../images/figures/figures'
import icons from '../../images/icons/icons'
import './principalNavbar.css'
import Share from "./Share"
import userAPI from "../user/userAPI"
import token from "../user/token"

const PrincipalNavbar = () => {

    const [modalShow, setModalShow] = useState<boolean>(false);
    const [username, setUsername] = useState<string>("");
    const [figure, setFigure] = useState<number>(0);
    const [role, setRole] = useState<string | null>();
    var id = 0;

    useEffect(() => {
        var userData: any = localStorage.getItem("userData");
        if (userData !== null) userData = JSON.parse(userData)
        id = userData.id
        var rol = localStorage.getItem("rol");
        setRole(rol);
        if (rol !== null && id !== 0) {
            userAPI.getUser(id, rol).then((user: any) => {
                setUsername(user.nickname)
                setFigure(user.figure.id - 1)
            }).catch(err => console.log(err));
        }
    }, [])

    if (!username && !figure && !role) return <></>

    const src = role === "Admin" ? icons(3) : figures(figure);
    const alt = role === "Admin" ? "Dobble logo" : "Profile image";
    const nickname = role === "Admin" ? "Admin" : username;
    const href1 = role === "Admin" ? "/gamesProgress" : "/profile";
    const namehref1 = role === "Admin" ? "Info" : "Profile";

    return <Navbar expand="lg">
        <Container>
            <a href="/" id="img"><img
                src={src}
                width="30"
                height="30"
                className="d-inline-block align-top"
                alt={alt}
            /></a>
            <NavDropdown title={nickname} id="img">
                <NavDropdown.Item href={href1}>{namehref1}</NavDropdown.Item>
                <NavDropdown.Item onClick={() => { token.logout(); window.location.href = "/"; }}>Logout</NavDropdown.Item>
            </NavDropdown>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Nav.Link href="/notifications"><img
                        src={icons(0)}
                        width="30"
                        height="30"
                        className="d-inline-block align-top"
                        alt="Notifications"
                    /></Nav.Link>
                    <Nav.Link href="/forum"><img
                        src={icons(1)}
                        width="30"
                        height="30"
                        className="d-inline-block align-top"
                        alt="Forum"
                    /></Nav.Link>
                    <Nav.Link id="btn-share" onClick={() => setModalShow(true)}><img
                        src={icons(2)}
                        width="30"
                        height="30"
                        className="d-inline-block align-top"
                        alt="Share"
                    /></Nav.Link>
                </Nav>
            </Navbar.Collapse>
            <Share show={modalShow} onHide={() => setModalShow(false)} />
        </Container>
    </Navbar>

}

export default PrincipalNavbar