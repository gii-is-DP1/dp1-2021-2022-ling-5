

function NewCard(gameId: any, playerId: any){
    return new Promise(function(resolve, reject){
        fetch(`/players/${playerId}/ongoingFoso/${gameId}/newcard`)
        .then(res=>{
            resolve(res.json())
        })
        .catch(error=> reject(console.error))
    })
}

export default NewCard