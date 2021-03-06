function AddPoints(gameId: any, playerId: any, formData: FormData) {
    const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
        'credentials':'include' as RequestCredentials
    }
    return new Promise(function (resolve, reject) {
        fetch(`http://localhost:8080/api/players/${playerId}/ongoingRegaloEnvenenado/${gameId}/points`,
            requestOptions)
            .then(res => {
                resolve(res.json())
            })
            .catch(error => reject(console.error))
    })
}

export default AddPoints