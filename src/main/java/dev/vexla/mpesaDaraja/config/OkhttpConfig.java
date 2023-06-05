package dev.vexla.mpesaDaraja.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkhttpConfig {

    @Bean
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }
}
