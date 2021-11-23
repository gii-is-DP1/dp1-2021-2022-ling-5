
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import App from "./App";
import PrincipalNavbar from "./PrincipalNavbar";
import Patata from "./Patata";
import ProfileNavbar from "./ProfileNavbar";

const Links = () => {
    return <Router>
        <PrincipalNavbar />
        <Switch>
            <Route exact path='/'>
                <App />
            </Route>
            <Route path='/profile'>
                <div className="d-flex justify-content-between"><ProfileNavbar />
                    <App /></div>

            </Route>
            <Route path='/stats'>
                <div className="d-flex justify-content-between"><ProfileNavbar />
                    <App /></div>
            </Route>
            <Route path='/games'>
                <div className="d-flex justify-content-between"><ProfileNavbar />
                    <App /></div>
            </Route>
            <Route path='/awards'>
                <div className="d-flex justify-content-between"><ProfileNavbar />
                    <App /></div>
            </Route>
            <Route path='/friends'>
                <div className="d-flex justify-content-between"><ProfileNavbar />
                    <App /></div>
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