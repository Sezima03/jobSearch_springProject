package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dao.UserImageDao;
import kg.attractor.job_search_project.dto.UserImageDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.UserImage;
import kg.attractor.job_search_project.service.ImageService;
import kg.attractor.job_search_project.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final UserImageDao userImageDao;
    private final FileUtil fileUtil;

    @Override
    public String saveImage(UserImageDto userImageDto){
        String fileName = fileUtil.saveUploadFile(userImageDto.getFile(), "images/");
        userImageDao.save(userImageDto.getUserId(), fileName);
        return fileName;
    }

    @Override
    public ResponseEntity<?> getByName(String imageName){
        return fileUtil.getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public ResponseEntity<?> findById(long id){
        UserImage image =userImageDao.getImgById(id)
                .orElseThrow(()-> new JobSearchException("Image not found"));
        String fileName = image.getFileName();
        return fileUtil.getOutputFile(fileName, "images/", MediaType.IMAGE_JPEG);
    }
}
