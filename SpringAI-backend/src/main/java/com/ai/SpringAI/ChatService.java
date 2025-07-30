package com.ai.SpringAI;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel){
        this.chatModel= chatModel;
    }

    public String getResponse(String prompt){
        return chatModel.call(prompt);
    }

    public String getResponseOptions(String prompt){
        Prompt promptObj = new Prompt(prompt,
                ChatOptions.builder()
                        .model("gpt-4-1106-preview")
                        .temperature(0.4D)          // temperature sets the randomness (Lower= focussed, higher= random)
                        .maxTokens(100)
                        .build()
        );

        ChatResponse response = chatModel.call(promptObj);
        return response.getResult().getOutput().getText();
    }

}
