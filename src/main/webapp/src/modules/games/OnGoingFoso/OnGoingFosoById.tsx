
function FindById(gameId: any){
    return new Promise(function(resolve, reject){
        fetch(`http://localhost:8080/api/ongoingFoso/${gameId}`)
        .then(response=>resolve(response.json()))
        .catch(error => reject(console.error))
    })
}

export default FindById