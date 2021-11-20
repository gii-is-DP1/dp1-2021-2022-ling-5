import { Nav } from "react-bootstrap"

const ProfileNavbar = () => {
    return <Nav defaultActiveKey="/home" className="flex-column">
        <Nav.Link href="/profile">Profile</Nav.Link>
        <Nav.Link href="/stats">Stats</Nav.Link>
        <Nav.Link href="/games">Games</Nav.Link>
        <Nav.Link href="/awards">Awards</Nav.Link>
        <Nav.Link href="/friends">Friends</Nav.Link>
    </Nav>
}

export default ProfileNavbar