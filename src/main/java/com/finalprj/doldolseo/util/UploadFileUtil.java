package com.finalprj.doldolseo.util;

import com.finalprj.doldolseo.dto.crew.CrewDTO;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadFileUtil {

    private final Path rootLocation;

    public UploadFileUtil(String uploadPath) {
        this.rootLocation = Paths.get(uploadPath);
    }

    //코스 이미지 저장
    public String courseImgSave(Long reviewNo, MultipartFile courseImg) throws IOException {

        File noDirectory = new File(rootLocation.toString() + "/review/" + reviewNo);
        if (!noDirectory.exists()) {
            noDirectory.mkdirs();
        }

        String fileName = courseImg.getOriginalFilename();
        Path savePath = Paths.get(rootLocation.toString() + "/review/" + reviewNo + "/" + fileName);
        File saveFile = new File(savePath.toString());
        courseImg.transferTo(saveFile);

        return saveFile.getAbsolutePath();
    }

    //temp 이미지들 후기글 이미지 폴더생성후 이동
    public void moveImages(Long reviewNo, String uploadImg) {

        String[] uploadImgs = uploadImg.split(",");

        for (int i = 0; i < uploadImgs.length; i++) {
            Path src = Paths.get(rootLocation.toString() + "/review/temp/" + uploadImgs[i]);
            Path dst = Paths.get(rootLocation.toString() + "/review/" + reviewNo + "/" + uploadImgs[i]);

            File noDirectory = new File(rootLocation.toString() + "/review/" + reviewNo);
            if (!noDirectory.exists()) {
                noDirectory.mkdirs();
            }
            try {

                Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //temp 이미지들 후기글 이미지 폴더생성후 이동
    public void moveImagesCrew(Long postNo, String uploadImg) {

        String[] uploadImgs = uploadImg.split(",");

        for (int i = 0; i < uploadImgs.length; i++) {
            Path src = Paths.get(rootLocation.toString() + "/crew/board/temp/" + uploadImgs[i]);
            Path dst = Paths.get(rootLocation.toString() + "/crew/board/" + postNo + "/" + uploadImgs[i]);

            File noDirectory = new File(rootLocation.toString() + "/crew/board/" + postNo);
            if (!noDirectory.exists()) {
                noDirectory.mkdirs();
            }
            try {

                Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //이미지폴더 이미지들 temp로 이동
    public void moveToTemp(Long reviewNo) {
        File imageDir = new File(rootLocation.toString() + "/review/" + reviewNo);

        if (imageDir.exists()) {

            //디렉토리 하위 파일 목록(course.png 제외)
            File[] files = imageDir.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return !name.equals("course.png");
                }
            });

            //Temp로 해당 파일 이동
            if (files != null) {
                for (File file : files) {

                    Path src = Paths.get(rootLocation.toString() + "/review/" + reviewNo + "/" + file.getName());
                    Path dst = Paths.get(rootLocation.toString() + "/review/temp/" + file.getName());
                    try {

                        Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("temp로 파일 이동");
        }
    }

    //후기글 삭제시 이미지삭제
    public void deleteImages(Long reviewNo) {

        Path path = Paths.get(rootLocation.toString() + "/review/" + reviewNo);
        File imageDir = new File(path.toString());

        if (imageDir.exists()) {
            File[] files = imageDir.listFiles();

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    boolean isDeleted = files[i].delete();
                    if (isDeleted) {
                        System.out.println(i + "번 파일 삭제 성공");
                    }
                }
            }
        }

        try {
            Files.delete(path);
            System.out.println(reviewNo + "번 이미지 디렉토리 삭제 됨");
        } catch (IOException e) {
            System.out.println(reviewNo + "번 이미지 디렉토리 삭제 실패");
            e.printStackTrace();
        }
    }

    //크루 이미지 저장 (기존 이미지 있으면 삭제하고 새로 저장)
    public String crewImgSave(MultipartFile crewImageFile, CrewDTO dto) throws IOException {

        File crewImage;

        //기존이미지 삭제 (업데이트 시)
        if (dto.getCrewImage() != null) {
            Path path = Paths.get(rootLocation.toString() + "/crew/logo/" + dto.getCrewImage());
            File oldImg = new File(path.toString());

            if (oldImg.exists()) {
                boolean isDeleteSuccess = oldImg.delete();
                if (isDeleteSuccess) {
                    System.out.println("기존 이미시 삭제 성공");
                } else {
                    System.out.println("기존 이미시 삭제 실패");
                }
            }
        }

        if (!crewImageFile.isEmpty()) {
            //파일 이름 : 크루이름.확장자
            String crewImageName = dto.getCrewName() + "." + crewImageFile.getOriginalFilename().split("\\.")[1];
            String crewImagePath = rootLocation.toString() + "/crew/logo/" + crewImageName;

            crewImage = new File(crewImagePath);
            crewImageFile.transferTo(crewImage);
        } else {
            return null;
        }

        return crewImage.getName();
    }

}

