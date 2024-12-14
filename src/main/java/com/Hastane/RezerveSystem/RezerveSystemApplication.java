package com.Hastane.RezerveSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.Hastane.RezerveSystem.BusinessLayer.BusinessLayer;
import com.Hastane.RezerveSystem.Pages.Main;

// Uygulamanın ana sınıfı: Spring Boot uygulamasını başlatır
@SpringBootApplication
public class RezerveSystemApplication {
    private BusinessLayer business; // İş katmanına erişim için bir değişken

    // Yapıcı metot: İş katmanı dependency injection ile alınır
    public RezerveSystemApplication(BusinessLayer business) {
        this.business = business;
    }

    // Uygulamanın giriş noktası (main metodu)
    public static void main(String[] args) {
        // Spring Boot uygulamasını başlatır
        SpringApplication.run(RezerveSystemApplication.class, args);
    }

    // Spring Boot başlatıldığında çalışacak bir CommandLineRunner tanımlanır
    @Bean
    public CommandLineRunner run() {
        return args -> {
            // Uygulamanın ana ekranı (Main) iş katmanını kullanarak başlatılır
            new Main(business);
        };
    }
}
