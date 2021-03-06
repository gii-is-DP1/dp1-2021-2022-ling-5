
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import PrincipalNavbar from "./modules/general/PrincipalNavbar";
import ProfileNavbar from "./modules/general/ProfileNavbar";
import NewGame from "./modules/general/NewGame";
import JoinGame from "./modules/general/JoinGame";
import PlayedGames from './modules/game/playedGames';
import StartGame from "./modules/general/StartGame";
import AdminNavbar from "./modules/general/AdminNavbar";
import UsersCRUD from "./modules/user/UsersCRUD";
import CreateUser from "./modules/user/CreateUser";
import BestAndWorstFigure from "./modules/figure/BestAndWorstFigure";
import Ranking from "./modules/user/Ranking";
import Friends from "./modules/friendship/Friends";
import { useEffect, useState } from "react";
import AddFriend from "./modules/friendship/AddFriend";
import AcceptFriend from "./modules/friendship/Notification";
import AdminAchievement from "./modules/achievement/AdminAchievement";
import CreateAchievement from "./modules/achievement/CreateAchievement";
import Achievements from "./modules/achievement/Achievements";
import Forums from "./modules/forum/AllForums";
import Forum from "./modules/forum/Forum";
import NewForum from "./modules/forum/NewForum";
import Foso from "./modules/general/Foso";
import Profile from "./modules/user/Profile";
import Stats from "./modules/general/Stats";
import ErrorPage from "./modules/general/ErrorPage";
import Auditory from "./modules/user/Auditory";
import AdminPlayedGames from "./modules/game/AdminPlayedGames";
import RegaloEnvenenado from "./modules/general/RegaloEnvenenado";
import TorreInfernal from "./modules/general/TorreInfernal";

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
            <Route path='/newGame'>
                <NewGame />
            </Route>
            <Route path='/joinGame'>
                <JoinGame />
            </Route>
            <Route path='/startGame/:id'>
                <StartGame />
            </Route>
            <Route path='/game/:gameId/1'>
                <TorreInfernal />
            </Route>
            <Route path='/game/:gameId/2'>
                <Foso />
            </Route>
            <Route path='/game/:gameId/3'>
                <RegaloEnvenenado />
            </Route>
            <Route path='/notifications'>
                <AcceptFriend />
            </Route>
            <Route path='/forums' >
                <Forums />
            </Route>

            <Route path='/profile'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <Profile /></div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/stats'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <Stats/>
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/ranking'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <Ranking/>
                    </div>
                    : <>Access is restricted</>}
             </Route>
            <Route path='/games'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <PlayedGames />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/awards'>
                {role === "Player" ?
                    <div id="body" className="d-flex"><ProfileNavbar />
                        <Achievements /></div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/friends'>
                {role === "Player" ?
                    <div id="body" className="d-flex"><ProfileNavbar />
                        <Friends /></div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/addFriend'>
                {role === "Player" ?
                    <div id="body" className="d-flex"><ProfileNavbar />
                        <AddFriend /></div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/gamesProgessAndPlayed'>
                {role === "Admin" ?
                    <div id="body" className="d-flex"><AdminNavbar />
                        <AdminPlayedGames /></div>
                    : <>Access is restricted</>}
            </Route>
            <Route exact path='/users'>
                {role === "Admin" ?
                    <div id="body" className="d-flex">
                        <AdminNavbar />
                        <UsersCRUD />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/createUser'>
                {role === "Admin" ?
                    <div id="body" className="d-flex">
                        <AdminNavbar />
                        <CreateUser />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/history'>
                {role === "Admin" ?
                    <div id="body" className="d-flex"><AdminNavbar />
                        <Auditory /></div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/adminAwards'>
                {role === "Admin" ?
                    <div id="body" className="d-flex"><AdminNavbar />
                        <AdminAchievement /></div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/createAchievement'>
                {role === "Admin" ?
                    <div id="body" className="d-flex">
                        <AdminNavbar />
                        <CreateAchievement />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/forum/:id' >
                <Forum />
            </Route><Route path='/newforum' >
                <NewForum />
            </Route>
            
            <Route path ='*'>
                <ErrorPage/>
            </Route>
        </Switch>
    </Router>
}

export default Links