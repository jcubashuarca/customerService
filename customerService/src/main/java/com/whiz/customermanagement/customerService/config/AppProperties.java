package com.whiz.customermanagement.customerService.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "client.app")
public class AppProperties {

    private Integer lifeExpectancyMan;
    private Integer lifeExpectancyWoman;

}