import { Container, Nav, Navbar, NavDropdown, Button } from "react-bootstrap"
import React, { useEffect, useState } from 'react'
import figures from '../../images/figures/figures'
import icons from '../../images/icons/icons'
import './principalNavbar.css'
import Share from "./Share"
import userAPI from "../user/userAPI"

const PrincipalNavbar = () => {
    var id = 1;
    var role = "player";

    const [modalShow, setModalShow] = useState<boolean>(false);
    const [username, setUsername] = useState<string>("");
    const [figure, setFigure] = useState<number>(0);

    useEffect(() => {
        userAPI.getUser(id, role).then((user: any) => {
            setUsername(user.nickname)
            setFigure(user.figure.id - 1)
        }).catch(err => console.log(err));
    }, [])

    const src = role === "admin" ? icons(3) : figures(figure);
    const alt = role === "admin" ? "Dobble logo" : "Profile image";
    const nickname = role === "admin" ? "Admin" : username;
    const href1 = role === "admin" ? "/gamesProgress" : "profile";
    const namehref1 = role === "admin" ? "Info" : "Profile";

    if (!username && !figure) return <></>
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
                <NavDropdown.Item href="/logout">Logout</NavDropdown.Item>
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