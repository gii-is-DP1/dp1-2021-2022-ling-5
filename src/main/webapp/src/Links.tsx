
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import Navbar from "./Navbar";
import Patata from "./Patata";

const Links = () => {
    return <Router>
        <Navbar />
        <Switch>
            <Route exact path='/'>
                <Patata />
            </Route>
            <Route path='/profile'>
                <Patata />
            </Route>
            <Route path='/notifications'>
                <Patata />
            </Route>
            <Route path='/forum' >
                <Patata />
            </Route>
        </Switch>
    </Router>
}

export default Links