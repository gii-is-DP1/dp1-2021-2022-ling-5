import { useEffect, useState } from 'react';
import { Row, Container } from 'react-bootstrap';
import figureImg from '../../images/figures/figures';
import achievementAPI from './achievementAPI';


function Achievements() {

  const [achievements, setAchievements] = useState<any[]>();

  useEffect(() => {
    achievementAPI.getAllAchievements()
      .then((res: any[]) => setAchievements(res))
      .catch((err) => console.log(err));
  }, [])

  if (!achievements) return <div>Loading...</div>


  if (achievements.length === 0) {
    achievements.push({ name: "Ninguno" });
  }
 

  return (
    <Container id="container">
        <Row>
        
          <Row> <h1>My achievements</h1> </Row>
       
        
          <Row> <h1>All achievements</h1> </Row>
          {
            achievements.map(e => (
              <Row>
                <p> <strong>{e.name}</strong> <img src={figureImg(e.figure.id)} width="50" height="50" alt="" /></p> 
              </Row>
            ))
          }
        
        </Row>
    </Container>
  );
}

export default Achievements