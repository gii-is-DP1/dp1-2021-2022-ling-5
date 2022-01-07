

function GetCenterCard(gameId: any){
    return new Promise(function(resolve, reject){
        fetch(`http://localhost:8080/api/ongoingTorreInfernal/${gameId}/card`)
        .then(res=>resolve(res.json()))
        .catch(error=>reject(console.error))
    })
}

export default GetCenterCard