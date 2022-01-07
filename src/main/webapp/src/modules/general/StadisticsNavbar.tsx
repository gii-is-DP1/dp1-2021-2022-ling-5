import { Nav } from "react-bootstrap"
import './principalNavbar.css'

const PrincipalNavbar = () => {
    return <Nav defaultActiveKey="/home" className="flex-column">
        <Nav.Link href="/pointsByMinigame" id="link">Points by minigame</Nav.Link>
        <Nav.Link href="/mostAndLeastUsed" id="link">Most and least used figures</Nav.Link>
        <Nav.Link href="/ranking" id="link">Ranking</Nav.Link>
        <Nav.Link href="/userFrequency" id="link">User frequency</Nav.Link>
        <Nav.Link href="/propGames" id="link">Ratio games played</Nav.Link>
        <Nav.Link href="/propTime" id="link">Ratio time played</Nav.Link>

    </Nav>

}

export default PrincipalNavbar