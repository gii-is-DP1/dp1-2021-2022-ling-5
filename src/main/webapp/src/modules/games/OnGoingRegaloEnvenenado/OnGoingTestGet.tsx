import { useEffect, useState } from "react";
import FindById from "./OnGoingRegaloEnvenenadoById"
import GetCenterCard from "./OnGoingRegaloEnvenenadoGetCard";
import GetPlayerCard from "./OnGoingRegaloEnvenenadoGetPlayerCard";
import GetPoints from "./OnGoingRegaloEnvenenadoGetPoints";


function GetGameTest() {
    const [game, SetGame] = useState<any>();
    useEffect(() => {
        FindById(1).then(gamel => {
            SetGame(gamel);
        })
    }, [])
    if (!game) {
        return <div>Loading...</div>
    }
    return (
        <div>
            <p>{game.currentCard.name}</p>
        </div>)
}

function GetPlayerCardTest() {
    const [playersCard, SetPlayersCard] = useState<any[]>([]);
    useEffect(() => {
        FindById(1).then((game1: any) => {
            const players: any[] = game1.players;
            for (let index = 0; index < players.length; index++) {
                const element: any = players[index];
                GetPlayerCard(1, element.id).then(card => {
                    const cards: any[] = playersCard;
                    cards.push(card);
                    SetPlayersCard(cards);
                })
            }
        })
    }, [])
    if (!playersCard) {
        return <div>Loading...</div>
    }
    return (
        <div>
            {[...Array(playersCard.length)].map((el, index) => {
                return <p key={playersCard[index]}>Player {index} Card: {playersCard[index].name}</p>
            })}

        </div>
    )
}

function GetCardTest() {
    const [centerCard, SetCenterCard] = useState<any>();
    useEffect(() => {
        GetCenterCard(1).then(card => {
            SetCenterCard(card);
        })
    }, [])
    if (!centerCard) {
        return <div>Loading...</div>
    }
    return (
        <div>
            <p>Center Card: {centerCard.name}</p>
        </div>
    )
}

function GetPointsTest() {
    const [points, SetPoints] = useState<any>();
    useEffect(() => {
        GetPoints(1, 1).then(ppoints => {
            SetPoints(ppoints);
        })
    }, [])
    if (!points) {
        return <div>Loading...</div>
    }
    return (
        <div>
            <p>Player 1 Points: {points}</p>
        </div>
    )
}

export { GetGameTest, GetPlayerCardTest, GetCardTest, GetPointsTest }