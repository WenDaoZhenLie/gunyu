package pers.wendaozhenlie.gunyugateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GunyuGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunyuGatewayApplication.class, args);
    }

}
