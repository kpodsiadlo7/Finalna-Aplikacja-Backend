package com.clinic.privateclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PrivateClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivateClinicApplication.class, args);
    }

}
