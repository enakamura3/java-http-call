package demo.thread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class Beans {

    private static final int POOL_SIZE = 10;

    @Bean
    public Executor threadPool(){
        return Executors.newFixedThreadPool(POOL_SIZE);
    }
}
