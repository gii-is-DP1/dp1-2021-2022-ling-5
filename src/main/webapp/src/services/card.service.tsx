import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/cards');
}

const get = (id: string) =>{
    return httpClient.get(`/cards/${id}`);
}

export default {getAll,get}