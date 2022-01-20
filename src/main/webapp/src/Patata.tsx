import { useState, useEffect } from "react";
import { Card, Col, Row } from "react-bootstrap";
import figures from "./images/figures/figures";
import icons from "./images/icons/icons";
import BestAndWorstFigure from "./modules/figure/BestAndWorstFigure";
import UserFrequency from "./modules/playedGames/UserFrequency";
import UserPoints from "./modules/playedGames/userPoints";
import PropGamesPlayed from "./modules/statistics/PropGamesPlayed";
import PropTime from "./modules/statistics/PropTime";
import Ranking from "./modules/user/Ranking";
import userAPI from "./modules/user/userAPI";

function Patata() {

    return (

        <div >
             <Row className="d-flex panel">
                <Col><UserPoints/></Col><Col><BestAndWorstFigure/></Col>                 
            </Row>
            <Row>                
                <Row><UserFrequency/></Row>
                <Row><PropGamesPlayed/></Row>
                <Row><PropGamesPlayed/></Row>
            </Row>
                    
    
        </div>

    );
}

export default Patata;