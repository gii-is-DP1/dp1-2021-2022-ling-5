
import Button from 'react-bootstrap/Button';
import { ButtonGroup, Form, ToggleButton, ToggleButtonGroup } from 'react-bootstrap';

import './NewGame.css';


function JoinGame() {

  return (
  
    <div className="NewGame-header"> 
      <p>JOIN GAME</p>
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Game's name you want to join</Form.Label>
          <Form.Control placeholder="Enter game's name" />
        </Form.Group>

        
        <Button className="Button" size="lg" variant="dark">
          JOIN
        </Button>
      </Form>
    </div>

      
  );
}


export default JoinGame ;
