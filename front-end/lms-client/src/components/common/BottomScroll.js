import React from 'react';
import {Row, Col, Card, Carousel, Figure} from "react-bootstrap";


export default function BottomScroll() {
    return (
        <div className={"bottom-row"}>
            <Carousel>
                <Carousel.Item>
                    <Card>
                        <Row>
                            <Col xs>1</Col>
                            <Col xs>2</Col>
                            <Col xs>3</Col>
                        </Row>
                    </Card>
                </Carousel.Item>
                <Carousel.Item>
                    <Card>
                        <Row>
                            <Col xs>1</Col>
                            <Col xs>2</Col>
                            <Col xs>3</Col>
                        </Row>
                    </Card>
                </Carousel.Item>
            </Carousel>
        </div>
    )
}