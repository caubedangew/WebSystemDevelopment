import { Form } from "react-bootstrap/Form";
import { Button } from "react-bootstrap/Button";
import { useContext, useState } from "react";
import API, { authAPIs, endpoints } from "../../configs/API";
import cookie from "react-cookies";
import { MyDispatchContext, MyUserContext } from "../../context/MyContext";
import APIs from "../../configs/APIs";

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
            cookie.save("user", user.data);

            dispatch({
                "type": "login",
                "action": user.data
            });
        } catch (ex) {
            console.log(ex);
        }
    }

    return (
        <>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" placeholder="Tên tài khoản..." />
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </>
    );
}

export default Login;