package com.finalprj.doldolseo.configuration;

import com.finalprj.doldolseo.util.UploadFileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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

    //파일 저장될 로컬 절대 경로
    @Bean(name = "uploadPath")
    public String uploadPath() {
        return System.getProperty("user.dir")+"/src/main/resources/static/_image/review";
    }

    //HTTP hidden Method : delete, put, patch ..
    @Bean
    public HiddenHttpMethodFilter httpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
