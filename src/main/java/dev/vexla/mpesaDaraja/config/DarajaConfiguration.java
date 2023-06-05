package dev.vexla.mpesaDaraja.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "mpesa.daraja")
public class DarajaConfiguration {

    private String consumerKey;
    private String consumerSecret;
    private String grantType;
    private String oauthEndpoint;
}
