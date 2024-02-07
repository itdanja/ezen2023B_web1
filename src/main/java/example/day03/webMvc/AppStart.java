package example.day03.webMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// ------------- 스프링부트 실행에 관련 기능 주입 ------------- //
// 1. 내장 서버(톰캣)
// ********** 2. 동등한 패키지 혹은 하위 패키지내
// @Controller @RestController 들을 스캔.
public class AppStart {
    public static void main(String[] args) {
        // * 스프링 시작.
        SpringApplication.run( AppStart.class );
    }
}
/*

 */
