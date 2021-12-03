import { useEffect, useState } from 'react';

function UserPoints(props: any) {
    const playerId: any = 1;
    const [results, setResutls] = useState<any[]>([]);
    useEffect(() => {
      fetch(`http://localhost:8080/api/players/${playerId}/results`)
        .then(res => {
            return res.json()
        })
        .then(data => setResutls(data))
        .catch(console.error)
  
    }, [])
    if (!results) return <div>Loading...</div>

    return (
      <div>
        {
          results.map(res=>(
            <div>
                <h1>{res.game.name}</h1>
                {res.game.minigames>1
                ? [0,1,2].map(i=>(
                    <div>
                        <h2>{res.game.minigame[i].name}</h2>
                        <h3>{res.data.split(" ")[i]}</h3>
                    </div>
                ))
                : <div>
                    <h2>{res.game.minigame[0].name}</h2>
                    <h3>{res.data}</h3>
                </div>
                }
            </div>
          ))
        }
      </div>
    );
  }
  
  
  export default UserPoints;
  