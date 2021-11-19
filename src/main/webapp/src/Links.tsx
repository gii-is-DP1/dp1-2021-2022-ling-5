
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import Navbar from "./Navbar";
import Patata from "./Patata";
import PlayedGames from './modules/games/playedGames';

const Links = () => {
    return <Router>
        <Navbar />
        <Switch>
            <Route exact path='/'>
                <App />
            </Route>
            <Route path='/profile'>
                <PlayedGames />
            </Route>
            <Route path='/notifications'>
                <Patata />
            </Route>
            <Route path='/forum' >
                <Patata />
            </Route>
            <Route path="/playedGames">
                <PlayedGames />
            </Route>
        </Switch>
    </Router>
}

export default Links