package com.ai.SpringAI;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenAIController {
    @Autowired
    ChatService chatService;

    @Autowired
    ImageServcie imageServcie;

    @Autowired
    RecipeService recipeService;

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

//    @GetMapping("/generate-image")
//    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
//        ImageResponse imageResponse=  imageServcie.generateImage(prompt);
//        String ImageUrl = imageResponse.getResult().getOutput().getUrl();
//        response.sendRedirect(ImageUrl);
//    }

    @GetMapping("/generate-image")
    public List<String> generateImage(HttpServletResponse response,
                                      @RequestParam String prompt,
                                      @RequestParam(defaultValue ="1") int N,
                                      @RequestParam(defaultValue = "1024") int width,
                                      @RequestParam(defaultValue = "1024") int height) throws IOException {

        ImageResponse imageResponse=  imageServcie.generateImage(prompt,N,width,height);
        //        ImageResponse imageResponse=  imageServcie.generateImage(prompt,N,width,height);
//        List<String> imageUrls = new ArrayList<>();
//        for (ImageGeneration result : imageResponse.getResults()) {
//            imageUrls.add(result.getOutput().getUrl());
//        }
        //or Using stream, as below

        List<String> imageUrls = imageResponse.getResults().stream()
                                .map(result-> result.getOutput().getUrl())
                                .collect(Collectors.toList());

        return imageUrls;   //Returning multiple urls ;
    }

    @GetMapping("/recipe-creator")
    public String createRecipe(@RequestParam String ingredients,
                               @RequestParam(defaultValue = "indian")String cuisine,
                               @RequestParam(defaultValue = "Vegetarian") String dietaryRestriction){
        return recipeService.getRecipe(ingredients,cuisine,dietaryRestriction);
    }

}
