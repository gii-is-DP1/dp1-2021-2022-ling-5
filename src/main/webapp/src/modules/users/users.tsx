import axios from "axios";

const usersAPI = {
    registerPlayer: function(formData: { figureId: FormData; }){
        return new Promise(function(resolve, reject){
            axios.post(`localhost:8080/api/roles/${1}/figures/${formData.figureId}/players`, formData)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    },
    modifyPlayer: function(formData: { figureId: FormData; }, playerId: {figureId: any; }){
        return new Promise(function(resolve, reject){
            axios.put(`localhost:8080/api/player/${playerId}`, formData)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    },
    registerAdmin: function(formData: { figureId: FormData; }){
        return new Promise(function(resolve, reject){
            axios.post(`localhost:8080/api/roles/${2}/figures/${formData.figureId}/admins`)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.reponse.data.message));
        });
    }
}

export { usersAPI } ;