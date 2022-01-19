const cardAPI = {

    async addNewCard(card: any) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(card),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/cards`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async addNewFigureToCard(cardId: number, figureId: number) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/cards/${cardId}/figures/${figureId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllCards() {
        return await fetch(`http://localhost:8080/api/cards`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getCardById(cardId: number) {
        return await fetch(`http://localhost:8080/api/cards/${cardId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteCard(cardId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/cards/${cardId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllCards() {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/cards`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteFigureFromCard(figureId: number, cardId: number) {
        const requestOptions = {
            method: 'DELETE',
            'credentials':'include' as RequestCredentials
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/cards/${cardId}/figures/${figureId}`, requestOptions).then(res => {
                resolve(res)
            }).catch(error => reject(console.error))
        })
    },

    async updateCard(newCard: any, cardId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newCard),
            'credentials':'include' as RequestCredentials
        }

        return await fetch(`http://localhost:8080/api/cards/${cardId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    }

}

export default cardAPI;