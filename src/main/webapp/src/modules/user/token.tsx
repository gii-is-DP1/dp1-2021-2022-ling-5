import userAPI from "./userAPI";

const token = {
    login: function (id: any, rol: string) {
        localStorage.setItem("sessionId", id);
        localStorage.setItem("rol", rol);
        userAPI.getUser(id, rol).then(user => {
            localStorage.setItem("userData", JSON.stringify(user));
        }).then(() => { window.location.href = "/" });
    },
    logout: function () {
        localStorage.removeItem("sessionId");
        localStorage.removeItem("rol");
        localStorage.removeItem("userData");
    },
    getToken: function () {
        let token = localStorage.getItem("sessionId");
        let fulltoken = null;
        if (token !== null) {
            fulltoken = {
                "sessionId": token,
                "rol": localStorage.getItem("rol")
            };
        }
        return fulltoken;
    },
    islogged: function () {
        return this.getToken !== null;
    },
    getLoggedUser: function () {
        let user = localStorage.getItem("userData");
        if (user !== null) {
            return JSON.parse(user);
        } else {
            return null;
        }
    }
}

export default token