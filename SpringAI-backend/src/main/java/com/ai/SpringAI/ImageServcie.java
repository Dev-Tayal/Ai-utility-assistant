package com.ai.SpringAI;

import org.springframework.ai.image.ImageMessage;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServcie {
    private final OpenAiImageModel openAiImageModel;

    public ImageServcie(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt,int n,int width,int height){
//        ImageResponse imageResponse= openAiImageModel.call(new ImagePrompt(prompt));
//        return imageResponse;
        ImageResponse imageResponse= openAiImageModel.call(new ImagePrompt(prompt,
                OpenAiImageOptions.builder()
                        .model("dall-e-2")
//                       .quality("high")  omly works with dalle-3 or gpt-image-1
                        .N(n)
                        .width(width)
                        .height(height)
                        .build()
                ));
        return imageResponse;
    }

}
