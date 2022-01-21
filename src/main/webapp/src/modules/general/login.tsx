import React from "react";
import { Button, Form, Row } from "react-bootstrap";
import authApi from "../user/authAPI";
import token from "../user/token";

class Login extends React.Component<{}, { nickname: string, password: string }>{
    constructor(props: any) {
        super(props);
        this.state = { nickname: '', password: '' }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event: any) {
        const value = event.target.value;
        this.setState({
            ...this.state,
            [event.target.name]: value
        });
    }

    handleSubmit(event: any) {
        authApi.login(this.state).then(logdata => {
            if (logdata.id !== undefined) {
                let id = logdata.id;
                let rol = logdata.rol;
                token.login(id, rol);
            }
        }).catch(error => console.log(error));
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Form.Group className="col-md-4">
                        <Form.Label>
                            Nickname:
                        </Form.Label>
                        <Form.Control name="nickname" required  type="text" placeholder={this.state.nickname} onChange={this.handleChange}>
                        </Form.Control>
                    </Form.Group>
                </Row>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Form.Group className="col-md-4">
                        <Form.Label>
                            Password:
                        </Form.Label>
                        <Form.Control name="password" required type="password" placeholder={this.state.password} onChange={this.handleChange}>
                        </Form.Control>
                    </Form.Group>
                </Row>
                <Row>
                    <p></p>
                </Row>
                <Row className="text-center">
                    <div className="col-md-4"></div>
                    <Button className="col-md-4" variant="primary" type="submit">
                        Log in
                    </Button>
                </Row>
            </form>
        )
    }
}

export default Login;