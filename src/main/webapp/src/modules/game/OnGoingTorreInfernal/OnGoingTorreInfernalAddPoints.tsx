function AddPoints(gameId: any, playerId: any){
    const requestOptions = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({"points":1}),
        'credentials':'include' as RequestCredentials
    }
    return new Promise(function(resolve, reject){
        fetch(`http://localhost:8080/api/players/${playerId}/ongoingTorreInfernal/${gameId}/points`, 
            requestOptions)
        .catch(error => reject(console.error))
    })
}

export default AddPoints