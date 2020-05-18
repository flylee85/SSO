package com.github.mjd507.sso.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by mjd on 2020/5/18 21:22
 */
@ConfigurationProperties(prefix = "sso")
public class SSOProperties {
    private String serverUrl;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
