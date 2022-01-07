import { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

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
        if (id != 0) {
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
        <Row>
            <Col>
                <h5>Me</h5>
                Min points: {minMaxAvg.min}
                <br />
                Max points: {minMaxAvg.max}
                <br />
                Average points: {minMaxAvg.avg.toFixed(2)}
            </Col>
            <Col>
                <h5>Global</h5>
                Min points: {minMaxAvgAll.min}
                <br />
                Max points: {minMaxAvgAll.max}
                <br />
                Average points: {minMaxAvgAll.avg.toFixed(2)}
            </Col>
        </Row>

    </Container>
}
export default PropGamesPlayed;