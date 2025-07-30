package com.ai.SpringAI;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class RecipeService {
    ChatModel chatModel;

    RecipeService(ChatModel chatModel){
        this.chatModel= chatModel;
    }

    public String getRecipe(String ingredients, String cuisine, String dietaryRestrictions){
        var template= """
                Create a well-formatted recipe using the following:
                        - Ingredients: {ingredients}
                        - Cuisine: {cuisine}
                        - Dietary Restrictions: {dietaryRestrictions}
                
                        Return it in Markdown format with:
                        - A title
                        - A list of ingredients
                        - Step-by-step instructions
        """;
        PromptTemplate promptTemplate= new PromptTemplate(template);
        Map<String, Object> map= new HashMap<>();
        map.put("ingredients",ingredients);
        map.put("cuisine",cuisine);
        map.put("dietaryRestrictions",dietaryRestrictions);

        Prompt prompt= promptTemplate.create(map);

        ChatResponse response= chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }

}
