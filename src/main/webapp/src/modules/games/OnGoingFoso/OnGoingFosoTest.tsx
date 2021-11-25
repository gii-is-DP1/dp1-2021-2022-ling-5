import React from "react";
import DeleteGame from "./OnGoingFosoDelete";
import NewOnGoingGame from "./OnGoingFosoNew";
import NewCard from "./OnGoingFosoChangeCard";

class OnGoingTest extends React.Component{
    constructor(props: any){
        super(props);

        this.handleDelete = this.handleDelete.bind(this);
        this.handleCreate = this.handleCreate.bind(this);
        this.handleNewCard = this.handleNewCard.bind(this);
    }

    handleDelete(){
        DeleteGame(1);
        window.location.reload();
    }
    handleCreate(event: any){
        event.preventDefault();
        let form = event.target;
        let formData = new FormData(form);
        NewOnGoingGame(formData);
        window.location.reload();
    }
    handleNewCard(event: any){
        event.preventDefault();
        let form = event.target;
        let formData = new FormData(form);
        NewCard(1,formData.get('playerId'));
        window.location.reload();
    }

    render(){
        return(
            <div>
                <button onClick={this.handleDelete}>Delete Game</button>
                <form onSubmit={this.handleCreate}>
                    <input type="number" name="gameId"/>
                    <input type="submit" value="Submit"/>
                </form>
                <form onSubmit={this.handleNewCard}>   
                    <input type="number" name="playerId"/>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
        );
    }
}

export default OnGoingTest