const minigameAPI = {

    async addNewMinigame(minigame: any) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(minigame),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/minigames`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllMinigames() {
        return await fetch(`http://localhost:8080/api/minigames`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getMinigameById(minigameId: number) {
        return await fetch(`http://localhost:8080/api/minigames/${minigameId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteMinigame(minigameId: number) {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/minigames/${minigameId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllMinigames() {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/minigames`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updateMinigame(newMinigame: any, minigameId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newMinigame),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/minigames/${minigameId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    }

}

export default minigameAPI;