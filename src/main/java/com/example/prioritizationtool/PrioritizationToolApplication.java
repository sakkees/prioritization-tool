package com.example.prioritizationtool;

import com.example.prioritizationtool.controller.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PrioritizationToolApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrioritizationToolApplication.class, args);
    }

}
