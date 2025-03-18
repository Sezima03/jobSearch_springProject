package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.service.ImageService;
import kg.attractor.job_search_project.util.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service

public class ImageServiceImpl implements ImageService {

    @Override
    public String saveImage(MultipartFile file){
        FileUtil fu=new FileUtil();
        return fu.saveUploadFile(file, "images/");
    }

    @Override
    public ResponseEntity<?> getByName(String imageName){
        return new FileUtil().getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }
}
