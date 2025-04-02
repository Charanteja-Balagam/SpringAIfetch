package com.ai.springAI;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImages(String prompt,String quality,int n,int height){

       ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("dall-e-2")
                                .height(1024)
                                .quality("hd")
                                .N(2).build())
        );
       return imageResponse;
    }
}
