import React from 'react';
import '../../css/ServerError.css';
import { Spinner } from 'react-bootstrap';

export default function LoadingIndicator() {

    return (
        <div style = {{display: 'block', textAlign: 'center', marginTop: 0}}>
            <Spinner animation="border" variant="success" />
            <Spinner animation="border" variant="danger" />
            <Spinner animation="border" variant="primary" />
        </div>
    );
}