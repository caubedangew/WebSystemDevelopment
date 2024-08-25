import React from "react";
import { Alert, Button, Form } from "react-bootstrap";
import APIs, { endpoints } from "../../configs/APIs";
import { Navigate } from "react-router-dom";

const Register = () => {
    const [user, setUser] = React.useState({});
    const [confirmErr, setConfirmErr] = React.useState(false);
    const [failErr, setFailErr] = React.useState(false);

    let items = [{
        "label": "Tên",
        "type": "text",
        "placeholder": "Nhập tên của bạn...",
        "field": "first_name"
    }, {
        "label": "Họ và tên lót",
        "type": "text",
        "placeholder": "Nhập họ và tên lót của bạn...",
        "field": "last_name"
    }, {
        "label": "Tên tài khoản",
        "type": "text",
        "placeholder": "Nhập tên tài khoản...",
        "field": "username"
    }, {
        "label": "Mật khẩu",
        "type": "password",
        "placeholder": "Nhập mật khẩu...",
        "field": "password"
    }, {
        "label": "Xác nhận mật khẩu",
        "type": "password",
        "placeholder": "Xác nhận mật khẩu đã nhập...",
        "field": "confirm"
    }, {
        "label": "Chọn ảnh đại diện",
        "type": "file",
        "field": "avatar"
    }]

    const setField = (value, field) => {
        setUser(current => {
            return {...current, [field]: value}
        })
    }

    const register = async (e) => {
        e.preventDefault();

        try {

            if (user.confirm != user.password)
                setConfirmErr(true);
            else {
                let form = new FormData();
                for (let k in user)
                    if (k !== "confirm")
                        form.append(k, user[k]);

                let res = await APIs.post(endpoints["register"], form, {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                });

                console.log(res.data);

                if (res.status === 400)
                    setFailErr(true);
                else if (res.status === 201)
                    return <Navigate to="/login" />
            }
        } catch (ex) {
            console.log(ex);
        }
    }

    return (
        <Form method="post" onEncrypted="multipart/form-data" onSubmit={register}>
            {confirmErr === true && <Alert className="alert alert-danger" >
                Xác nhận mật khẩu không chính xác!!!
            </Alert>}

            {failErr === true && <Alert className="alert alert-danger" >
                Đã tồn tại tên tài khoản này rồi!!!
            </Alert>}

            {items.map(item =>
                <Form.Group className="mb-3" controlId={`exampleForm.ControlInput${item.field}`}>
                    <Form.Label>{item.label}: </Form.Label>
                    <Form.Control type={item.type} placeholder={item.placeholder} value={user[item.field]} onChange={e => setField(e.target.value, item.field)}/>
                </Form.Group>
            )}
            <Button variant="primary" type="submit">Submit</Button>
        </Form>
    );
}

export default Register;