import React, {useState} from 'react';
import {Card, Form, Col, Button} from "react-bootstrap";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../css/Login.css'
import { faUserLock, faSignInAlt } from '@fortawesome/free-solid-svg-icons'

export default function Login() {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    return(
        <Card className="login">
            <Card.Header>
                <span className="header"><FontAwesomeIcon icon={faUserLock}/>  Login </span>
            </Card.Header>
            <Form>
                <Card.Body>
                    <Form.Row>
                        <Form.Group as={Col} md="12" controlId="usernameId">
                            <Form.Label> Username/Email</Form.Label>
                            <Form.Control required autoComplete="off"
                              type="text" name="username"
                              onChange={e => setUserName(e.target.value)}
                              className={"bg-light text-dark"}
                              placeholder="Username or Email Address" />
                        </Form.Group>
                    </Form.Row>
                    <Form.Row>
                        <Form.Group as={Col} md="12" controlId="passwordId">
                            <Form.Label> Password</Form.Label>
                            <Form.Control required autoComplete="off"
                              type="password" name="password"
                              onChange={e => setPassword(e.target.value)}
                              className={"bg-light text-dark"}
                              placeholder="Password" />
                        </Form.Group>
                    </Form.Row>
                </Card.Body>
                <Card.Footer style={{"textAlign":"right"}}>
                    <Button size="sm" variant="success" type="submit" className="log-button">
                        <FontAwesomeIcon icon={faSignInAlt} /> {" "} Login
                    </Button>
                </Card.Footer>
            </Form>
        </Card>
    );
}