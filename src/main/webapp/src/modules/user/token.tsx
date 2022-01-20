import userAPI from "./userAPI";

const token = {
    login: function (id: any, rol: string) {
        localStorage.setItem("sessionId", id);
        localStorage.setItem("rol", rol);

        userAPI.getUser(id, rol).then(async (user: any) => {
            var newUser = user;
            newUser.playerState = "NO_PLAY";
            const userUpdated = await userAPI.updateUser(newUser, id, rol);
            localStorage.setItem("userData", JSON.stringify(userUpdated));
            window.location.href = "/";
        }).catch((err) => console.log(err));

    },
    logout: function () {
        const idStr = localStorage.getItem("sessionId");
        const rol = localStorage.getItem("rol");
        if (rol != null && idStr != null) {
            const id = parseInt(idStr);
            console.log(id, rol);
            userAPI.getUser(id, rol).then(async (user: any) => {
                var newUser = user;
                newUser.playerState = "OFFLINE";
                console.log(newUser);
                const userUpdated = await userAPI.updateUser(newUser, id, rol);
                localStorage.removeItem("sessionId");
                localStorage.removeItem("rol");
                localStorage.removeItem("userData");
                window.location.href="/";
            }).catch((err) => {
                console.log(err);
                console.log("hola");
            });

        }

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
    },
    getLoggedId: function(){
        return localStorage.getItem("sessionId");
    }
}

export default token