
import Button from 'react-bootstrap/Button';
import { ButtonGroup, Form, ToggleButton, ToggleButtonGroup } from 'react-bootstrap';

import './NewGame.css';


function NewGame() {

  return (
  
    <div className="NewGame-header"> 
      <p>NEW GAME</p>
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Game's name</Form.Label>
          <Form.Control placeholder="Enter name" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Choose minigame</Form.Label>
      
          <div className="Button" >
            <button id="btn1">
              Minigame 1
            </button >
            <button id="btn2">
              Minigame 2
            </button>
            <button id="btn3">
              Minigame 3
            </button>
            <button id="btn4">
              All minigames
            </button>
          </div>
        </Form.Group>
        
        <Button className="Button" size="lg" variant="dark">
          CREATE
        </Button>
      </Form>
    </div>

      
  );
}


export default NewGame ;
