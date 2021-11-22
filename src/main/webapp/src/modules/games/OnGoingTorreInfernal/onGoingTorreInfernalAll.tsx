import { useEffect, useState } from "react"


function GetAll(){
    const[state, SetState] = useState<any>();
    useEffect(()=>{
        fetch(`http://localhost:8080/api/ongoingTorreInfernal`)
        .then(res=>{
            console.log(res.status)
            return res.json()
        })
        .then(data => SetState(data))
        .catch(console.error)
    }, [])
    if(!state) return <div>Loading...</div>
        let games = []
        for(let i=0; i<state.length; i++){
            games[i] = state[i]
        }
    if(games.length==0){
        return "No games on going";
    } else{
        return games;
    }
}

export default GetAll