package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dao.UserImageDao;
import kg.attractor.job_search_project.dto.UserImageDto;
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

    @GetMapping
    public ResponseEntity<?> getImageById(@RequestParam(name = "id") Long id){
        return imageService.findById(id);
    }

    @GetMapping("{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName){
        return imageService.getByName(imageName);
    }

    @PostMapping
    public String uploadImage(UserImageDto  userImageDto){
        return imageService.saveImage(userImageDto);
    }
}
