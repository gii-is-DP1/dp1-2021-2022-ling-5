import { useState, useEffect } from "react";
import { Card, Col, Row } from "react-bootstrap";
import BestAndWorstFigure from "../figure/BestAndWorstFigure";
import UserFrequency from "../playedGames/UserFrequency";
import UserPoints from "../playedGames/userPoints";
import PropGamesPlayed from "../statistics/PropGamesPlayed";


function Stats() {

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

export default Stats;