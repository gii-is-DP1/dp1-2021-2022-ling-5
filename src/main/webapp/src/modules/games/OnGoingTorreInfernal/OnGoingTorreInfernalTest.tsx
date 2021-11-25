import React from "react";
import AddPoints from "./OnGoingTorreInfernalAddPoints";
import DeleteGame from "./OnGoingTorreInfernalDelete";
import NewOnGoingGame from "./OnGoingTorreInfernalNew";
import NewCard from "./OnGoingTorreInfernalNewCard";

class OnGoingTest extends React.Component{
    constructor(props: any){
        super(props);

        this.handleDelete = this.handleDelete.bind(this);
        this.handleCreate = this.handleCreate.bind(this);
        this.handleAddPoints = this.handleAddPoints.bind(this);
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
    handleAddPoints(event: any){
        event.preventDefault();
        let form = event.target;
        let formData = new FormData(form);
        AddPoints(1, 1, formData);
        window.location.reload();
    }
    handleNewCard(){
        let form = new HTMLFormElement()
        form.setAttribute("playerId", "1")
        let formData = new FormData(form);
        NewCard(1, formData);
        window.location.reload();
    }

    render(){
        return(
            <div>
                <button onClick={this.handleNewCard}>New Center Card</button>
                <button onClick={this.handleDelete}>Delete Game</button>
                <form onSubmit={this.handleCreate}>
                    <input type="number" name="gameId"/>
                    <input type="submit" value="Submit"/>
                </form>
                <form onSubmit={this.handleAddPoints}>
                    <input type="number" name="points"/>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
        );
    }
}

export default OnGoingTest