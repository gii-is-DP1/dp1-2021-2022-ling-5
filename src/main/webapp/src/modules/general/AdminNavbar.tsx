import { Nav } from "react-bootstrap"
import './navbars.css'

const AdminNavbar = () => {
    return <Nav defaultActiveKey="/home" className="flex-column">
        <Nav.Link id="link" href="/gamesProgressAndPlayed">Games in Progress and Played</Nav.Link>
        <Nav.Link id="link" href="/users">Users</Nav.Link>
        <Nav.Link id="link" href="/history">History</Nav.Link>
        <Nav.Link id="link" href="/adminAwards">Awards</Nav.Link>
    </Nav>
}

export default AdminNavbar