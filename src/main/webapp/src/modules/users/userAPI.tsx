
const userAPI = {
    getUser: function(id: any, rol: string){
        let rolId = "";
        if(rol==="Player"){
            rolId = "players";
        } else if(rol==="Admin"){
            rolId = "admins";
        } else{
            throw "Not a role";
        }
        return new Promise(function(resolve, reject){
            fetch(`http://localhost:8080/api/${rolId}/${id}`)
            .then(res=>resolve(res.json()))
            .catch(error=>console.error);
        });
    }
}

export default userAPI;