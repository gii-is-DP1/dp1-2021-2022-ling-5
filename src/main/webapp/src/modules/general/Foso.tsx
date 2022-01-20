import { useEffect, useState } from "react";
import { Button, Col, Row } from "react-bootstrap";
import { withRouter } from "react-router-dom";
import figures from "../../images/figures/figures";
import GetPlayerCard from "../game/OnGoingFoso/OnGoingFosoGetPlayerCard";
import FindById from "../game/OnGoingFoso/OnGoingFosoById";
import token from "../user/token";
import gameAPI from "../game/gameAPI";
import GetPoints from "../game/OnGoingFoso/OnGoingFosoGetPoints";
import GetCenterCard from "../game/OnGoingFoso/OnGoingFosoGetCard";
import NewCard from "../game/OnGoingFoso/OnGoingFosoChangeCard";
import DeleteGame from "../game/OnGoingFoso/OnGoingFosoDelete";


function Foso(props:any){
    const gameId = props.match.params.gameId;

    const[foso, SetFoso] = useState<any>();
    const[playerCard, setPlayerCard] = useState<any>();
    const[points, setPoints] = useState<any[]>();
    const[centerCard, setCenterCard] = useState<any>();

    useEffect(()=>{
        FindById(gameId)
        .then((f:any)=>SetFoso(f))
        .catch((err)=>console.log(err));
    },[foso])

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

    if(!foso || !playerCard || !points || !centerCard) return <div>Loading...</div>

    let remaininglength = foso.remainingSize;

    if(remaininglength===0){
        window.location.href = '';
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
                {
                    points.map(p=>(
                        <p>{p.name}: {p.points}</p>
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
                                        NewCard(gameId, token.getLoggedId())
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

export default withRouter(Foso);