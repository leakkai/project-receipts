package com.rp.mainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@SpringBootApplication(scanBasePackages = {"com.rp.*", "hello"})
//@EnableJpaRepositories("hello")
//@EntityScan("hello")
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
