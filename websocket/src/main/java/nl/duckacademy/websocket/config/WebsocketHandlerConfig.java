package nl.duckacademy.websocket.config;

import nl.duckacademy.websocket.ws.CustomSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketHandlerConfig implements WebSocketConfigurer {
    @Autowired
    private CustomSocketHandler customSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customSocketHandler, "/helloworld");
    }
}
