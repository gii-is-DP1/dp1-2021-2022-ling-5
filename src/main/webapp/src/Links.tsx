
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import PrincipalNavbar from "./modules/general/PrincipalNavbar";
import Patata from "./Patata";
import ProfileNavbar from "./modules/general/ProfileNavbar";
import NewGame from "./NewGame";
import JoinGame from "./JoinGame";
import PlayedGames from './modules/games/playedGames';
import StartGame from "./StartGame";
import AdminPlayedGames from "./modules/games/adminPlayedGames";

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
            <Route path='/startGame'>
                <StartGame />
            </Route>
            <Route path='/profile'>
                <div className="d-flex justify-content-between">
                    <ProfileNavbar />
                    <Patata /></div>

            </Route>
            <Route path='/stats'>
                <div className="d-flex justify-content-between"><ProfileNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/games'>
                <div className="d-flex justify-content-between">
                    <ProfileNavbar />
                    <PlayedGames />
                </div>
            </Route>
            <Route path='/awards'>
                <div className="d-flex justify-content-between"><ProfileNavbar />
                    <Patata /></div>
            </Route>
            <Route path='/friends'>
                <div className="d-flex justify-content-between"><ProfileNavbar />
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