import { Nav } from "react-bootstrap"
import './navbars.css'

const ProfileNavbar = () => {
    return <Nav defaultActiveKey="/home" className="flex-column">
        <Nav.Link id="link" href="/profile">Profile</Nav.Link>
        <Nav.Link id="link" href="/stats">Stats</Nav.Link>
        <Nav.Link id="link" href="/games">Games</Nav.Link>
        <Nav.Link id="link" href="/awards">Awards</Nav.Link>
        <Nav.Link id="link" href="/friends">Friends</Nav.Link>
    </Nav>
}

export default ProfileNavbar