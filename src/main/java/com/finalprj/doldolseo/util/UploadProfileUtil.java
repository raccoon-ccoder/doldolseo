package com.finalprj.doldolseo.util;

import com.finalprj.doldolseo.dto.MemberDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
 * 멤버 프로필 관련 클래스
 *
 * @Author 백정연
 * @Date 2021/08/11
 */

public class UploadProfileUtil {

    public void makeDirectory(){
        String path = System.getProperty("user.dir") + "/src/main/resources/static/_image/profile";
        File Directory = new File(path);
        if(!Directory.exists()){
            Directory.mkdirs();
        }
    }

    public String uploadProfile(MultipartFile profile, MemberDTO dto) throws IOException {
        makeDirectory();
        String file = profile.getOriginalFilename();
        String fileName = dto.getId() + "." + file.substring(file.lastIndexOf(".") + 1);
        String rootPath = System.getProperty("user.dir") + "/src/main/resources/static/_image/profile/";
        String filePath = rootPath + fileName;
        File dest = new File(filePath);
        profile.transferTo(dest);
        return fileName;
    }

    public void deleteProfile(MemberDTO dto){
        makeDirectory();
        String fileName = dto.getMember_img();
        String rootPath = System.getProperty("user.dir") + "/src/main/resources/static/_image/profile/";
        String filePath = rootPath + fileName;
        File file = new File(filePath);
        file.delete();
    }

    public MemberDTO updateProfile(MemberDTO originUser, MemberDTO changedUser, MultipartFile file) throws IOException {
        makeDirectory();
        String profileImg = "sample.png";

        if(originUser.getMember_img().equals(profileImg)){
            if(file.isEmpty()){
                changedUser.setMember_img(profileImg);
            }else{
                profileImg = uploadProfile(file, changedUser);
                changedUser.setMember_img(profileImg);
            }
        }else{
            if(file.isEmpty()){
                changedUser.setMember_img(originUser.getMember_img());
            }else{
                deleteProfile(originUser);
                uploadProfile(file, originUser);
                changedUser.setMember_img(originUser.getMember_img());
            }
        }
        return changedUser;
    }
}
