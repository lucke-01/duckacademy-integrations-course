## Ollama

### Install Ollama

Linux:
```declarative
curl -fsSL https://ollama.com/install.sh | sh
```

Windows
```declarative
https://ollama.com/download/OllamaSetup.exe
```

### List of models available

https://ollama.com/search

### Installing and running a model

```declarative
ollama run tinyllama
```

### showing llama models in local

```
ollama list
```

## Calling API

```declarative
POST http://localhost:11434/api/chat
{
  "model": "llama2", #tinyllama
  "messages": [
    {
    "role": "system",
    "content": "You are a friendly chatbot"
    }
    {
      "role": "user",
      "content": "why is the sky blue?"
    }
  ],
  "stream": false
}
```