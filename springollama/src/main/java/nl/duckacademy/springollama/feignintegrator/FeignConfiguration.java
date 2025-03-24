package nl.duckacademy.springollama.feignintegrator;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import okhttp3.OkHttpClient.Builder;

import java.util.concurrent.TimeUnit;

public class FeignConfiguration {

    @Bean
    public OkHttpClient client() {
        okhttp3.OkHttpClient okHttpClient = new Builder()
                .connectTimeout(10, TimeUnit.DAYS)
                .readTimeout(10, java.util.concurrent.TimeUnit.DAYS)
                .writeTimeout(10, java.util.concurrent.TimeUnit.DAYS)
                .build();
        return new OkHttpClient(okHttpClient);
    }
}