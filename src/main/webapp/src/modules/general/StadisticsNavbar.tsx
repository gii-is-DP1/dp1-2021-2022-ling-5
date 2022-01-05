import { Nav } from "react-bootstrap"
import './principalNavbar.css'

const PrincipalNavbar = () => {
    return <Nav defaultActiveKey="/home" className="flex-column">
        <Nav.Link href="/pointsbyminigamme" id="link">Points by minigame</Nav.Link>
        <Nav.Link href="/mostandleastused" id="link">Most and least used figures</Nav.Link>
        <Nav.Link href="/ranking" id="link">Ranking</Nav.Link>
        <Nav.Link href="/userfrequency" id="link">User frequency</Nav.Link>

    </Nav>

}

export default PrincipalNavbar