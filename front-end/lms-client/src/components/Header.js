import React from "react";
import {Nav, Navbar, NavDropdown} from "react-bootstrap";

import BrandLogo from "../images/lms512.png"
import {Link} from "react-router-dom";
import { faUserCircle, faSignOutAlt } from '@fortawesome/free-solid-svg-icons'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome/index.es";
import '../css/Header.css';

export default function Header() {

    return (
        <Navbar collapseOnSelect expand="lg" className="nav-bar" sticky="top">
            <Link to={""} className="navbar-brand">
                <img src={BrandLogo} width="50" height="50" alt="LMS"/>
            </Link>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="mr-auto">
                    <Link to={""} className="nav-link nav-bar-link">Home</Link>
                    <Link to={"#features"} className="nav-link nav-bar-link">Features</Link>
                    <Link to={"#catalogues"} className="nav-link nav-bar-link">Catalogues</Link>
                    <NavDropdown title="Sections" id="nav-dropdown" className="nav-bar-link">
                        <Link to="#action/3.2" className="nav-bar-action nav-link dropdown-item">Books</Link>
                        <Link to="#action/3.2" className="nav-bar-action nav-link dropdown-item">Journals</Link>
                        <Link to="#action/3.2" className="nav-bar-action nav-link dropdown-item">Daily</Link>
                        <NavDropdown.Divider />
                        <Link to="#action/3.2" className="nav-bar-action nav-link dropdown-item">Personal</Link>
                    </NavDropdown>
                </Nav>
                <Nav>
                    <Link to={"/login"} className="nav-link nav-bar-action">Log In</Link>
                    <Link to={"/register"} className="nav-link nav-bar-action">Register</Link>

                    <NavDropdown alignRight title={<FontAwesomeIcon size="2x" className="drop" icon={faUserCircle}/>}  id="collasible-nav-dropdown">
                        <div className="profile">
                            <div className="user-full-name-info">
                                Habeeb Okunade
                            </div>
                            <div className="username-info">
                                @habeebCycle
                            </div>
                        </div>
                        <NavDropdown.Divider />
                        <Link to="#action/3.2" className="nav-bar-action nav-link dropdown-item">Another action</Link>
                        <Link to="#action/3.3" className="nav-bar-action nav-link dropdown-item">Something</Link>
                        <NavDropdown.Divider />
                        <Link to="#action/3.4" className="nav-bar-action nav-link dropdown-item">Logout</Link>
                    </NavDropdown>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}