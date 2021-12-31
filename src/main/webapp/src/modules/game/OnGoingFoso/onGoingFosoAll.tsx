import { useEffect, useState } from "react"


function GetAll(){
    return new Promise(function(resolve, reject){
        fetch(`http://localhost:8080/api/ongoingFoso`)
        .then(response=>resolve(response.json()))
        .catch(error => reject(console.error))
    })
}

export default GetAll