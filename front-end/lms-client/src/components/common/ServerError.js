import React from 'react';
import '../../css/ServerError.css';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

export default function ServerError() {
    return (
        <div className="server-error-page">
            <h1 className="server-error-title">
                500
            </h1>
            <div className="server-error-desc">
                Oops! Something went wrong at our Server. Why don't you try again?
            </div>
            <Link to="/"><Button className="server-error-go-back-btn" type="primary" size="large">Go Back</Button></Link>
        </div>
    );
}

