import React from 'react';
import './App.css';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Header from "./components/Header";
import {Col, Container, Row} from "react-bootstrap";
import Home from "./components/Home";
import HomeAuth from "./components/HomeAuth";
import Footer from "./components/Footer";
import Login from "./components/Login";
import Register from "./components/Register";
import BottomScroll from "./components/common/BottomScroll";

function App() {
  return (
    <Router>
        <Header/>
        <Container>
            <Row>
                <Col lg={12}>
                    <Switch>
                        <Route path="/" exact component={Home} />
                        <Route path="/log" component={HomeAuth} />
                        <Route path="/login" component={Login} />
                        <Route path="/register" component={Register} />
                    </Switch>
                </Col>
            </Row>
            <BottomScroll/>
            <BottomScroll/>
        </Container>
        <Footer/>
    </Router>
  );
}

export default App;
