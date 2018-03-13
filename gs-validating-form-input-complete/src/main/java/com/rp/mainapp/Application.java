package com.rp.mainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@SpringBootApplication(scanBasePackages = {"com.rp.*", "hello"})
@EnableJpaRepositories("com.rp.repository")
@EntityScan("com.rp.model")
@SpringBootApplication(scanBasePackages = {"com.rp.*"})
@Controller
public class Application {

	@GetMapping("")
    public String simpleView() {
    	return "/index";
    }
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
