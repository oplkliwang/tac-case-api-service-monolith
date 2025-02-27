package com.beaconstrategists.taccaseapiservice.config.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class RestControllerConfig {

    @Value("${API_SVC_MAX_FILE_SIZE_MB:20}")
    private Integer maxFileSize;

    @Bean
    public MultipartProperties multipartProperties() {
        MultipartProperties properties = new MultipartProperties();
        properties.setMaxFileSize(DataSize.ofMegabytes(maxFileSize));
        properties.setFileSizeThreshold(DataSize.ofMegabytes(maxFileSize));
        properties.setMaxRequestSize(DataSize.ofMegabytes(maxFileSize));
        return properties;
    }
}
