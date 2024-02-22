package example.day10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication // 아파치 톰캣 :  Uses Apache Tomcat as the default embedded container.
@ServletComponentScan // 서블릿 찾는다.
public class SpringBootStart {
    public static void main(String[] args) {

        SpringApplication.run( SpringBootStart.class );

    }
}
