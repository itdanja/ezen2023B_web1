package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 1.스프링 컨테이너(메모리 저장소)에 빈(객체/힙) 등록
// 2.스프링이 해당 클래스를 사용할수 있다.
// 3.모든 클라이언트 요청은 컨트롤러로 들어온다.
@Controller
public class ArticleController {

    @Autowired // 스프링 컨테이너에 등록된 빈 주입한다.
    ArticleDao articleDao;

    // 1. 글쓰기 페이지 반환
    @GetMapping("/articles/new") // HTTP 요청 경로 : GET방식 : localhost:80/articles/new
    public String newArticleForm(){
        return "articles/new";   // .확장자 빼고 resources/templates 부터 경로 작성
    } // m end

    // 2. 글쓰기 처리
    // 1. <form action="/articles/create" method="post">
    // 2. 입력태그 속성의 name과 DTO 필드의 필드명 일치하면 자동 연결/대입 된다.
    // 3. public 생성자 필요
    @PostMapping("/articles/create")    // HTTP 요청 경로 : POST방식 : localhost:80/articles/create
    public boolean createArticle( ArticleForm form ){
        // soutm : 메소드명 출력
        System.out.println("ArticleController.createArticle");
        // soutp : 메소드 매개변수 출력
        System.out.println("form = " + form);

        // DAO에게 요청하고 응답 받기.
        boolean result = articleDao.createArticle( form );

        return result ;
    } // m end

}
/*

    @어노테이션
        1. 표준 어노테이션 : 자바에서 기본적으로 제공
            @Override : 메소드 재정의
            등등
       2. 메타 어노테이션 : p.64
            - 소스코드에 추가해서 사용하는 메타 데이터
            - 메타 데이터 : 구조화된 데이터
            - 컴파일 또는 실행 했을때 코드를 어떻게 처리 해야 할지 알려주는 정보
            @SpringBootApplication
                - 1. 내장 서버(톰캣) 지원
                - 2. 스프링 MVC 패턴 내장
                    view : resources
                    controller : @Controller , @RestController
                        service : @Service
                    model(dao) :  @Repository
                        entity(db table) : @Entity
                        그외 별도 : @Component
                        설정 클래스 : @Configuration
                - 3. 컴포넌트(module) 스캔 : MVC 클래스 스캔
                    동일 패키지내 혹은 하위패키지 스캔
            @Controller
            @GetMapping


        - 다른 클래스의 함수를 호출하는방법
        [ MVC패턴은 클래스들이 분업하기 때문에 서로 다른 클래스들끼리 데이터 주고(매개변수)받는다(리턴). *상호작용 특징 ]

        // 1. 해당 함수가 인스턴스(static없으면) 멤버이면
        ArticleDao articleDao = new ArticleDao();
        articleDao.createArticle();
        // 2. 해당 함수가 인스턴스(static없으면) 멤버이면
        new ArticleDao().createArticle();
        // 3. 해당 함수가 정적(static) 멤버이면
        ArticleDao.createArticle();
        // 4. 해당 클래스가 싱글톤( 프로그램내 무조건 하나의 객체만 갖는 패턴)
        ArticleDao.getInstance().createArticle();
        // 5. 스프링 컨테이너(JVM만들어진 메모리 저장소) 등록 빈(객체) 된 경우
        @Autowired
        ArticleDao articleDao;

 */
