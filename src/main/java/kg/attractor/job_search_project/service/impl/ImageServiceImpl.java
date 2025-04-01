package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dao.UserImageDao;
import kg.attractor.job_search_project.dto.UserImageDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.UserImage;
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

    private final UserImageDao userImageDao;

    @Override
    public String saveImage(UserImageDto userImageDto){
        log.info("Starting image upload for user Id: {}", userImageDto.getUserId());
        String fileName = FileUtil.saveUploadFile(userImageDto.getFile(), "images/");
        log.info("Image upload for user Id: {}", userImageDto.getUserId());
        userImageDao.save(userImageDto.getUserId(), fileName);
        log.info("File name saved for user Id: {}", userImageDto.getUserId());
        return fileName;
    }

    @Override
    public ResponseEntity<?> getByName(String imageName){
        log.info("Retrieving user image with name: {}", imageName);
        return FileUtil.getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public ResponseEntity<?> findById(long id){
        log.info("Retrieving user image with id: {}", id);
        UserImage image =userImageDao.getImgById(id)
                .orElseThrow(()-> new JobSearchException("Image not found"));
        String fileName = image.getFileName();
        log.info("Found image with ID: {} file name: {}", id, fileName);
        return FileUtil.getOutputFile(fileName, "images/", MediaType.IMAGE_JPEG);
    }
}
