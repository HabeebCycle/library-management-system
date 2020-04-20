import React from 'react';
import {Jumbotron} from "react-bootstrap";


export default function Home() {
    return(
        <Jumbotron className="bg-white text-dark home-card">
            <h1>LMS</h1>
            <blockquote className="blockquote mb-0">
                <p>
                    Library Management System
                </p>
                <footer className="blockquote-footer">
                    Online library management solution for community library.
                </footer>
            </blockquote>
        </Jumbotron>
    );
}