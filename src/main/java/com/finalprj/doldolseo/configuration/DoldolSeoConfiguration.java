package com.finalprj.doldolseo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Configuration 클래스
 *
 * @Author 김경일
 * @Date 2021/08/07
 */

@Configuration
public class DoldolSeoConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
