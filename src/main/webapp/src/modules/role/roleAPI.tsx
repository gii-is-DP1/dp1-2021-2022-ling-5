const roleAPI = {

    async addNewRole(role: any) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(role),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/roles`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllRoles() {
        return await fetch(`http://localhost:8080/api/roles`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getRoleById(roleId: number) {
        return await fetch(`http://localhost:8080/api/roles/${roleId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteRole(roleId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/roles/${roleId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllRoles() {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/roles`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updateRole(newRole: any, roleId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newRole),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/roles/${roleId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    }

}

export default roleAPI;