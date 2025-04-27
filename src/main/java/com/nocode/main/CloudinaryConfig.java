package com.nocode.main;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dqyxcnusl",
                "api_key", "889269391922931",
                "api_secret", "yAshj4JZILKou32fUfNbxfUk3jE",
                "secure", true
        ));
    }
}