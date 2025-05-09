package kg.attractor.job_search_project.controller;
import kg.attractor.job_search_project.dto.UserImageDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.ImageService;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

    @GetMapping("upload")
    public String getImage(@ModelAttribute("userImageDto") String filename, Model model) {
        model.addAttribute("userImageDto", filename);
        return "personalAccount/profileApplicant";
    }

    @PostMapping("upload")
    public String uploadImage(@ModelAttribute UserImageDto userImageDto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {

        if (result.hasErrors()) {
            return "personalAccount/profileApplicant";
        }

        User user = userService.findUserByUsername(principal.getName());
        Long userID = user.getId();
        String filename = imageService.saveImage(userImageDto, userID);
        redirectAttributes.addFlashAttribute("userImageDto", filename);
        return "redirect:/users/profileApplicant";

    }

    @PostMapping("uploadForEmployer")
    public String uploadImageForEmployer(@ModelAttribute UserImageDto userImageDto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {

        if (result.hasErrors()) {
            return "personalAccount/profileEmployer";
        }

        User user = userService.findUserByUsername(principal.getName());
        Long userID = user.getId();
        String filename = imageService.saveImage(userImageDto, userID);
        redirectAttributes.addFlashAttribute("userImageDto", filename);
        return "redirect:/users/profileEmp";

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
