import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/players');
}

const get = (id: String) =>{
    return httpClient.get('/api/players/'+id);
}

export default {getAll,get}