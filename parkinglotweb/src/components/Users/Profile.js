import cookie from "react-cookies";
import { Button, Card, Col, Form, InputGroup, Row } from "react-bootstrap";
import { MyDispatchContext, MyUserContext } from "../../context/MyContext";
import React, { useContext, useRef } from "react";
import { authAPIs, endpoints } from "../../configs/APIs";
import { Navigate } from "react-router-dom";

const Profile = () => {
    const [user, setUser] = React.useState(React.useContext(MyUserContext));
    const dispatch = useContext(MyDispatchContext);
    const [disabled, setDisabled] = React.useState(true);
    const [loading, setLoading] = React.useState(false);
    const avatar = useRef();
    const [avatarURL, setAvatarURL] = React.useState(null);

    const setItem = (value, field) => {
        setUser({ ...user, [field]: value })
    }

    const changeAvatar = (e) => {
        let file = e.target.files[0];
        if (file) {
            let url = URL.createObjectURL(file);
            setAvatarURL(url);
        }
    }

    if (user === null)
        return <Navigate to="/login" />

    const update = async (e) => {
        e.preventDefault();
        setLoading(true);

        try {
            let form = new FormData();

            for (let k in user)
                form.append(k, user[k]);

            form.append("avatar", avatar.current.files[0])

            let res = await authAPIs().post(endpoints["updateUser"], form, {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            });

            console.log(res.data);

            let users = await authAPIs().get(endpoints["current-user"]);
            console.log(users.data);
            cookie.save("user", users.data);

            dispatch({
                "type": "login",
                "payload": users.data
            })

            setDisabled(true);
        } catch (ex) {
            console.log(ex);
        } finally {
            setLoading(false);
        }
    }

    return (
        <>
            {user !== null && <Row>
                <Col md={4} xs={12}>
                    <Card className="text-center">
                        <Form.Group className="d-flex justify-content-center mt-3">
                            <Form.Label>
                                <Card.Img src={avatarURL || user.avatar} style={{ width: "10rem", height: "10rem" }} />
                                <Form.Control disabled={disabled} accept=".png,.jpeg" type="file" ref={avatar}
                                 style={{ display: "none" }} name="avatar" id="avatar" onChange={changeAvatar}/>
                            </Form.Label>
                        </Form.Group>
                        <Card.Body>
                            <Card.Text>
                                {user.lastName} {user.firstName}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={8} xs={12} >
                    <div className="d-flex justify-content-end mb-3">
                        <Button onClick={() => setDisabled(false)}>Cập nhật thông tin</Button>
                    </div>
                    <Form method="post" onSubmit={update}>
                        <InputGroup className="mb-3">
                            <InputGroup.Text>Họ và tên</InputGroup.Text>
                            <Form.Control aria-label="Last name" value={user["lastName"]} disabled={disabled} onChange={(e) => setItem(e.target.value, "lastName")} />
                            <Form.Control aria-label="First name" value={user["firstName"]} disabled={disabled} onChange={(e) => setItem(e.target.value, "firstName")} />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <InputGroup.Text>Số điện thoại:</InputGroup.Text>
                            <Form.Control aria-label="Phone-number" value={user["phoneNumber"] || ""} disabled={disabled} onChange={(e) => setItem(e.target.value, "phoneNumber")} />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <InputGroup.Text>Email:</InputGroup.Text>
                            <Form.Control aria-label="Email" value={user["email"] || ""} disabled={disabled} onChange={(e) => setItem(e.target.value, "email")} />
                        </InputGroup>
                        {!disabled && <div className="d-flex justify-content-center">
                            <Button variant="primary" type="submit" disabled={loading}>
                                Thay đổi
                            </Button>
                        </div>}
                    </Form>
                </Col>
            </Row>}
        </>
    );
}

export default Profile;