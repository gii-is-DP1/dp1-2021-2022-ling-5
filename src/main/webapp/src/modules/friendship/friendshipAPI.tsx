const friendshipAPI = {

    async addFriendship(friendship: any, requesterId: number, requestedId: number) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(friendship),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/players/requester/${requesterId}/requested/${requestedId}/friendships`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllFriendships() {
        return await fetch(`http://localhost:8080/api/friendships`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getFriendshipById(friendshipId: number) {
        return await fetch(`http://localhost:8080/api/friendships/${friendshipId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllFriendshipsByRequester(playerId: number) {
        return await fetch(`http://localhost:8080/api/players/requester/${playerId}/friendships`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllFriendshipsByRequested(playerId: number) {
        return await fetch(`http://localhost:8080/api/players/requested/${playerId}/friendships`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteFriendship(friendshipId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/friendships/${friendshipId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllFriendships() {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/friendships`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllFriendshipsByRequester(playerId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/players/requester/${playerId}/friendships`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllFriendshipsByRequested(playerId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/players/requested/${playerId}/friendships`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updateFriendship(newFriendship: any, requestedId: number, friendshipId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newFriendship),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/players/requested/${requestedId}/friendships/${friendshipId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    }

}

export default friendshipAPI;