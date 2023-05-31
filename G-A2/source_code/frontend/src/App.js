import { useState } from "react";
import ChatBubble from "./ChatBubble";
import axios from "axios";
import { useRef, useEffect } from "react";

function App() {
  const [text, setText] = useState("");
  const BOT_URL = "http://localhost:5005/webhooks/rest/webhook";
  const messageRef = useRef();

  let initialChats = [];

  const [chats, setChats] = useState(initialChats);

  useEffect(() => {
    if (messageRef.current) {
      messageRef.current.scrollIntoView({
        behavior: "smooth",
        block: "end",
        inline: "nearest",
      });
    }
  }, [chats]);

  const updateText = (event) => {
    setText(event.target.value);
  };

  const sendMessage = () => {
    if (text === "") return;
    const newChats = [
      ...chats,
      {
        id: 11,
        type: "query",
        time: new Date(),
        text: text,
      },
    ];
    setChats(newChats);
    getResponse(text, newChats);
    setText("");
  };

  const getResponse = async (msg, newChats) => {
    var bodyFormData = { message: msg };
    const response = axios.post(BOT_URL, bodyFormData);
    var messages = [];
    response
      .then((res) => {
        console.log(res);
        messages = [];
        res.data.map(
          (data) =>
            (messages = [
              ...messages,
              {
                id: 12,
                type: "reply",
                time: new Date(),
                text: data.text,
              },
            ])
        );
        setChats([...newChats, ...messages]);
      })
      .catch((error) => console.log("Error in response: " + error));
  };

  return (
    <div className="container">
      <div className="chats-div" id="chats-div" ref={messageRef}>
        {chats.map((chat) => (
          <ChatBubble key={chat.id} msg={chat} />
        ))}
        <div style={{ height: "100px" }} id="divider"></div>
      </div>
      <div id="input-div">
        <input
          type="text"
          name="msg"
          value={text}
          id="msg-input"
          onInput={updateText}
        />
        <button id="send-button" onClick={sendMessage}>
          Send
        </button>
      </div>
    </div>
  );
}

export default App;
