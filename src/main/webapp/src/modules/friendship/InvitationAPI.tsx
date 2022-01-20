const invitationAPI = {

    async addInvitation(invitation: any, requesterId: number, requestedId: number) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(invitation)
        }

        return await fetch(`http://localhost:8080/api/players/requester/${requesterId}/requested/${requestedId}/invitations`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllInvitations() {
        return await fetch(`http://localhost:8080/api/invitations`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getInvitationById(invitationId: number) {
        return await fetch(`http://localhost:8080/api/invitations/${invitationId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllInvitationsByRequester(playerId: number) {
        return await fetch(`http://localhost:8080/api/players/requester/${playerId}/invitations`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllInvitationsByRequested(playerId: number) {
        return await fetch(`http://localhost:8080/api/players/requested/${playerId}/invitations`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteInvitation(invitationId: number) {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/invitations/${invitationId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllInvitations() {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/invitations`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllInvitationsByRequester(playerId: number) {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/players/requester/${playerId}/invitations`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllInvitationsByRequested(playerId: number) {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/players/requested/${playerId}/invitations`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updateInvitation(newInvitation: any, requestedId: number, invitationId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newInvitation)
        }

        return await fetch(`http://localhost:8080/api/players/requested/${requestedId}/invitations/${invitationId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    }

}

export default invitationAPI;