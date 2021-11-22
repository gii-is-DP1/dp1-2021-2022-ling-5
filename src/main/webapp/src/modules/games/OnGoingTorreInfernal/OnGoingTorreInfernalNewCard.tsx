import { useEffect, useState } from "react"

function NewCard(gameId: any){
    const[satate, SetState] = useState<any>();
    const requestOptions = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({})
    };
    useEffect(()=>{
        fetch(`http://localhost:8080/api/ongoingTorreInfernal/${gameId}/card`, requestOptions)
        .then(res=>{
            console.log(res.status)
            return res.json()
        })
        .then(data => SetState(data))
        .catch(console.error)
    }, []);
    return (<div></div>)
}

export default NewCard