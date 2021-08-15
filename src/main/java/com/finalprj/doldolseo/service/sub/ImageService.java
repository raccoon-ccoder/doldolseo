package com.finalprj.doldolseo.service.sub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final Path rootLocation;

    public ImageService(String uploadPath) {
        this.rootLocation = Paths.get(uploadPath + "/review/temp");
        System.out.println(rootLocation.toString());
    }

    //이미지 파일을 로컬환경에 저장
    public String store(MultipartFile file) throws Exception {
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }

            String saveFileName = fileSave(rootLocation.toString(), file); //이미지파일 _image/review/temp/UUID+파일명 으로 저장
            System.out.println(saveFileName);

            return "/_image" + saveFileName.split("_image")[1]; //절대경로 -> _image/review/temp..
//            return saveFileName;

        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    //파일 저장
    public String fileSave(String rootLocation, MultipartFile file) throws IOException {
        File uploadDir = new File(rootLocation);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // saveFileName 생성
        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString() + file.getOriginalFilename();
        File saveFile = new File(rootLocation, saveFileName);
        FileCopyUtils.copy(file.getBytes(), saveFile);

        return saveFile.getAbsolutePath();
    }
}
