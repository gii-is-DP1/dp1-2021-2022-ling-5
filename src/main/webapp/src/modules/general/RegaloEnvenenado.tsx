import { useEffect, useState } from "react";
import { Button, Card, Col, Container, Row } from "react-bootstrap";
import { withRouter } from "react-router-dom";
import figures from "../../images/figures/figures";
import GetPlayerCard from "../game/OnGoingRegaloEnvenenado/OnGoingRegaloEnvenenadoGetPlayerCard";
import FindById from "../game/OnGoingRegaloEnvenenado/OnGoingRegaloEnvenenadoById";
import token from "../user/token";
import gameAPI from "../game/gameAPI";
import GetPoints from "../game/OnGoingRegaloEnvenenado/OnGoingRegaloEnvenenadoGetPoints";
import GetCenterCard from "../game/OnGoingRegaloEnvenenado/OnGoingRegaloEnvenenadoGetCard";
import NewCard from "../game/OnGoingRegaloEnvenenado/OnGoingRegaloEnvenenadoNewCard";
import DeleteGame from "../game/OnGoingRegaloEnvenenado/OnGoingRegaloEnvenenadoDelete";
import "./Foso.css";
import userAPI from "../user/userAPI";

function RegaloEnvenenado(props: any) {
    const gameId = props.match.params.gameId;

    const [regaloEnvenenado, SetRegaloEnvenenado] = useState<any>();
    const [playersCards, setPlayersCards] = useState<any[]>([]);
    const [points, setPoints] = useState<any[]>();
    const [centerCard, setCenterCard] = useState<any>();


    useEffect(() => {
        FindById(gameId)
            .then((f: any) => SetRegaloEnvenenado(f))
            .catch((err: any) => console.log(err));
    }, [regaloEnvenenado])

    useEffect(() => {
        let cards: any[] = [];
        gameAPI.getPlayersByGame(gameId)
            .then((pls: any) => {
                for (let pl of pls) {
                    GetPlayerCard(gameId, pl.id)
                        .then((pc: any) => {
                            let cardItem = { name: pl.nickname, id: pl.id, card: pc };
                            cards.push(cardItem);
                        })
                        .catch((err) => console.log(err));
                }
                setPlayersCards(cards);
            }).catch((err: any) => console.log(err));
        console.log(cards);
    }, [])

    // useEffect(()=>{
    //     GetPlayerCard(gameId, token.getLoggedId())
    //     .then((pc:any)=>setPlayersCards(pc))
    //     .catch((err)=>console.log(err));
    // },[playersCards])

    useEffect(() => {
        gameAPI.getPlayersByGame(gameId)
            .then((ps: any[]) => {
                let points: any[] = [];
                for (let p of ps) {
                    let name = p.nickname;
                    let user = p;
                    user.playerState = "PLAYING";
                    userAPI.updateUser(user, p.id, "player");
                    GetPoints(gameId, p.id)
                        .then((ps: any) => {
                            let point = { name: '', points: 0 };
                            point.name = name;
                            point.points = ps;
                            points.push(point);
                        })
                }
                setPoints(points);
            })
            .catch((err) => console.log(err));
    }, [])

    useEffect(() => {
        GetCenterCard(gameId)
            .then((card: any) => setCenterCard(card))
            .catch((err) => console.log(err));
    }, [centerCard])

    if (!regaloEnvenenado || !playersCards || !points || !centerCard) return <div>Loading...</div>

    let remaininglength = regaloEnvenenado.remainingSize;


    if (remaininglength === 0) {
        window.location.href = '/';

        gameAPI.getPlayersByGame(gameId)
            .then((ps: any[]) => {
                for (let p of ps) {
                    let user = p;
                    user.playerState = "NO_PLAY";
                    userAPI.updateUser(user, p.id, "player");
                }
                setPoints(points);
            })
            .catch((err) => console.log(err));

        DeleteGame(gameId)
            .catch((err) => console.log(err));
    }

    let lcf: any[] = [];
    for (let i = 0; i < centerCard.figures.length; i++) {
        lcf[i] = figures(centerCard.figures[i].id - 1);
    }
    return (
        <Container>
            <Row>
                <p id="ptext">ON GOING REGALO ENVENENADO </p>

                {
                    points.map((p, i) => (
                        <Col key={i} className="align-items-start">
                            <Card ><Card.Body>
                                <div><strong>{p.name}: {p.points}</strong></div>
                            </Card.Body></Card>
                        </Col>

                    ))
                }
            </Row>
            <Row>
                {
                    lcf.map((l, i) => (
                        <Col key={i}>
                            <img src={l} width="100px" alt="logo" />
                        </Col>
                    ))
                }
            </Row>

            {
                playersCards.map((el: any, ind: number) => {

                    return <Row key={ind}><h4>{el.name}</h4>{el.card.figures.map((l: any) => (

                        <Col>
                            {el.id == token.getLoggedId() ? <Button key={l.id} className='btn-warning' disabled={true}>
                                <img src={figures(l.id - 1)} width="100px" alt="logo" />
                            </Button> : <Button key={l.id} className='btn-warning' onClick={() => {
                                let equal = false;
                                for (let figure of centerCard.figures) {
                                    if (figure.id === l.id) {
                                        equal = true;
                                        break;
                                    }
                                }
                                if (equal) {
                                    NewCard(token.getLoggedId(),gameId)
                                    .then(()=>userAPI.addOneToFigure(token.getLoggedId(),l.id))
                                        .catch((err) => console.log(err));
                                    window.location.reload();
                                }
                            }}>
                                <img src={figures(l.id - 1)} width="100px" alt="logo" />
                            </Button>}

                        </Col>

                    ))}</Row>
                })
            }

        </Container>
    );
}

export default withRouter(RegaloEnvenenado);