

function NewCard(gameId: any){
    const requestOptions = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({})
    };
    return new Promise(function(resolve, reject){
        fetch(`http://localhost:8080/api/ongoingElFoso/${gameId}/card`, requestOptions)
        .then(res=>{
            resolve(res.json())
        })
        .catch(error=> reject(console.error))
    })
}

export default NewCard