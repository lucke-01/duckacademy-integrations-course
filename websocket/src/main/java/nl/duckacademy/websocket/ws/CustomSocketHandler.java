package nl.duckacademy.websocket.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class CustomSocketHandler  extends TextWebSocketHandler {
    Logger log = LoggerFactory.getLogger(CustomSocketHandler.class);

    private List<WebSocketSession> sessions = new ArrayList<>();

    //cada 5 segundos ejecutamos este codigo
    //servidor inicia peticion a cliente
    @Scheduled(fixedRate = 5000)
    public void notifyTime() throws IOException {
        String current = new SimpleDateFormat("HH:mm:ss").format(new Date());
        if (!sessions.isEmpty()) {
            for (WebSocketSession session : sessions) {
                session.sendMessage(new TextMessage(current));
            }
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //recibimos mensaje
        log.info("Cliente envia mensaje");
        String mensaje = message.getPayload();
        log.info("mensaje: "+mensaje);
        //contestamos
        session.sendMessage(new TextMessage("recibido"));
    }
    //conexion establecida
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        log.info("conexion establecida");
    }
}
