import { Container, Nav, Navbar, NavDropdown, Button } from "react-bootstrap"
import React, { useEffect, useState } from 'react'
import figures from '../../images/figures/figures'
import icons from '../../images/icons/icons'
import './principalNavbar.css'

const PrincipalNavbar = () => {
    var role = "admin";
    if (role === "player") {
        return <Navbar expand="lg">
            <Container>
                <a href="/"><img
                    src={figures(1)}
                    width="30"
                    height="30"
                    className="d-inline-block align-top"
                    alt="Profile img"
                /></a>
                <NavDropdown title="Player1" id="img">
                    <NavDropdown.Item href="/profile">Profile</NavDropdown.Item>
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
                            alt="React Bootstrap logo"
                        /></Nav.Link>
                        <Nav.Link href="/forum"><img
                            src={icons(1)}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                            alt="React Bootstrap logo"
                        /></Nav.Link>
                        <Nav.Link id="btn-share"><img
                            src={icons(2)}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                            alt="React Bootstrap logo"
                        /></Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    } else if (role === "admin") {
        return <Navbar expand="lg">
            <Container>
                <a href="/" id="img"><img
                    src={icons(3)}
                    width="30"
                    height="30"
                    className="d-inline-block align-top"
                    alt="React Bootstrap logo"
                /></a>
                <NavDropdown title="Admin" id="img">
                    <NavDropdown.Item href="/gamesProgress">Info</NavDropdown.Item>
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
                            alt="React Bootstrap logo"
                        /></Nav.Link>
                        <Nav.Link href="/forum"><img
                            src={icons(1)}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                            alt="React Bootstrap logo"
                        /></Nav.Link>
                        <Nav.Link id="btn-share"><img
                            src={icons(2)}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                            alt="React Bootstrap logo"
                        /></Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    } else {
        return <></>
    }

}

export default PrincipalNavbar