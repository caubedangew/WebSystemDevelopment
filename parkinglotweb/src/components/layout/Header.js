import React from "react";
import { Button, Container, Image, Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../context/MyContext";

const Header = () => {
    const user = React.useContext(MyUserContext);
    const dispatch = React.useContext(MyDispatchContext);

    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary bg-primary mb-3">
                <Container>
                    <Link to="/" className="navbar-brand text-light">Parking Lot Web</Link>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Link className="nav-link text-light" to="/">Home</Link>
                            <Link className="nav-link text-light" to="/parkinglot">Địa điểm giữ xe</Link>
                            {user === null ? <>
                                <Link className="nav-link text-light" to="/login">Đăng nhập</Link>
                                <Link className="nav-link text-light" to="/register">Đăng ký</Link>
                            </> : <>
                                <Link className='nav-link text-white' to="/profile">
                                    Chào {user}!
                                </Link>
                                <Button variant='light' onClick={() => dispatch({ "type": "logout" })}>Đăng xuất</Button>
                            </>}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    );
}

export default Header;