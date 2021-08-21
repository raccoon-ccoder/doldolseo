package com.finalprj.doldolseo.configuration;

import com.finalprj.doldolseo.util.UploadFileUtil;
import com.finalprj.doldolseo.util.UploadProfileUtil;
import com.finalprj.doldolseo.util.UploadReviewFileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/*
 * Configuration 클래스
 *
 * @Author 김경일
 * @Date 2021/08/07
 */

@Configuration
public class DoldolSeoConfiguration {

    //Entity-DTO 간 변환
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UploadFileUtil uploadFileUtil(){
        return new UploadFileUtil(uploadPath());
    }
    @Bean
    public UploadReviewFileUtil uploadReviewFileUtil(){
        return new UploadReviewFileUtil(uploadPath());
    }

    //파일 저장될 절대 경로(로컬)
    @Bean(name = "uploadPath")
    public String uploadPath() {
        return System.getProperty("user.dir")+"/src/main/resources/static/_image";
    }

    //파일 저장될 절대 경로(톰캣)
//    @Bean(name = "uploadPath")
//    public String uploadPath() {
//        return "C:/tomcat/doldolseo/_image";
//    }

    //HTTP hidden Method : delete, put, patch ..
    @Bean
    public HiddenHttpMethodFilter httpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    // @Author 백정연, Date : 2021/08/11
    @Bean
    public UploadProfileUtil uploadProfileUtil() {return  new UploadProfileUtil(uploadPath());}
}
