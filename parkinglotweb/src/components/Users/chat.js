import { useState, useEffect, useRef, useContext } from "react";
import { database, ref, onValue, push } from "../../firebase/config";
import { MyUserContext } from "../../context/MyContext";
import { Navigate } from "react-router-dom";
import cookie from "react-cookies"

const Chat = () => {
  const [inpMessage, setIptMessage] = useState("");
  const [messages, setMessages] = useState([]);
  const input = useRef();
  const user = cookie.load("user");
  useEffect(() => {
    onValue(ref(database, "message"), (data) => {
      let getMsg = [];
      data.forEach((d) => {
        getMsg.push(d.val());
      });
      setMessages(getMsg);
    });
  }, []);
  const handleSendMessage = () => {
    push(ref(database, "message"), {
      name: user.username,
      message: inpMessage,
    });
    setIptMessage("");
    const inputElement = document.getElementById("message-input");
    if (inputElement) {
    inputElement.focus();
    }
  };

  if (user === null)
    return <Navigate to="/login" />

  return (
    <div>
      <h1>xin chào {user.username}</h1>
      <ul>
        {messages.map((msg, index) => {
          return (
            <li key={index}>
              <span>{msg.name}: </span>
              <span>{msg.message}</span>
            </li>
          );
        })}
      </ul>
      <input
        type="text"
        value={inpMessage}
        onChange={(e) => {
          setIptMessage(e.target.value);
        }}
        ref={input}
      />
      <button onClick={handleSendMessage}>Gửi</button>
    </div>
  );
}

export default Chat;