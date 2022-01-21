function GetPoints(gameId: any, playerId: any) {
    return new Promise(function (resolve, reject) {
        fetch(`http://localhost:8080/api/players/${playerId}/ongoingRegaloEnvenenado/${gameId}/points`)
            .then(response => resolve(response.text()))
            .catch(error => reject(console.error))
    })
}

export default GetPoints