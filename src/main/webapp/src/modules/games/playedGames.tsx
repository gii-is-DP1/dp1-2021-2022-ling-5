import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

function PlayedGames() {
  const player=1
  const [state, setState] = useState<any>()
  useEffect(() => {
    fetch("http://localhost:8080/api/players/"+player+"/results")
      .then(res => {
        console.log(res.status)
        return res.json()
      })
      .then(data => setState(data))
      .catch(console.error)
  }, [])
  if (!state) return <div>Loading...</div>
  let creados=[]
  let jugados=[]
  for (var r of state){
    if(r.game.creator==player){
      creados.push(r)
    }else{
      jugados.push(r)
    }
  }
  console.log(creados)
  console.log(jugados)
  return (
    <div>
    </div>
  );
}

export default PlayedGames