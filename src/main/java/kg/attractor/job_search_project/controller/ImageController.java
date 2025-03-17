package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName){
        return imageService.getByName(imageName);
    }

    @PostMapping
    public String uploadImage(MultipartFile file){
        return imageService.saveImage(file);
    }
}
