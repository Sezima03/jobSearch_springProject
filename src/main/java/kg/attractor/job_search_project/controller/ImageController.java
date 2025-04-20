package kg.attractor.job_search_project.controller;


import kg.attractor.job_search_project.dto.UserImageDto;
import kg.attractor.job_search_project.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("upload")
    public String getImage(@ModelAttribute("userImageDto") String filename, Model model) {
        model.addAttribute("userImageDto", filename);
        return "images/img";
    }

    @PostMapping("upload")
    public String uploadImage(@ModelAttribute UserImageDto userImageDto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "images/img";
        }

        String filename = imageService.saveImage(userImageDto);
        redirectAttributes.addFlashAttribute("userImageDto", filename);
        return "redirect:/images/upload";

    }

    @GetMapping("by-name/{name}")
    public ResponseEntity<?> getImageByName(@PathVariable String name) {
        return imageService.getByName(name);
    }

    @GetMapping("by-id")
    public ResponseEntity<?> getImageById(@RequestParam long id) {
        return imageService.findById(id);
    }
}
