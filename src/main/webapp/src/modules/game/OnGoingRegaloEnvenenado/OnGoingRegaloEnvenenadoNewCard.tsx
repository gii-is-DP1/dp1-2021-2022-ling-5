

function NewCard(gameId: any, id:number) {
    const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({playerId: id}),
        'credentials':'include' as RequestCredentials
    };
    return new Promise(function (resolve, reject) {
        fetch(`http://localhost:8080/api/ongoingRegaloEnvenenado/${gameId}/card`, requestOptions)
            .then(res => {
                resolve(res.json())
            })
            .catch(error => reject(console.error))
    })
}

export default NewCard