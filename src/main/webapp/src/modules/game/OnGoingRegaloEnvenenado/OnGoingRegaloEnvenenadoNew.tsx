
function NewOnGoingGame(formData: any) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
        'credentials':'include' as RequestCredentials
    }
    return new Promise(function (resolve, reject) {
        fetch(`http://localhost:8080/api/ongoingRegaloEnvenenado`, requestOptions)
            .then(res => {
                resolve(res.json())
            })
            .catch(error => reject(console.error))
    })
}

export default NewOnGoingGame