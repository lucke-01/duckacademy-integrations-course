package nl.duckacademy.springollama.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class Llama2AiService {
    public final static Logger log = LoggerFactory.getLogger(Llama2AiService.class);

    private final OllamaChatModel chatModel;
    private final ChatClient defaultChatClient;

    public Llama2AiService(OllamaChatModel chatModel,ChatClient defaultChatClient) {
        this.chatModel = chatModel;
        this.defaultChatClient = defaultChatClient;
    }

    public String generateResponse(String prompt) {
        ChatResponse response = chatModel.call(
                new Prompt(prompt, OllamaOptions.builder()
                        .model("llama2")
                        .temperature(0.8).build()));

        return response.getResult().getOutput().getText();
    }

    public Flux<String> generateStreamResponse(String prompt) {
        return chatModel.stream(prompt);
    }

    public Flux<String> generateResponseRAG(String prompt) {
        return defaultChatClient.prompt().user(prompt).stream().content();
    }
}