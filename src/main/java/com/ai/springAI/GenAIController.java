package com.ai.springAI;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenAIController {

  private final   ChatService chatService;
   private final  ImageService imageService;

   private final RecipeService recipeService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }


    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){

        return chatService.getResponseOptions(prompt);
    }
    @GetMapping("generate-images")
    public List<String> getImage(HttpServletResponse response, @RequestParam String prompt,
    @RequestParam(defaultValue="hd") String quality,
                                 @RequestParam(defaultValue="1") int N,
                                 @RequestParam(defaultValue="500") int height
                                 ) throws IOException {

        ImageResponse imageResponse=  imageService.generateImages(prompt,quality,N,height);
      List<String> imageUrls= imageResponse.getResults().stream()
               .map(res->res.getOutput().getUrl())
               .collect(Collectors.toList());

      return imageUrls;

    }


    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                      @RequestParam(defaultValue = "any") String cuisine,
                                      @RequestParam(defaultValue = "") String dietaryRestrictions){

        return recipeService.createRecipe(ingredients,cuisine,dietaryRestrictions);

    }
}
