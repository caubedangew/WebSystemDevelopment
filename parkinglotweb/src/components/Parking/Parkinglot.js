import React from "react";
import APIs, { endpoints } from "../../configs/APIs";
import { Button, Card, Col, Dropdown, DropdownButton, Form, InputGroup, Row, Spinner } from "react-bootstrap";
import { Link } from "react-router-dom";
import IconArrowLeftCircle from "../Icons/IconArrowLeftCircle";
import IconArrowRightCircle from "../Icons/IconArrowRightCircle";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowDown, faArrowUp } from "@fortawesome/free-solid-svg-icons";

const Parkinglot = () => {
    const [parkinglots, setParkinglots] = React.useState(null);
    const [page, setPage] = React.useState(1);
    const [address, setAddress] = React.useState("");
    const [price, setPrice] = React.useState("");

    const loadParkinglots = async () => {
        try {
            let url = `${endpoints["parkinglot"]}?page=${page}&address=${address}&price=${price}`
            let res = await APIs.get(url);

            console.log(url);
            console.log(res.data);

            setParkinglots(res.data);

        } catch (ex) {
            console.log(ex);
        }
    }

    const search = (value, callback) => {
        setPage(1);
        callback(value)
    }

    React.useEffect(() => {
        loadParkinglots();
    }, [address, page, price])

    if (parkinglots === null)
        return
    <div className="d-flex justify-content-center">
        <Spinner animation="border" role="status">
            <span className="visually-hidden">Loading...</span>
        </Spinner>
    </div>

    return (
        <>
            <InputGroup className="mb-3">
                <DropdownButton variant="outline-primary" title="Keywords" id="input-group-dropdown-1">
                    <Dropdown.Item>Địa chỉ</Dropdown.Item>
                </DropdownButton>
                <Form.Control type="text" placeholder="Tìm chỗ đỗ xe theo địa chỉ" className="mr-sm-2" value={address} onChange={e => search(e.target.value, setAddress)} />
                <Button value={price} onClick={() => setPrice(price !== "asc" ? "asc" : "desc")}>
                    Lọc theo giá 
                    {price !== ""? price === "asc"? <FontAwesomeIcon icon={faArrowUp} /> : <FontAwesomeIcon icon={faArrowDown} /> : ""}
                </Button>
            </InputGroup>
            <Row>
                <Col className="d-flex justify-content-center align-items-center" md={1}>
                    <Button className="rounded-circle" disabled={page === 1} onClick={() => setPage(page - 1)}><IconArrowLeftCircle /></Button>
                </Col>
                <Col md={10} xs={12} > <Row >
                    {parkinglots !== null && parkinglots.map(p =>
                        <Col md={4} xs={12} className="d-flex justify-content-center">
                            <Card key={p.id} style={{ width: '100%' }}>
                                <Card.Img variant="top" src={p.thumbnail || "/static/images/thumbnail.jpg"} height={200} />
                                <Card.Body>
                                    <Card.Title>{p.address}</Card.Title>
                                    <Card.Text>Số lượng chỗ: {p.quantity}</Card.Text>
                                    <Card.Text>Tiền đỗ xe/giờ: {p.price} VNĐ</Card.Text>
                                    <div className="d-flex justify-content-center">
                                        <Link className="btn btn-primary mr-2" to={`/parkinglot/${p.id}/parkingspace`} variant="primary">Xem các chỗ đỗ</Link>
                                        <Link className="btn btn-secondary" to={`/parkinglot/${p.id}/comments`} variant="secondary">Đánh giá</Link>
                                    </div>
                                </Card.Body>
                            </Card>
                        </Col>)}
                </Row></Col>
                <Col md={1} className="d-flex justify-content-center align-items-center">
                    <Button disabled={parkinglots.length < 3} className="rounded-circle" onClick={() => setPage(page + 1)}><IconArrowRightCircle /></Button>
                </Col>
            </Row>
        </>
    );
}

export default Parkinglot;