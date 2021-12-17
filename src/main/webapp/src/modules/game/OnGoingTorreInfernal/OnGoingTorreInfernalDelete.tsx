
function DeleteGame(gameId: any){
    const requestOptions = {
        method: 'DELETE'
    }
    return new Promise(function(resolve, reject){
        fetch(`http://localhost:8080/api/ongoingTorreInfernal/${gameId}`, requestOptions)
        .then(res=>{
            resolve(res.json())
        })
        .catch(error=> reject(console.error))
    })
}

export default DeleteGame