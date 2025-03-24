package nl.duckacademy.springollama.feignintegrator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "Llama2Client", url = "http://localhost:11434/api", configuration = FeignConfiguration.class)
public interface Llama2Client {
    @RequestMapping(method = RequestMethod.POST, value = "/chat", produces = "application/json")
    LlamaResponseJSON getLlamaResponse(@RequestBody LlamaRequestJSON request);
}
