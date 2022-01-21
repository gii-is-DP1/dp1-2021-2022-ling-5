import { useEffect, useState } from 'react';
import { Row, Container } from 'react-bootstrap';
import figureImg from '../../images/figures/figures';
import achievementAPI from './achievementAPI';


function Achievements() {

  const [achievements, setAchievements] = useState<any[]>([]);
  const [myAchievements, setMyAchievements] = useState<any[]>([]);


  useEffect(() => {
    achievementAPI.getAllAchievements()
      .then((res: any[]) => setAchievements(res))
      .catch((err) => console.log(err));
  }, [achievements])

  useEffect(() => {
    var userData: any = localStorage.getItem("userData");
    if (userData !== null) userData = JSON.parse(userData)
    const playerId = userData.id
    achievementAPI.getAchievementByPlayer(playerId)
    .then((res: any[]) => setMyAchievements(res))
      .catch((err) => console.log(err));
  }, [myAchievements])

  

  if (!achievements && !myAchievements ) return <div>Loading...</div>



  return (
    <Container id="container">
        <Row>
        
          <Row> <h1>My achievements</h1> </Row>
{
            myAchievements.map(e => (
              <Row>
                <p> <strong>{e.name}</strong> <img src={figureImg(e.figure.id)} width="50" height="50" alt="" /></p> 
              </Row>
            ))
          }
        
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