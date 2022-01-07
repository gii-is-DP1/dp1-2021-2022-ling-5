const privilegeAPI = {

    async addNewPrivilege(privilege: any) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(privilege)
        }

        return await fetch(`http://localhost:8080/api/privileges`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllPrivileges() {
        return await fetch(`http://localhost:8080/api/privileges`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getPrivilegeById(privilegeId: number) {
        return await fetch(`http://localhost:8080/api/privileges/${privilegeId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deletePrivilege(privilegeId: number) {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/privileges/${privilegeId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllPrivileges() {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/privileges`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updatePrivilege(newPrivilege: any, privilegeId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newPrivilege)
        }

        return await fetch(`http://localhost:8080/api/privileges/${privilegeId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    }

}

export default privilegeAPI;