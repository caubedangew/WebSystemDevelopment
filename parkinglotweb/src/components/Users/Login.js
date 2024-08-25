import { useContext, useState } from "react";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";
import cookie from "react-cookies";
import { MyDispatchContext, MyUserContext } from "../../context/MyContext";
import { Form, Button } from "react-bootstrap";
import { Navigate } from "react-router-dom";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    const login = async (e) => {
        e.preventDefault();

        try {
            let res = await APIs.post(endpoints["login"], {
                "username": username,
                "password": password
            });

            cookie.save("access-token", res.data);

            let user = await authAPIs().get(endpoints["current-user"]);
            console.log(user.data);
            cookie.save("user", user.data);

            dispatch({
                "type": "login",
                "action": user.data
            });

        } catch (ex) {
            console.log(ex);
        }
    }

    if (user !== null)
        return <Navigate to="/" />

    return (
        <>
            <Form method="post" onSubmit={login}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" placeholder="Tên tài khoản..." value={username} onChange={e => setUsername(e.target.value)}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)}/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </>
    );
}

export default Login;