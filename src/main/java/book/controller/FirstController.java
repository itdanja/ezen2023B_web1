package book.controller;

import org.springframework.stereotype.Controller; // @Controller
import org.springframework.ui.Model; //
import org.springframework.web.bind.annotation.GetMapping;

@Controller // * 이 클래스가 컨트롤러임을 선언
public class FirstController {
    
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){ // 인수에 model매개변수 선언,
        // 머스테치에 전달할 변수 등록.
        model.addAttribute( "username" , "유재석");
        // return "머스테치파일명";
        return "greetings";
        // 서버가 알아서 templates 폴더에서 파일을 찾아 브라우저에게 전송.
    }
    // http://localhost:80/greetings.mustache      [X] resources/templates
    // http://localhost:80/greetings.html          [X] resources/templates
    // http://localhost:80/hello.html              [O] resources/static
    // http://localhost/hi                         [O]

    /*

        GET : 요청

        HTTP : 이동식문서 교환 규약
            1. IP주소:PORT번호      ,   스프링아~       ,   localhost:80
            2. /자원의경로           ,  도서지급대장문서 줘 ,   /bookdoument , @GetMapping("/bookdoument") ---> 해당 함수로 이동

        브라우저[클라이언트]                                                 스프링[ 서버 ] localhost:80
        강호동                                                             신동엽
                            [말] 강호동이 신동엽에게 도서지급대장문서를 요청
                            [HTTP] http://localhost:80/hi
                            -------------------------------------->         서랍1 = hi [ 도서지급대장문서 = greetings.mustache ]
         브라우저            [말] 신동엽이 강호동에게 도서지급대장문서를 응답
         html렌더링가능          String 문자[HTML]전송                         강호동은 템플릿을 모르니까. 템플릿을 HTML 렌더링 하고 HTML로 반환.
                            <---------------------------------------

    */

}
