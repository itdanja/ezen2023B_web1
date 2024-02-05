package example.day01.webMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // **스프링부트 주입
public class AppStart {
    public static void main(String[] args) {
        // **스프링 시작
        SpringApplication.run( AppStart.class );
        // http://localhost:80
        //      또는
        // http://localhost:8080

    }
}