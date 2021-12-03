import { Container, Nav, Navbar, NavDropdown, Button } from "react-bootstrap"
import React, { useEffect, useState } from 'react'
import figures from '../../images/figures/figures'
import icons from '../../images/icons/icons'
import './principalNavbar.css'

const PrincipalNavbar = () => {
    return <Nav defaultActiveKey="/home" className="flex-column">
                    <Nav.Link href="/pointsbyminigamme" id="link">Points by minigame</Nav.Link>
                    <Nav.Link href="/pointsbyminigamme" id="link">Points by minigame</Nav.Link>
            </Nav>

}

export default PrincipalNavbar