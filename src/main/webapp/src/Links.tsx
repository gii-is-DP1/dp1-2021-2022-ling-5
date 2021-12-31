
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import PrincipalNavbar from "./modules/general/PrincipalNavbar";
import Patata from "./Patata";
import ProfileNavbar from "./modules/general/ProfileNavbar";
import NewGame from "./modules/general/NewGame";
import JoinGame from "./modules/general/JoinGame";
import PlayedGames from './modules/game/playedGames';
import StartGame from "./modules/general/StartGame";
import AdminPlayedGames from "./modules/game/adminPlayedGames";
import AdminNavbar from "./modules/general/AdminNavbar";
import UsersCRUD from "./modules/user/UsersCRUD";
import StadisticsNavbar from "./modules/general/StadisticsNavbar";
import CreateUser from "./modules/user/CreateUser";
import UserPoints from "./modules/playedGames/UserPoints";
import BestAndWorstFigure from "./modules/figure/BestAndWorstFigure";
import Friends from "./modules/friendship/Friends";

const Links = () => {
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
            <Route path='/profile'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/stats'>
                <div id="body" className="d-flex"><ProfileNavbar />
                    <StadisticsNavbar /></div>
            </Route>
            <Route path='/pointsbyminigamme'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <StadisticsNavbar />
                    <UserPoints />
                </div>
            </Route>
            <Route path='/mostandleastused'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <StadisticsNavbar />
                    <BestAndWorstFigure />
                </div>
            </Route>
            <Route path='/games'>
                <div id="body" className="d-flex">
                    <ProfileNavbar />
                    <PlayedGames />
                </div>
            </Route>
            <Route path='/awards'>
                <div id="body" className="d-flex"><ProfileNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/friends'>
                <div id="body" className="d-flex"><ProfileNavbar />
                    <Friends /></div>
            </Route>
            <Route path='/gamesProgress'>
                <div id="body" className="d-flex">
                    <AdminNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/gamesPlayed'>
                <div id="body" className="d-flex"><AdminNavbar />
                    <AdminPlayedGames /></div>
            </Route>
            <Route exact path='/users'>
                <div id="body" className="d-flex">
                    <AdminNavbar />
                    <UsersCRUD />
                </div>
            </Route>
            <Route path='/createUser'>
                <div id="body" className="d-flex">
                    <AdminNavbar />
                    <CreateUser />
                </div>
            </Route>
            <Route path='/history'>
                <div id="body" className="d-flex"><AdminNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/createAwards'>
                <div id="body" className="d-flex"><AdminNavbar />
                    <Patata /></div>
            </Route>
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