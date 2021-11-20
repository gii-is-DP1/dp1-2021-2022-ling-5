import axios from "axios";


const onGoingTorreInfernalAPI = {
    createGame: function(formData: {figureId: FormData}){
        return new Promise(function(resolve, reject){
            axios.put(`localhost:8080/api/ongoingTorreInfernal`, formData)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    },
    getAll: function(){
        return new Promise(function(resolve, reject){
            axios.get(`localhost:8080/api/ongoingTorreInfernal`)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    },
    getById: function(gameId: {figureId: any}){
        return new Promise(function(resolve, reject){
            axios.get(`localhost:8080/api/ongoingTorreInfernal/${gameId}`)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.reponse.data.message));
        });
    },
    getPlayerCard: function(playerId: {figureId: any}, gameId: {figureId: any}){
        return new Promise(function(resolve, reject){
            axios.get(`localhost:8080/api/players/${playerId}/ongoingTorreInfernal/${gameId}`)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.reponse.data.message));
        });
    },
    getCenterCard: function(gameId: {figureId: any}){
        return new Promise(function(resolve, reject){
            axios.get(`localhost:8080/api/ongoingTorreInfernal/${gameId}/card`)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    },
    getPoints: function(playerId: {figureId: any}, gameId: {figureId: any}){
        return new Promise(function(resolve, reject){
            axios.get(`localhost:8080/api/players/${playerId}/ongoingTorreInfernal/${gameId}/points`)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    },
    newCenterCard: function(gameId: {figureId: any}){
        return new Promise(function(resolve, reject){
            axios.put(`localhost:8080/api/ongoingTorreInfernal/${gameId}/card`)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    },
    newPlayerCard: function(playerId: {figureId: any}, gameId: {figureId:any}){
        return new Promise(function(resolve, reject){
            axios.put(`localhost:8080/api/players/${playerId}/ongoingTorreInfernal/${gameId}`)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    },
    addPoints: function(playerId: {figureId: any}, gameId: {figureId: any}, formData: {figureId: FormData}){
        return new Promise(function(resolve, reject){
            axios.put(`localhost:8080/api/players/${playerId}/ongoingTorreInfernal/${gameId}/points`, formData)
            .then(response=>resolve(response.data))
            .catch(error=>reject(error.response.data.message));
        });
    }
}

export { onGoingTorreInfernalAPI }