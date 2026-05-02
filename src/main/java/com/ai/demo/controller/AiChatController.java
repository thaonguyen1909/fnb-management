package com.ai.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiChatController {
    private final ChatClient chatClient;
    public AiChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/api/ask")
    public String askAi(@RequestParam String message) {
        return chatClient.prompt(message).call().content();
    }
}
