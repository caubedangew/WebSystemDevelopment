import moment from "moment";
import { Alert, Button, Form, Image, InputGroup, Spinner, Toast, ToastContainer } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import APIs, { authAPIs, endpoints } from "../configs/APIs";
import React from "react";
import { MyUserContext } from "../context/MyContext";

const Comment = () => {
    const { parkinglotId } = useParams("parkinglotId");
    const [comments, setComments] = React.useState(null);
    const [page, setPage] = React.useState(1);
    const user = React.useContext(MyUserContext);
    const [content, setContent] = React.useState("");
    const [error, setError] = React.useState(false);

    const loadComments = async () => {

        try {
            let url = `${endpoints["comment"]}?page=${page}&parkinglotId=${parkinglotId}`;
            let res = await APIs.get(url);

            if (page === 1)
                setComments(res.data);
            else
                setComments(current => [...current, ...res.data]); 
        } catch (ex) {
            console.error(ex);
        }
    }

    const postComment = (e) => {
        e.preventDefault();

        if (content === null || content.length === 0)
            setError(true);
        else {
            try {
                let res = authAPIs().post(endpoints["postComment"], {
                    "parkinglotId": parkinglotId,
                    "content": content
                })

                setComments(comments);


            } catch (ex) {
                console.error(ex);
            }
        }
    }

    const delComment = (e, commentId) => {
        e.preventDefault();

        if (window.confirm("Bạn chắc chắn xóa đánh giá này chứ???") === true) {
            try {
                let res = authAPIs().delete(endpoints["delComment"](commentId))

                console.log(res.status);

                setComments(comments);
            } catch (ex) {
                console.error(ex.response.message);
            }
        }
    }

    React.useEffect(() => {
        loadComments();
    }, [comments, page])

    if (comments === null)
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="visually-hidden">Loading...</span>
            </Spinner>
        </div>

    return (
        <>
            {comments !== null && <h1 className="text-info text-center">Đánh giá về bãi xe</h1>}
            {user !== null ? <>
                {error && <Alert variant="danger">Hãy điền nội dung trước khi đăng bình luận</Alert>}
                <Form method="post" onSubmit={postComment}>
                    <InputGroup className="mb-3" >
                        <InputGroup.Text className="border-0"><Image src={user.avatar} width={40} className="" /></InputGroup.Text>
                        <Form.Control as="textarea" value={content} onChange={(e) => setContent(e.target.value)} />
                        <Button variant="secondary" id="button-addon2" type="submit">Xác nhận</Button>
                    </InputGroup>
                </Form>
            </> : <div className="text-center alert alert-primary mb-3">Vui lòng <Link to={`/login?next=/parkinglot/${parkinglotId}/comments`} >đăng nhập</Link> để được thêm đánh giá!!!</div>
            }
            <ToastContainer className="position-static">
                {comments.length !== 0 ? comments.map(c =>
                    <Toast key={c.id} onClose={() => delComment(c.id)} >
                        <Toast.Header closeButton={c.userId.id === user?.id}>
                            <img src={c.userId.avatar} width={30} className="rounded me-2" alt={c.userId.avatar} />
                            <strong className="me-auto">{c.userId.username}</strong>
                            <small className="text-muted">{moment(c.createdDate).fromNow()}</small>
                        </Toast.Header>
                        <Toast.Body>{c.content}</Toast.Body>
                    </Toast>
                ) : <h3 className="text-info text-center">{page === 1 ? "Bãi đậu xe này hiện chưa có đánh giá nào" : ""}</h3>}
            </ToastContainer>
            {comments.length !== 0 && <Button className="btn btn-primary mt-3 justify-content-center" onClick={() => setPage(page + 1)}>Xem thêm</Button>}
        </>
    );
}

export default Comment;