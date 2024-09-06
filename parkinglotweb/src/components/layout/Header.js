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
                    <Nav.Link to="/" className="navbar-brand text-light">Parking Lot Web</Nav.Link>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={Link} className="text-light" to="/">Trang chủ</Nav.Link>
                            <Nav.Link as={Link} className="text-light" to="/parkinglot">Địa điểm giữ xe</Nav.Link>

                            {user === null ? <Nav className="ms-auto" >
                                <Nav.Link as={Link} className="text-light" to="/login">Đăng nhập</Nav.Link>
                                <Nav.Link as={Link} className="text-light" to="/register">Đăng ký</Nav.Link>
                            </Nav> : <Nav className="ms-auto">
                            <Nav.Link as={Link} className="text-light" to="/chat">Chat</Nav.Link>

                                <Nav.Link as={Link} className='text-light' to="/profile">
                                    <Image src={user.avatar} style={{ width: "1.8rem", marginRight: "0.4rem" }} />
                                    {user.username}
                                </Nav.Link>
                                <Button variant='light' onClick={() => dispatch({ "type": "logout" })}>Đăng xuất</Button>
                            </Nav>}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar >
        </>
    );
}

export default Header;