import React from "react";
import { Link, useParams } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";
import { Button, Col, Form, Image, Modal, OverlayTrigger, Row, Spinner, Tooltip } from "react-bootstrap";

const Parkingspace = () => {
    const { parkinglotId } = useParams("parkinglotId");
    const [parkingSpace, setParkingSpace] = React.useState(null);
    const [free, setFree] = React.useState(0);
    const [visible, setVisible] = React.useState(false);
    const [page, setPage] = React.useState(1);

    const loadParkingSpaces = async () => {
        try {
            let url = `${endpoints["parkingspace"]}?parkinglotId=${parkinglotId}`;

            let res = await APIs.get(url);

            setParkingSpace(res.data);
        } catch (ex) {
            console.log(ex);
        }
    }

    const bookingAndPayment = () => {
        
    }

    React.useEffect(() => {
        loadParkingSpaces();
    }, [free, page])

    if (parkingSpace === null)
        return
    <div className="d-flex justify-content-center">
        <Spinner animation="border" role="status">
            <span className="visually-hidden">Loading...</span>
        </Spinner>
    </div>

    return (
        <>
            <Row>
                {parkingSpace !== null && parkingSpace.map(p =>
                    <Col md={3} xs={12} key={p.id}>
                        <OverlayTrigger placement="right" delay={{ show: 100 }} overlay={(props) =>
                            <Tooltip id="button-tooltip" {...props}>
                                Nhấn vào để đặt chỗ
                            </Tooltip>}>
                            <Button disabled={p.status === "BUSY"} variant="light" onClick={() => setVisible(true)}>
                                <Image src={`/static/images/${p.status === "FREE" ? "greenmotor.png" : "redmotor.png"}`}></Image>
                                <div>Vị trí số {p.stt}</div>
                            </Button>
                        </OverlayTrigger>
                    </Col>
                )}
            </Row>

            <Modal show={visible} size="lg" aria-labelledby="contained-modal-title-vcenter" centered>
                <Modal.Header>
                    <Modal.Title id="contained-modal-title-vcenter">
                        Đặt chỗ
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form method="post" onSubmit="">
                        <Form.Group>
                            <Form.Label>Thời gian đặt chỗ</Form.Label>
                            <Form.Control type="datetime-local" placeholder="Chọn thời gian đặt chỗ" className="mb-3" />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Thời gian giữ xe</Form.Label>
                            <Form.Control type="number" placeholder="Nhập số giờ bạn muốn giữ xe" />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button type="submit" onClick="">Tiếp tục</Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default Parkingspace;