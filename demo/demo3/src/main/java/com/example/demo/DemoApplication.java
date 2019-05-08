package com.example.demo;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;


//@SpringBootApplication
//@MapperScan("com.example.demo.*")
@RestController
@EnableAutoConfiguration
public class DemoApplication {
    @RequestMapping("/")
    String home()
    {
        return "hello demo3 !";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
