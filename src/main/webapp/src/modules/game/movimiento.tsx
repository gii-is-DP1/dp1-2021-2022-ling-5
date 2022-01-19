import { useEffect, useState } from "react";

export default function movimiento(figureId: any, card: any) {
    const [playerId, setPlayerId] = useState<number>();
    let res = false
    let figs = card.figures
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
    if (playerId !== 0) {
        for (var fig of figs) {
            res = res || fig.id === figureId
        }
    
        if(res){
            const requestOptions = {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: ''
            }
            fetch(`http://localhost:8080/api/player/${playerId}/figure/${figureId}/add`, requestOptions)
                .then((res: any) => {
                    console.log(`One point added to figure ${figureId} of player ${playerId}`)
                }).catch((err: any) => console.log(err));
        }
    }

  }, [])

    return res;
}