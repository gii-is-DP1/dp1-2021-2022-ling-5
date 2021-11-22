import { useEffect, useState } from "react"

function loginUser(formData:FormData){
    const[state, SetState] = useState<any>();
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
    };
    useEffect(()=>{
        fetch(`http://localhost:8080/api/login`, requestOptions)
        .then(res=>{
            console.log(res.status)
            return res.json()
        })
        .then(data => SetState(data))
        .catch(console.error)
    }, []);
    return (<div></div>)
}

export default loginUser