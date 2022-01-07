const figureAPI = {
    async addNewFigure(figure: any) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(figure)
        }

        return await fetch(`http://localhost:8080/api/figures`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getAllFigures() {
        return await fetch(`http://localhost:8080/api/figures`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    async getFigureById(figureId: number) {
        return await fetch(`http://localhost:8080/api/figures/${figureId}`)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    },

    deleteFigure(figureId: number) {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/figures/${figureId}`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    deleteAllFigures() {
        const requestOptions = {
            method: 'DELETE'
        };

        return new Promise(function (resolve, reject) {
            fetch(`http://localhost:8080/api/figures`, requestOptions)
                .then(res => {
                    resolve(res)
                })
                .catch(error => reject(console.error))
        })
    },

    async updateFigure(newFigure: any, figureId: number) {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newFigure)
        }

        return await fetch(`http://localhost:8080/api/figures/${figureId}`, requestOptions)
            .then((res: any) => {
                return res.json();
            }).catch((err: any) => console.log(err));
    }
}
export default figureAPI;