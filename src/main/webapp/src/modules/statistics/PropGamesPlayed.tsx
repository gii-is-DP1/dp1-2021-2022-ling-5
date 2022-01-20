import { useEffect, useState } from "react";
import { Badge, Col, Container, Row } from "react-bootstrap";
import ChartsPage from "./ChartsPage";

const PropGamesPlayed = () => {
    const [playerId, setPlayerId] = useState<number>();
    const [propTotal, setPropTotal] = useState<number>();
    const [minMaxAvg, setMinMaxAvg] = useState<any>();
    const [minMaxAvgAll, setMinMaxAvgAll] = useState<any>();

    useEffect(() => {
        var userData: any = localStorage.getItem("userData");
        var id = 0;
        if (userData !== null) {
            userData = JSON.parse(userData);
            if (userData !== null) {
                setPlayerId(userData.id)
                id = userData.id
            }
        }
        if (id !== 0) {
            fetch(`http://localhost:8080/api/statistics/propTotal/${id}`).then((res: any) => {
                res.json().then((prop: number) => setPropTotal(prop)).catch(console.error);
            }).catch(console.error);
            fetch(`http://localhost:8080/api/statistics/maxMinAvg/${id}`).then((res: any) => {
                res.json().then((data: any) => setMinMaxAvg(data)).catch(console.error);
            }).catch(console.error);
            fetch(`http://localhost:8080/api/statistics/maxMinAvgAll`).then((res: any) => {
                res.json().then((data: any) => setMinMaxAvgAll(data)).catch(console.error);
            }).catch(console.error);
        }

    }, [])

    if (playerId === undefined || propTotal === undefined || minMaxAvg === undefined || minMaxAvgAll === undefined) return <></>

    return <Container id="container">
        <h4>Ratio of games played to total number of games played: {propTotal}</h4>
        <br />
        <Row >
            <Col ><ChartsPage ratio={propTotal} width="50%" /></Col>
            <Col className="my-auto"> <Badge bg="info"> </Badge> {' '}Games played by me: {propTotal*100} %</Col>

            <Col className="my-auto text-center">
                <h5>Me</h5>
                <strong>Min points</strong>: {minMaxAvg.min}
                <br />
                <strong>Max points</strong>: {minMaxAvg.max}
                <br />
                <strong>Average points</strong>: {minMaxAvg.avg.toFixed(2)}
                <hr />
                <h5>Global</h5>
                <strong>Min points</strong>: {minMaxAvgAll.min}
                <br />
                <strong>Max points</strong>: {minMaxAvgAll.max}
                <br />
                <strong>Average points</strong>: {minMaxAvgAll.avg.toFixed(2)}
            </Col>
        </Row>
    </Container>
}
export default PropGamesPlayed;