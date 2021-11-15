import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/cards');
}

const get = (id: string) =>{
    return httpClient.get('/api/cards/'+id);
}

export default {getAll,get}