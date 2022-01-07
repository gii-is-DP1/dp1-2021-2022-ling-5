import { Nav } from "react-bootstrap"
import './navbars.css'

const AdminNavbar = () => {
    return <Nav defaultActiveKey="/home" className="flex-column">
        <Nav.Link id="link" href="/gamesProgress">Games in Progress</Nav.Link>
        <Nav.Link id="link" href="/gamesPlayed">Games Played</Nav.Link>
        <Nav.Link id="link" href="/users">Users</Nav.Link>
        <Nav.Link id="link" href="/history">History</Nav.Link>
        <Nav.Link id="link" href="/adminAwards">Awards</Nav.Link>
    </Nav>
}

export default AdminNavbar