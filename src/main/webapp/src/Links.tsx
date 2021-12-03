
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import PrincipalNavbar from "./modules/general/PrincipalNavbar";
import Patata from "./Patata";
import ProfileNavbar from "./modules/general/ProfileNavbar";
import NewGame from "./modules/general/NewGame";
import JoinGame from "./modules/general/JoinGame";
import PlayedGames from './modules/games/playedGames';
import StartGame from "./modules/general/StartGame";
import AdminPlayedGames from "./modules/games/adminPlayedGames";
import AdminNavbar from "./modules/general/AdminNavbar";
import UsersCRUD from "./modules/users/UsersCRUD";

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
                <div className="d-flex">
                    <ProfileNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/stats'>
                <div className="d-flex"><ProfileNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/games'>
                <div className="d-flex">
                    <ProfileNavbar />
                    <PlayedGames />
                </div>
            </Route>
            <Route path='/awards'>
                <div className="d-flex"><ProfileNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/friends'>
                <div className="d-flex"><ProfileNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/gamesProgress'>
                <div className="d-flex">
                    <AdminNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/gamesPlayed'>
                <div className="d-flex"><AdminNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/users'>
                <div className="d-flex">
                    <AdminNavbar />
                    <UsersCRUD />
                </div>
            </Route>
            <Route path='/history'>
                <div className="d-flex"><AdminNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/awards'>
                <div className="d-flex"><AdminNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/notifications'>
                <Patata />
            </Route>
            <Route path='/forum' >
                <Patata />
            </Route>
            <Route path="/logout">
                <AdminPlayedGames />
            </Route>
        </Switch>
    </Router>
}

export default Links