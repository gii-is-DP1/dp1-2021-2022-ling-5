import { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

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
        fetch(`http://localhost:8080/api/statistics/maxMinAvgTimeAll`).then((res: any) => {
            res.json().then((data: any) => setMinMaxAvgAll(data)).catch(console.error);
        }).catch(console.error);
    }, [])

    if (playerId === undefined || propTotal === undefined || minMaxAvg === undefined || minMaxAvgAll === undefined) return <></>

    return <Container id="container">
        <h4>Ratio of time played to total: {propTotal}</h4>
        <br />
        <Row>
            <Col>
                <h5>Me</h5>
                Min time: {minMaxAvg.min}
                <br />
                Max time: {minMaxAvg.max}
                <br />
                Average time: {minMaxAvg.avg.toFixed(2)}
            </Col>
            <Col>
                <h5>Global</h5>
                Min time: {minMaxAvgAll.min}
                <br />
                Max time: {minMaxAvgAll.max}
                <br />
                Average time: {minMaxAvgAll.avg.toFixed(2)}
            </Col>
        </Row>

    </Container>
}
export default PropTime;