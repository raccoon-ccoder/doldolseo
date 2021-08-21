package com.finalprj.doldolseo.util;

import com.finalprj.doldolseo.dto.crew.CrewDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

public class UploadFileUtil {
    private final String ROOT_PATH;
    private final String CREW_LOGO_IMG_PATH;

    public UploadFileUtil(String uploadPath) {
        this.ROOT_PATH = uploadPath;
        this.CREW_LOGO_IMG_PATH = ROOT_PATH + "/crew/logo/";
    }

    public void trasferFile(MultipartFile multipartFile, Path savepath) {
        try {
            multipartFile.transferTo(new File(savepath.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeDirIfNoExist(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void moveFile(Path src, Path dst) {
        try {
            Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFilesInDir(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    boolean isDeleted = files[i].delete();
                    logWhenDeleteFile(isDeleted, files[i].getPath());
                }
            }
        }

    }

    //temp 이미지들 후기글 이미지 폴더생성후 이동
    public void moveImagesCrew(Long postNo, String uploadImg) {

        String[] uploadImgs = uploadImg.split(",");

        for (int i = 0; i < uploadImgs.length; i++) {
            Path src = Paths.get(ROOT_PATH.toString() + "/crew/board/temp/" + uploadImgs[i]);
            Path dst = Paths.get(ROOT_PATH.toString() + "/crew/board/" + postNo + "/" + uploadImgs[i]);

            File noDirectory = new File(ROOT_PATH.toString() + "/crew/board/" + postNo);
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

    //이미지폴더 이미지들 temp로 이동 (크루)
    public void moveToTempCrew(Long postNo) {
        File imageDir = new File(ROOT_PATH.toString() + "/crew/board/" + postNo);

        if (imageDir.exists()) {

            File[] files = imageDir.listFiles();

            //Temp로 해당 파일 이동
            if (files != null) {
                for (File file : files) {

                    Path src = Paths.get(ROOT_PATH.toString() + "/crew/board/" + postNo + "/" + file.getName());
                    Path dst = Paths.get(ROOT_PATH.toString() + "/crew/board/temp/" + file.getName());
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

    //크루 활동글 삭제시 이미지삭제
    public void deleteCrewImages(Long postNo) {
        Path path = Paths.get(ROOT_PATH.toString() + "/crew/board/" + postNo);
        File imageDir = new File(path.toString());

        if (imageDir.exists()) {
            File[] files = imageDir.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    boolean isDeleted = files[i].delete();
                    logWhenDeleteFile(isDeleted, files[i].getPath());
                }
            }
        }

        try {
            Files.delete(path);
            System.out.println(postNo + "번 이미지 디렉토리 삭제 됨");
        } catch (IOException e) {
            System.out.println(postNo + "번 이미지 디렉토리 삭제 실패");
            e.printStackTrace();
        }
    }

    //크루 이미지 저장 (기존 이미지 있으면 삭제하고 새로 저장)
    public String updateCrewLogo(CrewDTO dto, MultipartFile crewImgFile) throws IOException {
        //기존이미지 삭제
        File oldfile = new File(CREW_LOGO_IMG_PATH + dto.getCrewImgFileName());
        deleteFile(oldfile);

        //저장될 파일 이름 생성 : 크루이름.확장자
        String crewImagePath = CREW_LOGO_IMG_PATH + makeSaveName(crewImgFile.getOriginalFilename(), dto.getCrewName());
        File fileToSave = new File(crewImagePath);

        //파일 저장
        crewImgFile.transferTo(fileToSave);

        return fileToSave.getName();
    }

    //실제 저장될 이미지 이름 생성 -> 저잘할파일이름.확장자 ex) myCrew.png
    public String makeSaveName(String originalFileName, String nameWantSave) {
        String[] splitedFileName = originalFileName.split("\\.");
        String imgExtension = splitedFileName[splitedFileName.length - 1]; //end Of FileName Array splited by '.'

        return nameWantSave + "." + imgExtension;
    }

    //파일 삭제
    public void deleteFile(File fileToDelete) {
        if (fileToDelete.exists()) {
            boolean isDeleted = fileToDelete.delete();
            logWhenDeleteFile(isDeleted, fileToDelete.getPath());
        }
    }

    //파일 삭제시, 삭제 로그 생성
    public void logWhenDeleteFile(boolean isDeleted, String filePath) {
        if (isDeleted) {
            System.out.printf("[%s] %s 삭제 완료", LocalDateTime.now(), filePath);
        } else {
            System.out.printf("[%s] %s 삭제 실패", LocalDateTime.now(), filePath);
        }
    }

}


