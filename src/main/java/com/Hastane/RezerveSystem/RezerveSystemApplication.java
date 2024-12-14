package com.Hastane.RezerveSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Pages.Main;

@SpringBootApplication
public class RezerveSystemApplication {
    private BusinessLayer business;

    public RezerveSystemApplication(BusinessLayer business) {
        this.business = business;
    }

    public static void main(String[] args) {
        SpringApplication.run(RezerveSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
        	new Main(business);
        };
    }
}
