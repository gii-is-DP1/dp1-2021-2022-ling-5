
function DeleteGame(gameId: any){
    const requestOptions = {
        method: 'DELETE'
    }
    return new Promise(function(resolve, reject){
        fetch(`http://localhost:8080/api/ongoingFoso/${gameId}`, requestOptions)
        .then(res=>{
            resolve(res.json())
        })
        .catch(error=> reject(console.error))
    })
}

export default DeleteGame