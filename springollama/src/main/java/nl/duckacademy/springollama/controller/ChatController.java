package nl.duckacademy.springollama.controller;


import nl.duckacademy.springollama.feignintegrator.Llama2ClientImpl;
import nl.duckacademy.springollama.service.Llama2AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/api")
public class ChatController {
    Logger log = LoggerFactory.getLogger(ChatController.class);
    private final OllamaChatModel chatModel;
    private final Llama2AiService llama2AiService;
    private final Llama2ClientImpl llama2ClientImpl;

    public ChatController(OllamaChatModel chatModel, Llama2AiService llama2AiService, Llama2ClientImpl llama2ClientImpl) {
        this.chatModel = chatModel;
        this.llama2AiService = llama2AiService;
        this.llama2ClientImpl = llama2ClientImpl;
    }

    @GetMapping("/ai/generate")
    public String generateResponse(@RequestParam(value = "prompt", defaultValue = "what is java") String prompt) {
        log.info("Sending prompt:: {} to Llama2 model ", prompt);
        return llama2AiService.generateResponse(prompt);
    }

    @GetMapping("/ai/generateStream")
    public Flux<String> generateStreamResponse(@RequestParam(value = "message", defaultValue = "what is python") String message) {
        log.info("Sending prompt message:: {} to Llama2 model ", message);
        return llama2AiService.generateStreamResponse(message);
    }
    @GetMapping("/ai/rag")
    public Flux<String> generateRag(@RequestParam(value = "message", defaultValue = "what is python") String message) {
        log.info("Sending prompt message:: {} to Llama2 model ", message);
        return llama2AiService.generateResponseRAG(message);
    }
    @GetMapping("/ai/generateREST")
    public String generateResponseRest(@RequestParam(value = "prompt", defaultValue = "what is java") String prompt) {
        log.info("Sending prompt:: {} to Llama2 model ", prompt);
        return llama2ClientImpl.chatLlamaRest(prompt);
    }
}