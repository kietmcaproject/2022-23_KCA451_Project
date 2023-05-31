function ChatBubble(props) {
  return (
    <div>
      {props.msg.type === "query" ? (
        <QueryBubble msg={props.msg} />
      ) : (
        <ReplyBubble msg={props.msg} />
      )}
    </div>
  );
}

function QueryBubble(props) {
  return (
    <div className="query-div">
      <div className="query-bubble">{props.msg.text}</div>
    </div>
  );
}

function ReplyBubble(props) {
  return (
    <div className="reply-div">
      <div className="reply-bubble">{props.msg.text}</div>
    </div>
  );
}

export default ChatBubble;
