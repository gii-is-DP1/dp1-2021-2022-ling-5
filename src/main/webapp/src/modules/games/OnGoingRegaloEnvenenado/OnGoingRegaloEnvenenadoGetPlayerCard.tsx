

function GetPlayerCard(gameId: any, playerId: any) {
    return new Promise(function (resolve, reject) {
        fetch(`http://localhost:8080/api/players/${playerId}/ongoingRegaloEnvenenado/${gameId}`)
            .then(res => resolve(res.json()))
            .catch(error => reject(console.error))
    })
}

export default GetPlayerCard