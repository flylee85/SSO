package com.github.mjd507.sso.client.config;


import com.github.mjd507.sso.client.util.SSOUserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mjd on 2020/4/17 09:54
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(SSOProperties.class)
@EnableConfigurationProperties(SSOProperties.class)
public class SSOClientAutoConfiguration {

    @Bean
    public SSOUserClient ssoUserClient(SSOProperties ssoProperties) {
        return new SSOUserClient(ssoProperties.getServerUrl());
    }

}
