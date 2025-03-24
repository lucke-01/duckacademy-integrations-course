package nl.duckacademy.springollama.feignintegrator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Llama2ClientImpl {
    private final Llama2Client llama2Client;

    public Llama2ClientImpl(Llama2Client llama2Client) {
        this.llama2Client = llama2Client;
    }

    public String chatLlamaRest(String request) {
        LlamaRequestJSON requestJSON = createLlamaRequest(request);
        LlamaResponseJSON response = llama2Client.getLlamaResponse(requestJSON);
        return response.getMessage().getContent();
    }
    public LlamaRequestJSON createLlamaRequest(String request) {
        // Create a new instance of LlamaRequestJSON
        LlamaRequestJSON llamaRequest = new LlamaRequestJSON();

        // Set the model field
        llamaRequest.setModel("llama2");

        // Create a new Message object
        LlamaRequestJSON.Message message = new LlamaRequestJSON.Message();
        message.setRole("user");
        message.setContent(request); // Populate the content field with the provided request

        // Add the message to the messages list
        List<LlamaRequestJSON.Message> messages = new ArrayList<>();
        messages.add(message);
        llamaRequest.setMessages(messages);

        // Set the stream field
        llamaRequest.setStream(false);

        return llamaRequest; // Return the populated object
    }
}
