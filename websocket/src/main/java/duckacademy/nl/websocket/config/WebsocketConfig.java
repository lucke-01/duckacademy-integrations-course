package duckacademy.nl.websocket.config;


import duckacademy.nl.websocket.ws.SocketHandler;
import org.springframework.context.annotation.Configuration;
 import org.springframework.web.socket.config.annotation.EnableWebSocket;
 import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
 import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
    private final SocketHandler socketHandler;
    public WebsocketConfig(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //websocket will connect to: ws://localhost:8080/name
        registry.addHandler(socketHandler, "/name");
    }
}