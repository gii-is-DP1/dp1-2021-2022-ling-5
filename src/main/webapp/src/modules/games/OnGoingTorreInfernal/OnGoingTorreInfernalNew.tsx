
function NewOnGoingGame(formData: FormData){
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
    }
    return new Promise(function(resolve, reject){
        fetch(`http://localhost:8080/api/ongoingTorreInfernal`, requestOptions)
        .then(res=>{
            resolve(res.json())
        })
        .catch(error=>reject(console.error))
    })
}

export default NewOnGoingGame