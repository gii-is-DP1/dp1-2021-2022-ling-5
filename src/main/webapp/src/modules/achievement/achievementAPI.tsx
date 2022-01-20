const achievementAPI = {
    async createAchievementWithFigure(achievement: any, figureId: number) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(achievement),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/achievements/figures/${figureId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllAchievements() {
        return await fetch(`http://localhost:8080/api/achievements`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAchievementByPlayer(playerId: number){
        return await fetch(`http://localhost:8080/api/achievements/players/${playerId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAchievementById(achievementId: number) {
        return await fetch(`http://localhost:8080/api/achievements/${achievementId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllFigureAchivementsPlayer(playerId: number){
        return await fetch(`http://localhost:8080/api//achievments/figures/${playerId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteAchievement(achievementId: number) {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/achievements/${achievementId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllAchievements() {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/achievements`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updateAchievement(newAchievement: any, achievementId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newAchievement),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/achievements/${achievementId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async updateFigureAchievement(achId: number, figureId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/achievements/figures/${figureId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
        }
    }

export default achievementAPI;