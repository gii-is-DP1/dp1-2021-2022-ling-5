import { useState, useEffect } from "react";
import { Card } from "react-bootstrap";
import figures from "./images/figures/figures";
import icons from "./images/icons/icons";
import ChartsPage from "./modules/playedGames/ChartsPage";
import userAPI from "./modules/user/userAPI";

function Patata () {

    const [username, setUsername] = useState<string>("");
    const [figure, setFigure] = useState<number>(0);
    var id = 0;
    var role = null;

    useEffect(() => {
        var userData: any = localStorage.getItem("userData");
        if (userData !== null) userData = JSON.parse(userData)
        id = userData.id
        role = localStorage.getItem("rol")
        if (role !== null && id !== 0) {
            userAPI.getUser(id, role).then((user: any) => {
                setUsername(user.nickname)
                setFigure(user.figure.id - 1)
            }).catch(err => console.log(err));
        }
    }, [])

    const src = role === "Admin" ? icons(3) : figures(figure);
    const alt = role === "Admin" ? "Dobble logo" : "Profile image";
   

    if (!username && !figure) return <></>
    return (
        
    <div className="container">
        
        <Card>
            <a href="/" id="img"><img
                src={src}
                width="50"
                height="50"
                className="d-inline-block align-top"
                alt={alt}
            /></a>
            <Card.Body>
            <Card.Text>
                Some quick example text to build on the card title and make up the bulk
                of the card's content.
            </Card.Text>
            </Card.Body>
        </Card>
    </div>

);
}

export default Patata;