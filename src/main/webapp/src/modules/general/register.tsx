import React from "react";
import { Button, Form, Row } from "react-bootstrap";
import authApi from "../user/authAPI";
import token from "../user/token";

class Register extends 
    React.Component<{}, {name:string, surname:string, email:string,nickname:string ,password:string, password2:string}>{
        constructor(props:any){
            super(props)
            this.state = {name:'', surname:'', email:'', nickname:'', password:'', password2:''};

            this.handleChange = this.handleChange.bind(this);
            this.handleSubmit = this.handleSubmit.bind(this);
        }

    handleChange(event:any){
        const value = event.target.value;
        this.setState({
            ...this.state,
            [event.target.name]: value
        });
    }
    handleSubmit(event:any){
        if(this.state.password===this.state.password2){
            const data = {name: this.state.name, surname: this.state.surname,
                email:this.state.email, nickname:this.state.nickname ,password:this.state.password};
            authApi.register(data).then(logdata => {
                if(logdata.id!==undefined){
                    let id = logdata.id;
                    let rol = logdata.rol;
                    token.login(id, rol);
                }
            }).catch(error=>alert(error));
        } else{
            console.log("password doesn't match");
        }
        event.preventDefault();
    }

    render(){
        return(
            <form onSubmit={this.handleSubmit}>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Form.Group className="col-md-4">
                        <Form.Label>
                            Name:
                        </Form.Label>
                        <Form.Control type="text" name="name" value={this.state.name} onChange={this.handleChange}></Form.Control>
                    </Form.Group>
                </Row>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Form.Group className="col-md-4">
                        <Form.Label>Surname: </Form.Label>
                        <Form.Control type="text" name="surname" value={this.state.surname} onChange={this.handleChange}></Form.Control>
                    </Form.Group>
                </Row>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Form.Group className="col-md-4">
                        <Form.Label>Email: </Form.Label>
                        <Form.Control type="text" name="email" value={this.state.email} onChange={this.handleChange}></Form.Control>
                    </Form.Group>
                </Row>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Form.Group className="col-md-4">
                        <Form.Label>Nickname: </Form.Label>
                        <Form.Control type="text" name="nickname" value={this.state.nickname} onChange={this.handleChange}></Form.Control>
                    </Form.Group>
                </Row>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Form.Group className="col-md-4">
                        <Form.Label>Password: </Form.Label>
                        <Form.Control type="password" name="password" value={this.state.password} onChange={this.handleChange}></Form.Control>
                    </Form.Group>
                </Row>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Form.Group className="col-md-4">
                        <Form.Label>Repeat password: </Form.Label>
                        <Form.Control type="password" name="password2" value={this.state.password2} onChange={this.handleChange}></Form.Control>
                    </Form.Group>
                </Row>
                <Row>
                    <p></p>
                </Row>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Button className="col-md-4" variant="primary" type="submit">
                        Register
                    </Button>
                </Row>
            </form>
        );
    }
}

export default Register