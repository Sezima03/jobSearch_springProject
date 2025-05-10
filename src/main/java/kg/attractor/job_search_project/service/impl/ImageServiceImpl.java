package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dto.UserImageDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.UserImage;
import kg.attractor.job_search_project.repository.ImageRepository;
import kg.attractor.job_search_project.service.ImageService;
import kg.attractor.job_search_project.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public String saveImage(UserImageDto userImageDto, Long userId) {
        log.info("Uploading image for user ID: {}", userImageDto.getUserId());

        String fileName = FileUtil.saveUploadFile(userImageDto.getFile(), "images/");

        UserImage existingUserImage = imageRepository.findByUserId(userId);
        if (existingUserImage != null) {
            FileUtil.deleteFile(existingUserImage.getFileName(), "images/");
            existingUserImage.setFileName(fileName);
            imageRepository.save(existingUserImage);
            log.info("updated existing image for user Id {}", userId);
        }
        else {
            UserImage userImage = new UserImage();
            userImage.setUserId(userId);
            userImage.setFileName(fileName);

            imageRepository.save(userImage);
            log.info("Image saved: {}", fileName);
        }

        return fileName;
    }

    @Override
    public ResponseEntity<?> getByName(String imageName) {
        log.info("Fetching image by name: {}", imageName);
        return FileUtil.getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public ResponseEntity<?> findById(long id) {
        UserImage image = imageRepository.findById(id)
                .orElseThrow(() -> new JobSearchException("Image not found"));

        return FileUtil.getOutputFile(image.getFileName(), "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public UserImage getImageDtoByUserId(Long userId) {
        return imageRepository.findByUserId(userId);
    }
}