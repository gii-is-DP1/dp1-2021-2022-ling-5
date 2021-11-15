import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/games');
}

const get = (id: string) =>{
    return httpClient.get('/api/games/'+id);
}

export default {getAll,get}