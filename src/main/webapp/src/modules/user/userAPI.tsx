
const userAPI = {
    getUser: function (id: any, role: string) {
        let roleId = "";
        if (role.toLowerCase() === "player") {
            roleId = "players";
        } else if (role.toLowerCase() === "admin") {
            roleId = "admins";
        } else {
            throw "Not a role";
        }
        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/${roleId}/${id}`)
                .then(res => resolve(res.json()))
                .catch(error => console.error);
        });
    },

    getPlayerByNickname: function (nickname: any) {
        return new Promise<any>(function (resolve, reject) {
            fetch(`http://localhost:8080/api/players/names/${nickname}`)
                .then(res => res.json()).then(resolve)
                .catch(error => console.error);
        });
    },

    async addNewUser(user: any, role: string) {
        if (role.toLowerCase() === "player") {
            role = "players";
        } else if (role.toLowerCase() === "admin") {
            role = "admins";
        } else {
            throw "Not a role";
        }

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        }

        return await fetch(`http://localhost:8080/api/${role}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllUsers(role: string) {
        if (role.toLowerCase() === "player") {
            role = "players";
        } else if (role.toLowerCase() === "admin") {
            role = "admins";
        } else {
            throw "Not a role";
        }

        return await fetch(`http://localhost:8080/api/${role}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteUser(role: string, userId: number) {
        if (role.toLowerCase() === "player") {
            role = "players";
        } else if (role.toLowerCase() === "admin") {
            role = "admins";
        } else {
            throw "Not a role";
        }

        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/${role}/${userId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllUsers(role: string, userId: number) {
        if (role.toLowerCase() === "player") {
            role = "players";
        } else if (role.toLowerCase() === "admin") {
            role = "admins";
        } else {
            throw "Not a role";
        }

        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/${role}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updateUser(newUser: any, userId: number, role: string) {
        if (role.toLowerCase() === "player") {
            role = "players";
        } else if (role.toLowerCase() === "admin") {
            role = "admins";
        } else {
            throw "Not a role";
        }

        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newUser)
        }

        return await fetch(`http://localhost:8080/api/${role}/${userId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async updateFigureUser(userId: number, figureId: number, role: string) {
        if (role.toLowerCase() === "player") {
            role = "players";
        } else if (role.toLowerCase() === "admin") {
            role = "admins";
        } else {
            throw "Not a role";
        }

        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' }
        }

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/figures/${figureId}/${role}/${userId}`, requestOptions)
                .then(res => {
                    resolve(res.json())
                })
                .catch(error => reject(console.error))
        });
    },

    async addNewAchievementToPlayer(playerId: number, achievementId: number) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }

        return await fetch(`http://localhost:8080/api/players/${playerId}/achievements/${achievementId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async deleteAchievementFromPlayer(playerId: number, achievementId: number) {
        const requestOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' }
        }

        return await fetch(`http://localhost:8080/api/players/${playerId}/achievements/${achievementId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

}

export default userAPI;