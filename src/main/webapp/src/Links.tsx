
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import PrincipalNavbar from "./modules/general/PrincipalNavbar";
import Patata from "./Patata";
import ProfileNavbar from "./modules/general/ProfileNavbar";
import NewGame from "./modules/general/NewGame";
import JoinGame from "./modules/general/JoinGame";
import PlayedGames from './modules/game/playedGames';
import StartGame from "./modules/general/StartGame";
import Login from "./modules/general/login";
import Register from "./modules/general/register";
import AdminPlayedGames from "./modules/game/adminPlayedGames";
import AdminNavbar from "./modules/general/AdminNavbar";
import UsersCRUD from "./modules/user/UsersCRUD";
import StadisticsNavbar from "./modules/general/StadisticsNavbar";
import CreateUser from "./modules/user/CreateUser";
import UserPoints from "./modules/playedGames/UserPoints";
import BestAndWorstFigure from "./modules/figure/BestAndWorstFigure";
import Ranking from "./modules/user/Ranking";
import Friends from "./modules/friendship/Friends";
import { useEffect, useState } from "react";
import UserFrequency from "./modules/playedGames/UserFrequency";

const Links = () => {
    const [role, setRole] = useState<string | null>(null)
    useEffect(() => {
        setRole(localStorage.getItem("rol"))
    }, [])

    if (!role) return <></>
    return <Router>
        <PrincipalNavbar />
        <Switch>
            <Route exact path='/'>
                <App />
            </Route>
            {/* <Route path="/login">
                <Login />
            </Route>
            <Route path="/register">
                <Register />
            </Route> */}
            <Route path='/newGame'>
                <NewGame />
            </Route>
            <Route path='/joinGame'>
                <JoinGame />
            </Route>
            <Route path='/startGame/:id'>
                <StartGame />
            </Route>

            {role === "Player" ? <Route path='/profile'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <Patata /></div>
            </Route> : <>Access is restricted</>}

            {role === "Player" ? <Route path='/stats'>
                <div id="body" className="d-flex"><ProfileNavbar />
                    <StadisticsNavbar /></div>
            </Route> : <>Access is restricted</>}

            {role === "Player" ? <Route path='/pointsbyminigamme'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <StadisticsNavbar />
                    <UserPoints />
                </div>
            </Route> : <>Access is restricted</>}

            {role === "Player" ? <Route path='/mostandleastused'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <StadisticsNavbar />
                    <BestAndWorstFigure />
                </div>
            </Route> : <>Access is restricted</>}

            {role === "Player" ? <Route path='/ranking'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <StadisticsNavbar />
                    <Ranking />
                </div>
            </Route> : <>Access is restricted</>}

            {role === "Player" ? <Route path='/userfrequency'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <StadisticsNavbar />
                    <UserFrequency />
                </div>
            </Route> : <>Access is restricted</>}

            {role === "Player" ? <Route path='/games'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <PlayedGames />
                </div>
            </Route> : <>Access is restricted</>}

            {role === "Player" ? <Route path='/awards'>
                <div id="body" className="d-flex"><ProfileNavbar />
                    <Patata /></div>
            </Route> : <>Access is restricted</>}

            {role === "Player" ? <Route path='/friends'>
                <div id="body" className="d-flex"><ProfileNavbar />
                    <Friends /></div>
            </Route> : <>Access is restricted</>}

            {role === "Admin" ? <Route path='/gamesProgress'>
                <div id="body" className="d-flex">
                    <AdminNavbar />
                    <Patata /></div>
            </Route> : <>Access is restricted</>}

            {role === "Admin" ? <Route path='/gamesPlayed'>
                <div id="body" className="d-flex"><AdminNavbar />
                    <AdminPlayedGames /></div>
            </Route> : <>Access is restricted</>}

            {role === "Admin" ? <Route exact path='/users'>
                <div id="body" className="d-flex">
                    <AdminNavbar />
                    <UsersCRUD />
                </div>
            </Route> : <>Access is restricted</>}

            {role === "Admin" ? <Route path='/createUser'>
                <div id="body" className="d-flex">
                    <AdminNavbar />
                    <CreateUser />
                </div>
            </Route> : <>Access is restricted</>}

            {role === "Admin" ? <Route path='/history'>
                <div id="body" className="d-flex"><AdminNavbar />
                    <Patata /></div>
            </Route> : <>Access is restricted</>}

            {role === "Admin" ? <Route path='/createAwards'>
                <div id="body" className="d-flex"><AdminNavbar />
                    <Patata /></div>
            </Route> : <>Access is restricted</>}

            <Route path='/notifications'>
                <Patata />
            </Route>
            <Route path='/forum' >
                <Patata />
            </Route>
            <Route path="/logout">
                <Patata />
            </Route>

        </Switch>
    </Router>
}

export default Links