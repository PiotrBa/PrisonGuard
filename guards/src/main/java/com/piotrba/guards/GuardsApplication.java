package com.piotrba.guards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GuardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuardsApplication.class, args);
    }

}
