package pers.wendaozhenlie.gunyuregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GunyuRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunyuRegistryApplication.class, args);
    }

}
