import { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';


function PlayerPlayedPoints(props:any){

    const playerId: any = 120
    const [results, setResults] = useState<any[]>([]);
    const [resultsAll, setResultsAll] = useState<any[]>([]);
    const [gamesAll, setGamesAll] = useState<number>(0);
    const maxAll = useState<number>(0);
    const maxPlayer = useState<number>(0);
    const minAll = useState<number>(0);
    const minPlayer = useState<number>(0);
    const avgAll = useState<number>(0);
    const avgPlayer = useState<number>(0);


    useEffect(() => {
        fetch(`http://localhost:8080/api/players/${playerId}/results`)
          .then(res => {
              return res.json()
          })
          .then(data => setResults(data))
          .catch(console.error)

          fetch(`http://localhost:8080/api/results`)
          .then(res => {
              return res.json()
          })
          .then(data => setResultsAll(data))
          .catch(console.error)

          fetch(`http://localhost:8080/api/games`)
          .then(res => {
              return res.json()
          })
          .then((data:any[]) => setGamesAll(data.length))
          .catch(console.error)
    
      }, [])

      if (!results) return <div>Loading...</div>
      return(
          <p>{results.length/gamesAll*100}%</p>






      );
    







    
}

export default PlayerPlayedPoints;