package duckacademy.nl.websocket.ws;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 import org.springframework.web.socket.TextMessage;
 import org.springframework.web.socket.WebSocketSession;
 import org.springframework.web.socket.handler.TextWebSocketHandler;

 import java.io.IOException;
 import java.util.List;
 import java.util.Map;
 import java.util.concurrent.CopyOnWriteArrayList;
 import com.google.gson.Gson;
 import com.google.gson.JsonObject;
 import com.google.gson.JsonParser;

@Component
public class SocketHandler extends TextWebSocketHandler {
    Logger logger = LoggerFactory.getLogger(SocketHandler.class);
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    //We receive a message
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        logger.info("IMPORTANT new message RECEIVED");
        final String messageString = message.getPayload();
        for(WebSocketSession webSocketSession : sessions) {
            logger.info("IMPORTANT sending messages to all sessions");
            webSocketSession.sendMessage(new TextMessage(messageString));
            //Map value = new Gson().fromJson(message.getPayload(), Map.class);
            //webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
        }
        //IMPORTANT send message to only the one who wrote to us:
        session.sendMessage(new TextMessage(messageString));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //added session to the array of sessions
        logger.info("IMPORTANT new connection CREATED");
        sessions.add(session);
    }
}