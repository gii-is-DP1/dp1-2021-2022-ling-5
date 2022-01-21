import { useEffect, useState } from "react";
import { Button, Col, Container, Row, Table } from "react-bootstrap";
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
        <h1 id="ptitle">HISTORY</h1>
        <Table striped bordered hover responsive="md">
        <thead>
            <tr>
            <th>User</th>
            <th>Creator</th>
            <th>Created Date</th>
            <th>Modifier</th>
            <th>Modified Date</th>
            </tr>
        </thead>
        <tbody>
            {players.map((e, ind) => (
                <tr> 
                    <td><p>{e.nickname}</p></td>
                    <td><p>{e.creator === null ? 'Populated with data.sql' : e.creator}</p></td>
                    <td><p>{e.createdDate === null ? 'Populated with data.sql' : e.createdDate}</p></td>
                    <td><p>{e.modifier === null ? 'Populated with data.sql' : e.modifier}</p></td>
                    <td><p>{e.lastModifiedDate === null ? 'Populated with data.sql' : e.lastModifiedDate}</p></td>
                </tr> 
            ))}

        </tbody>
        </Table>
        </Container>
    )
}
export default Auditory;