import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap"
import React, { useEffect, useState } from 'react'
import figures from './images/figures/figures'
import icons from './images/icons/icons'
import Button from "@restart/ui/esm/Button"

const PrincipalNavbar = () => {
    return <Navbar bg="light" expand="lg">
        <Container>
            <a href="/"><img
                src={figures(1)}
                width="30"
                height="30"
                className="d-inline-block align-top"
                alt="React Bootstrap logo"
            /></a>
            <NavDropdown title="Player1">
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
                    <Nav.Link><Button id="btn-share"><img
                        src={icons(2)}
                        width="30"
                        height="30"
                        className="d-inline-block align-top"
                        alt="React Bootstrap logo"
                    /></Button></Nav.Link>
                </Nav>
            </Navbar.Collapse>
        </Container>
    </Navbar>
}

export default PrincipalNavbar