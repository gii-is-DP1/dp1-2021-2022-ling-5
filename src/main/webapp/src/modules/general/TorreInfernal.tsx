import { useEffect, useState } from "react";
import { Button, Card, Col, Row } from "react-bootstrap";
import { withRouter } from "react-router-dom";
import figures from "../../images/figures/figures";
import GetPlayerCard from "../game/OnGoingTorreInfernal/OnGoingTorreInfernalGetPlayerCard";
import FindById from "../game/OnGoingTorreInfernal/OnGoingTorreInfernalById";
import token from "../user/token";
import gameAPI from "../game/gameAPI";
import GetPoints from "../game/OnGoingTorreInfernal/OnGoingTorreInfernalGetPoints";
import GetCenterCard from "../game/OnGoingTorreInfernal/OnGoingTorreInfernalGetCard";
import NewCard from "../game/OnGoingTorreInfernal/OnGoingTorreInfernalNewCard";
import DeleteGame from "../game/OnGoingTorreInfernal/OnGoingTorreInfernalDelete";
import "./Foso.css";
import userAPI from "../user/userAPI";

function TorreInfernal(props:any){
    const gameId = props.match.params.gameId;

    const[torre, setTorre] = useState<any>();
    const[playerCard, setPlayerCard] = useState<any>();
    const[points, setPoints] = useState<any[]>();
    const[centerCard, setCenterCard] = useState<any>();


    useEffect(()=>{
        FindById(gameId)
        .then((f:any)=>setTorre(f))
        .catch((err)=>console.log(err));
    },[torre])

    useEffect(()=>{
        GetPlayerCard(gameId, token.getLoggedId())
        .then((pc:any)=>setPlayerCard(pc))
        .catch((err)=>console.log(err));
    },[playerCard])

    useEffect(()=>{
        gameAPI.getPlayersByGame(gameId)
        .then((ps:any[])=>{
            let points:any[] = [];
            for(let p of ps){
                let name = p.nickname;
                let user = p;
                user.playerState = "PLAYING";
                userAPI.updateUser(user, p.id, "player");
                GetPoints(gameId, p.id)
                .then((ps:any)=>{
                    let point = {name:'',points:0};
                    point.name = name;
                    point.points = ps;
                    points.push(point);
                })
            }
            setPoints(points);
        })
        .catch((err)=>console.log(err));
    }, [])

    useEffect(()=>{
        GetCenterCard(gameId)
        .then((card:any)=>setCenterCard(card))
        .catch((err)=>console.log(err));
    }, [centerCard])

    if(!torre || !playerCard || !points || !centerCard) return <div>Loading...</div>

    let remaininglength = torre.remainingSize;

    if(remaininglength===0){
        window.location.href = '/';

        gameAPI.getPlayersByGame(gameId)
        .then((ps:any[])=>{
            for(let p of ps){
                let user = p;
                user.playerState = "NO_PLAY";
                userAPI.updateUser(user, p.id, "player");
            }
            setPoints(points);
        })
        .catch((err)=>console.log(err));
        
        DeleteGame(gameId)
        .catch((err)=>console.log(err));
    }

    let lcf:any[] = [];
    for(let i=0;i<centerCard.figures.length;i++){
        lcf[i] = figures(centerCard.figures[i].id-1);
    }
    return(
        <div>
            <Row>
                <p id="ptext">ON GOING TORRE INFERNAL</p>

                {
                    points.map(p=>(
                        <Col className="align-items-start">
                        <Card ><Card.Body>
                            <div><strong>{p.name}: {p.points}</strong></div>
                            </Card.Body></Card>
                        </Col>
                        
                    ))
                }
            </Row>
            <Row>
                {
                    lcf.map(l=>(
                        <Col>
                            <img src={l} width="100px" alt="logo"/>
                        </Col>
                    ))
                }
            </Row>
            <Row>
                {
                    playerCard.figures.map((l:any)=>(
                        <Col>
                            <Button onClick={()=>{
                                    let equal = false;
                                    for(let figure of centerCard.figures){
                                        if(figure.id===l.id){
                                            equal = true;
                                            break;
                                        }
                                    }
                                    if(equal){
                                        NewCard(token.getLoggedId(),gameId)
                                        .then(()=>userAPI.addOneToFigure(token.getLoggedId(),l.id))
                                        .catch((err)=>console.log(err));
                                    }
                                }}>
                                <img src={figures(l.id-1)} width="100px" alt="logo" />
                            </Button>
                        </Col>
                    ))
                }
            </Row>
        </div>
    );
}

export default withRouter(TorreInfernal);