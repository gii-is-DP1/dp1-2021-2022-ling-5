const gameAPI = {

    async addNewGame(game: any) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(game),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/games`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async addNewPlayerToGame(gameId: number, playerId: number) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/games/${gameId}/players/${playerId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async addNewMinigameToGame(gameId: number, minigameId: number) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/games/${gameId}/minigames/${minigameId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllGames() {
        return await fetch(`http://localhost:8080/api/games`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getGameById(gameId: number) {
        return await fetch(`http://localhost:8080/api/games/${gameId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getPlayersByGame(gameId: number) {
        return await fetch(`http://localhost:8080/api/games/${gameId}/players`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getGameByName(name: string) {
        return await fetch(`http://localhost:8080/api/games/names/${name}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteGame(gameId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/games/${gameId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllGames() {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/games`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deletePlayerFromGame(gameId: number, playerId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/games/${gameId}/players/${playerId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteMinigameFromGame(gameId: number, minigameId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/games/${gameId}/minigames/${minigameId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updateGame(newGame: any, gameId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newGame),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/games/${gameId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getGameMinigame(gameId: number){
        return await fetch(`http://localhost:8080/api/games/${gameId}/minigames`)
        .then((res:any)=>{
            return res.json();
        }).catch((err:any)=>console.log(err));
    }

}

export default gameAPI;