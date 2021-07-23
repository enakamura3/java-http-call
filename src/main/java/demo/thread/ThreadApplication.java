package demo.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadApplication.class, args);
	}

}
