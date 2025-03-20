package nl.duckacademy.springollama.llm;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {
    @Bean
    public ChatClient defaultChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("you are a 18 years old student in Spain, you have to answers questions like that, finish all your phrases with dude")
                .build();
    }
}
