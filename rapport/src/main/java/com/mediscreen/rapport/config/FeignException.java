package com.mediscreen.rapport.config;

import com.mediscreen.rapport.exception.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignException {

    @Bean
    public CustomErrorDecoder mCustomErrorDecoder() {
        return new CustomErrorDecoder();
    }

}
