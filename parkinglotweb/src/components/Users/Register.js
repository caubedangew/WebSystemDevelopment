import React from "react";
import { Alert, Button, Form } from "react-bootstrap";
import APIs, { endpoints } from "../../configs/APIs";
import { Navigate, useNavigate } from "react-router-dom";

const Register = () => {
    const [user, setUser] = React.useState({});
    const [confirmErr, setConfirmErr] = React.useState(false);
    const [failErr, setFailErr] = React.useState(false);
    const avatar = React.useRef();
    const nav = useNavigate();

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
        "label": "Email",
        "type": "email",
        "placeholder": "Nhập email...",
        "field": "email"
    },  {
        "label": "Số điện thoại",
        "type": "text",
        "placeholder": "Nhập số điện thoại...",
        "field": "phone_number"
    },{
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
    }]

    const setField = (value, field) => {
        setUser(current => {
            return { ...current, [field]: value }
        })
    }

    const register = async (e) => {
        e.preventDefault();

        try {

            if (user.confirm !== user.password)
                setConfirmErr(true);
            else {
                let form = new FormData();
                for (let k in user)
                    if (k !== "confirm")
                        form.append(k, user[k]);

                form.append('avatar', avatar.current.files[0]);

                let res = await APIs.post(endpoints["register"], form, {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                });

                console.log(res.data);

                if (res.status === 400)
                    setFailErr(true);
                else if (res.status === 201)
                    nav("/login");
            }
        } catch (ex) {
            console.log(ex.response);
        }
    }

    return (
        <Form method="post" onSubmit={register}>
            {confirmErr === true && <Alert className="alert alert-danger" >
                Xác nhận mật khẩu không chính xác!!!
            </Alert>}

            {failErr === true && <Alert className="alert alert-danger" >
                Đã tồn tại tên tài khoản này rồi!!!
            </Alert>}

            {items.map(item =>
                <Form.Group key={item.field} className="mb-3" controlId={`exampleForm.ControlInput${item.field}`}>
                    <Form.Label>{item.label}: </Form.Label>
                    <Form.Control type={item.type} placeholder={item.placeholder} value={user[item.field] || ''} onChange={e => setField(e.target.value, item.field)} />
                </Form.Group>
            )}
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea7">
                <Form.Label>Ảnh đại diện</Form.Label>
                <Form.Control accept=".png,.jpg" type="file" ref={avatar} />
            </Form.Group>
            <Button variant="primary" type="submit">Submit</Button>
        </Form>
    );
}

export default Register;