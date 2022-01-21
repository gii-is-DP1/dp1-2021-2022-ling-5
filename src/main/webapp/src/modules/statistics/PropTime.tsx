import { useEffect, useState } from "react";
import { Badge, Col, Container, Row } from "react-bootstrap";
import ChartsPage from "./ChartsPage";

const PropTime = () => {
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
        fetch(`http://localhost:8080/api/statistics/propTotalTime/${id}`).then((res: any) => {
            res.json().then((prop: number) => setPropTotal(prop)).catch(console.error);
        }).catch(console.error);
        fetch(`http://localhost:8080/api/statistics/maxMinAvgTime/${id}`).then((res: any) => {
            res.json().then((data: any) => setMinMaxAvg(data)).catch(console.error);
        }).catch(console.error);
        fetch(`http://localhost:8080/api/statistics/maxminavgtimeall`).then((res: any) => {
            res.json().then((data: any) => setMinMaxAvgAll(data)).catch(console.error);
        }).catch(console.error);
    }, [])

    if (playerId === undefined || propTotal === undefined || minMaxAvg === undefined || minMaxAvgAll === undefined) return <></>

    return <Container id="container">
        <h4>Ratio of time played to total: {propTotal}</h4>
        <br />
        <Row >
            <Col ><ChartsPage ratio={propTotal} width="50%" /></Col>
            <Col className="my-auto"> <Badge bg="info"> </Badge> {' '}Time played by me: {propTotal*100} %</Col>

            <Col className="my-auto text-center">
                <h5>Me</h5>
                <strong>Min time</strong>: {minMaxAvg.min}
                <br />
                <strong>Max time</strong>: {minMaxAvg.max}
                <br />
                <strong>Average time</strong>: {minMaxAvg.avg.toFixed(2)}
                <hr />
                <h5>Global</h5>
                <strong>Min time</strong>: {minMaxAvgAll.min}
                <br />
                <strong>Max time</strong>: {minMaxAvgAll.max}
                <br />
                <strong>Average time</strong>: {minMaxAvgAll.avg.toFixed(2)}
            </Col>
        </Row>

    </Container>
}
export default PropTime;