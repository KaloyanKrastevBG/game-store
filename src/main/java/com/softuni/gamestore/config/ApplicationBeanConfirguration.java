package com.softuni.gamestore.config;


import com.softuni.gamestore.utils.ValidationUtil;
import com.softuni.gamestore.utils.ValidationUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfirguration {

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }
}
