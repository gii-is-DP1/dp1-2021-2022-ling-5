import { useEffect, useState } from "react";
import FindById from "./OnGoingTorreInfernalById"
import GetCenterCard from "./OnGoingTorreInfernalGetCard";
import GetPlayerCard from "./OnGoingTorreInfernalGetPlayerCard";
import GetPoints from "./OnGoingTorreInfernalGetPoints";


function GetGameTest(){
    const[game, SetGame] = useState<any>();
    useEffect(()=>{
        FindById(1).then(gamel=>{
            SetGame(gamel);
        })
    }, [])
    if(!game){
        return <div>Loading...</div>
    }
    return (
    <div>
        <p>{game.currentCard.name}</p>
    </div>)
}

function GetPlayerCardTest(){
    const[player1card, SetPlayer1Card] = useState<any>();
    useEffect(()=>{
        GetPlayerCard(1, 1).then(card=>{
            SetPlayer1Card(card);
        })
    }, [])
    if(!player1card){
        return <div>Loading...</div>
    }
    return (
        <div>
            <p>Player 1 Card: {player1card.name}</p>
        </div>
    )
}

function GetCardTest(){
    const[centerCard, SetCenterCard] = useState<any>();
    useEffect(()=>{
        GetCenterCard(1).then(card=>{
            SetCenterCard(card);
        })
    }, [])
    if(!centerCard){
        return <div>Loading...</div>
    }
    return (
        <div>
            <p>Center Card: {centerCard.name}</p>
        </div>
    )
}

function GetPointsTest(){
    const[points, SetPoints] = useState<any>();
    useEffect(()=>{
        GetPoints(1, 1).then(ppoints=>{
            SetPoints(ppoints);
        })
    }, [])
    if(!points){
        return <div>Loading...</div>
    }
    return (
        <div>
            <p>Player 1 Points: {points}</p>
        </div>
    )
}

export {GetGameTest, GetPlayerCardTest, GetCardTest, GetPointsTest}