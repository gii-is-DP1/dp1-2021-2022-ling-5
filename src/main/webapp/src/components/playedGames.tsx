import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import playerService from '../services/player.service';

const gameList = (id:String)=>{

    const [games, setGames] = useState([]);
    id='1';

    useEffect(()=>{
        playerService.get(id)
        .then(response => {
            console.log('Printing games data', response.data);
            setGames(response.data.gamesPlayed);
          })
          .catch(error => {
            console.log('Something went wrong', error);
          })    
      }, []);

    return (
        <div className="container">
        <h3>List of Employees</h3>
        <hr/>
        <div>
          <Link to="/add" className="btn btn-primary mb-2">Add Employee</Link>
          <table className="table table-bordered table-striped">
            <thead className="thead-dark">
              <tr>
                <th>Name</th>
                <th>Location</th>
                <th>Department</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
            {/* {
              games.map(game => (
                <tr key={game.id}>
                  <td>{game.name}</td>
                  <td>{game.location}</td>
                  <td>{game.department}</td>
                </tr>
              ))
            } */}
            </tbody>
          </table>
          
        </div>
      </div>
    );
    }

export default gameList;