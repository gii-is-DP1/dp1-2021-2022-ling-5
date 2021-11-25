
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import PrincipalNavbar from "./modules/general/PrincipalNavbar";
import Patata from "./Patata";
import ProfileNavbar from "./modules/general/ProfileNavbar";
import PlayedGames from './modules/games/playedGames';

const Links = () => {
    return <Router>
        <PrincipalNavbar />
        <Switch>
            <Route exact path='/'>
                <App />
            </Route>
            <Route path='/newGame'>
                <Patata />
            </Route>
            <Route path='/joinGame'>
                <Patata />
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
                <Patata />
            </Route>
        </Switch>
    </Router>
}

export default Links