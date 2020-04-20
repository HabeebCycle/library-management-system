import React, {useState} from 'react';
import {Card, Form, Col, Button} from "react-bootstrap";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../css/Register.css'
import { faIdCardAlt, faIdCard } from '@fortawesome/free-solid-svg-icons'

export default function Register() {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [confirmPass, setConfirmPass] = useState("");

    return(
        <Card className="register">
            <Card.Header>
                <span className="header"><FontAwesomeIcon icon={faIdCardAlt}/>  Register </span>
            </Card.Header>
            <Form>
                <Card.Body>
                    <Form.Row>
                        <Form.Group as={Col} md="6" controlId="firstnameId">
                            <Form.Label> First Name</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text" name="firstname"
                                          onChange={e => setFirstName(e.target.value)}
                                          className={"bg-light text-dark"}
                                          placeholder="First Name" />
                        </Form.Group>
                        <Form.Group as={Col} md="6" controlId="lastnameId">
                            <Form.Label> Last Name</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text" name="lastname"
                                          onChange={e => setLastName(e.target.value)}
                                          className={"bg-light text-dark"}
                                          placeholder="Last Name" />
                        </Form.Group>
                    </Form.Row>
                    <Form.Row>
                        <Form.Group as={Col} md="6" controlId="emailId">
                            <Form.Label> Email Address</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="email" name="email"
                                          onChange={e => setEmail(e.target.value)}
                                          className={"bg-light text-dark"}
                                          placeholder="Email Address" />
                        </Form.Group>
                        <Form.Group as={Col} md="6" controlId="usernameId">
                            <Form.Label> Username</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text" name="username"
                                          onChange={e => setUserName(e.target.value)}
                                          className={"bg-light text-dark"}
                                          placeholder="Username" />
                        </Form.Group>
                    </Form.Row>
                    <Form.Row>
                        <Form.Group as={Col} md="6" controlId="passwordId">
                            <Form.Label> Password</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="password" name="password"
                                          onChange={e => setPassword(e.target.value)}
                                          className={"bg-light text-dark"}
                                          placeholder="Password" />
                        </Form.Group>
                        <Form.Group as={Col} md="6" controlId="confirmId">
                            <Form.Label> Confirm Password</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="password" name="confirm"
                                          onChange={e => setConfirmPass(e.target.value)}
                                          className={"bg-light text-dark"}
                                          placeholder="Type Password Again" />
                        </Form.Group>
                    </Form.Row>
                </Card.Body>
                <Card.Footer style={{"textAlign":"right"}}>
                    <Button size="sm" variant="success" type="submit" className="reg-button">
                        <FontAwesomeIcon icon={faIdCard} /> {" "} Register
                    </Button>
                </Card.Footer>
            </Form>
        </Card>
    );
}