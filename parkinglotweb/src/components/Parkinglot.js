import React from "react";
import APIs, { endpoints } from "../configs/APIs";
import { Button, Card, Col, Row, Spinner } from "react-bootstrap";
import { Link } from "react-router-dom";

const Parkinglot = () => {
    const [parkinglots, setParkinglots] = React.useState(null);
    const [page, setPage] = React.useState(1);
    const [address, setAddress] = React.useState("");
    const [loading, setLoading] = React.useState(false);

    const loadParkinglots = async () => {
        setLoading(true);
        try {
            let url = `${endpoints["parkinglot"]}?page=${page}&address=${address}`
            let res = await APIs.get(url);

            console.log(url);
            console.log(res.data);

            setParkinglots(res.data);

        } catch (ex) {
            console.log(ex);
        } finally {
            setLoading(false);
        }
    }

    React.useEffect(() => {
        loadParkinglots();
    }, [address, page])

    return (
        <>
            {loading ? <Spinner animation="border" role="status">
                <span className="visually-hidden">Loading...</span>
            </Spinner> : <Row>
                {parkinglots !== null && parkinglots.map(p =>
                    <Col md={2} xs={12} className="m-2">
                        <Card key={p.id} style={{ width: '13rem' }}>
                            <Card.Img variant="top" src="holder.js/100px180" />
                            <Card.Body>
                                <Card.Title>{p.address}</Card.Title>
                                <Card.Text>Số lượng chỗ đậu tối đa: {p.quantity}</Card.Text>
                                <Card.Text>Giá tiền đỗ xe mỗi giờ: {p.price}</Card.Text>
                                <Link to={`/${p.id}/parkingspace`} variant="primary">Xem các chỗ đậu xe</Link>
                            </Card.Body>
                        </Card>
                    </Col>)}
            </Row>}
        </>
    );
}

export default Parkinglot;