import { useEffect, useState } from "react";
import { Figure, Table } from "react-bootstrap";
import userAPI from "./userAPI";
import figures from '../../images/figures/figures'

function Ranking() {

    const [results, setResults] = useState<any[]>([]);
    useEffect(() => {
        fetch(`http://localhost:8080/api/statistics/top10ranking`)
        .then(res => {
            return res.json()
        })
        .then(data => setResults(data))
        .catch(console.error)
        
    }, [])

    const [user, setUser] = useState<any>();
    useEffect(() => {
    var userData: any = localStorage.getItem("userData");
    if (userData !== null) userData = JSON.parse(userData)
    const playerId = userData.id
    fetch(`http://localhost:8080/api/statistics/ranking/${playerId}`)
      .then(res => {
        return res.json()
      })
      .then(data => setUser(data))
      .catch(console.error)

  }, [])
   
    if (!results) return <div>Loading...</div>
    if (!user) return <div>Loading...</div>


    return (
    <div className="container">
        <h1>RANKING</h1>
        <h3>YOUR POSITION IS #{user.first} WITH {user.second.points} POINTS</h3>

        <Table striped bordered hover responsive="md">
        <thead>
            <tr>
            <th>#</th>
            <th>Username</th>
            <th>Points</th>
            </tr>
        </thead>
        <tbody>
            {results.map((e, ind) => (
                <tr> 
                    <td>{ind+1}</td>
                     {/* <img src={figure(e.imageName)} width='15px' height='15px'/>  */}
                    <td>{e.nickname}</td>
                    <td>{e.points}</td>
                </tr> 
            ))}

        </tbody>
        </Table>
    </div>


    );

}
export default Ranking;