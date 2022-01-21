function DeleteGame(gameId: any) {
    const requestOptions = {
        method: 'DELETE',
        'credentials':'include' as RequestCredentials
    }
    return new Promise(function (resolve, reject) {
        fetch(`http://localhost:8080/api/ongoingRegaloEnvenenado/${gameId}`, requestOptions)
            .then(res => {
                resolve(res.json())
            })
            .catch(error => reject(console.error))
    })
}

export default DeleteGame