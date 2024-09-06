import { useContext, useState } from "react";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";
import cookie from "react-cookies";
import { MyDispatchContext, MyUserContext } from "../../context/MyContext";
import { Form, Button } from "react-bootstrap";
import { Navigate, useSearchParams } from "react-router-dom";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);
    const [loading, setLoading] = useState(false);
    const [q, ] = useSearchParams();

    const login = async (e) => {
        e.preventDefault();

        setLoading(true);
        try {
            let res = await APIs.post(endpoints["login"], {
                "username": username,
                "password": password
            });

            cookie.save("access-token", res.data);

            let user = await authAPIs().get(endpoints["current-user"]);

            setTimeout(100);
            
            cookie.save("user", user.data);

            dispatch({
                "type": "login",
                "payload": cookie.load("user")
            });

        } catch (ex) {
            console.log(ex.response.data);
        } finally {
            setLoading(false);
        }
    }

    if (user !== null)
        return <Navigate to={`${q.get("next") !== null ? q.get("next") : "/"}`} />

    return (
        <>
            <Form method="post" onSubmit={login}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" placeholder="Tên tài khoản..." value={username || ""} onChange={e => setUsername(e.target.value)}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" value={password || ""} onChange={e => setPassword(e.target.value)}/>
                </Form.Group>
                <Button disabled={loading} variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </>
    );
}

export default Login;