import { useEffect, useState } from "react";
import userAPI from "../user/userAPI";

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
            userAPI.addOneToFigure(id,figureId);
        }
    }

  }, [])

    return res;
}