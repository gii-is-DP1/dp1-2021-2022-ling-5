
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import PrincipalNavbar from "./modules/general/PrincipalNavbar";
import Patata from "./Patata";
import ProfileNavbar from "./modules/general/ProfileNavbar";
import NewGame from "./modules/general/NewGame";
import JoinGame from "./modules/general/JoinGame";
import PlayedGames from './modules/game/playedGames';
import StartGame from "./modules/general/StartGame";
import AdminNavbar from "./modules/general/AdminNavbar";
import UsersCRUD from "./modules/user/UsersCRUD";
import StadisticsNavbar from "./modules/general/StadisticsNavbar";
import CreateUser from "./modules/user/CreateUser";
import BestAndWorstFigure from "./modules/figure/BestAndWorstFigure";
import Ranking from "./modules/user/Ranking";
import Friends from "./modules/friendship/Friends";
import { useEffect, useState } from "react";
import UserFrequency from "./modules/playedGames/UserFrequency";
import AddFriend from "./modules/friendship/AddFriend";
import AcceptFriend from "./modules/friendship/Notification";
import PropGamesPlayed from "./modules/statistics/PropGamesPlayed";
import PropTime from "./modules/statistics/PropTime";
import AdminAchievement from "./modules/achievement/AdminAchievement";
import CreateAchievement from "./modules/achievement/CreateAchievement";
import Achievements from "./modules/achievement/Achievements";
import Forums from "./modules/forum/AllForums";
import Forum from "./modules/forum/Forum";
import NewForum from "./modules/forum/NewForum";
import UserPoints from "./modules/playedGames/UserPoints";
import Profile from "./modules/user/Profile";
import AdminPlayedGames from "./modules/game/AdminPlayedGames";

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
                        
                        <Patata/>
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
            {/*<Route path='/pointsByMinigame'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <StadisticsNavbar />
                        <UserPoints />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/mostAndLeastUsed'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <StadisticsNavbar />
                        <BestAndWorstFigure />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/ranking'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <StadisticsNavbar />
                        <Ranking />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/userFrequency'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <StadisticsNavbar />
                        <UserFrequency />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/propGames'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <StadisticsNavbar />
                        <PropGamesPlayed />
                    </div>
                    : <>Access is restricted</>}
            </Route>
            <Route path='/propTime'>
                {role === "Player" ?
                    <div id="body" className="d-flex">
                        <ProfileNavbar />
                        <StadisticsNavbar />
                        <PropTime />
                    </div>
                    : <>Access is restricted</>}
            </Route> */}
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
                        <Patata /></div>
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
            
        </Switch>
    </Router>
}

export default Links