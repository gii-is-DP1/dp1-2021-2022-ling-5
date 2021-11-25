import Button from 'react-bootstrap/Button';
import { ButtonGroup, Form, ToggleButton, ToggleButtonGroup } from 'react-bootstrap';




function StartGame() {

  return (
  
    <div className="NewGame-header"> 
      <p>GAME</p>
      <Form>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Waiting for players</Form.Label>
      
        </Form.Group>
        
        <Button className="Button" size="lg" variant="dark">
          START
        </Button>
      </Form>
    </div>

      
  );
}


export default StartGame ;
