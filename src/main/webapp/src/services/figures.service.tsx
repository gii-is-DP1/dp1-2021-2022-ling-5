import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/figures');
}

const get = (id: string) =>{
    return httpClient.get('/figures/'+id);
}

export default {getAll,get}