
const authApi = {
    login: function(formData: any){
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(formData),
            'credentials':'include' as RequestCredentials,
        };
        return new Promise<any>(function(resolve, reject){
            fetch('http://localhost:8080/api/login', requestOptions)
            .then(res => resolve(res.json()))
            .catch(error => reject(alert(error.message)));
        });
    },
    register: function(formData: any){
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(formData),
            'credentials':'include' as RequestCredentials
        };
        return new Promise<any>(function(resolve, reject){
            fetch('http://localhost:8080/api/register', requestOptions)
            .then(res => resolve(res.json()))
            .catch(error => reject(alert(error.message)));
        });
    }
}

export default authApi;