import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import userAPI from "./userAPI";

const Auditory = () => {
    const [players, setPlayers] = useState<any[]>([])


    useEffect(() => {
        userAPI.getAllUsers("player")
            .then((pls: any[]) => {
                setPlayers(pls);
            }).catch((err) => console.log(err));
    }, [players])

    return (

        <Container id="container">
            <Row>
                <Col className="text-center"><h4>User</h4></Col>
                <Col className="text-center"><h4>Creator</h4></Col>
                <Col className="text-center"><h4>Created Date</h4></Col>
                <Col className="text-center"><h4>Modifier</h4></Col>
                <Col className="text-center"><h4>Modified Date</h4></Col>
            </Row>
            <br />
            {players.map((e, ind) =>
                <Row key={ind}>
                    <Col className="text-center"><p>{e.nickname}</p></Col>
                    <Col className="text-center"><p>{e.creator === null ? 'Populated with data.sql' : e.creator}</p></Col>
                    <Col className="text-center"><p>{e.createdDate === null ? 'Populated with data.sql' : e.createdDate}</p></Col>
                    <Col className="text-center"><p>{e.modifier === null ? 'Populated with data.sql' : e.modifier}</p></Col>
                    <Col className="text-center"><p>{e.lastModifiedDate === null ? 'Populated with data.sql' : e.lastModifiedDate}</p></Col>
                </Row>)}
        </Container>
    )
}
export default Auditory;