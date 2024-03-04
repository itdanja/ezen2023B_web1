package ezenweb.controller;

import ezenweb.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController // @Controller + @ResponseBody
@RequestMapping("/auth") // 해당 클래스의 매핑 ( 주로 해당 클래스내 함수들의 매핑주소중에 공통 )
public class AuthController {

    // 세션 객체 호출
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmailService emailService;

    // 1. email 인증 요청
    @GetMapping("/email/req")
    public boolean getEmailReq( @RequestParam String email ){
        System.out.println("AuthController.getEmailReq");
        // 1. 난수 이용한 6자리 인증코드 발급
        Random random = new Random();  // 1. 난수 객체 생성
        String ecode = "";  //  001475
        for( int i = 1 ; i<=6 ; i++ ){      // 2. 6번
            ecode += random.nextInt( 10 ); // 0~9 난수  // 3. 난수생성 해서 변수에 누적 문자로 연결하기
        }
        System.out.println("ecode = " + ecode); // 이메일 전송 안했을때 JAVA콘솔보고 테스트 진행을 위해
        // 2. HTTP세션에 특정시간만큼 발급된 인증코드 보관
            // 1. 세션에 속성 추가 해서 발급된 인증코드 대입하기.
        request.getSession().setAttribute( "ecode" , ecode );
            // 2. 세션에 생명주기
        request.getSession().setMaxInactiveInterval( 10 ); // 초 기준 // 10초 동안 세션 유지 하고 10후 삭제
        // 3. 발급된 인증코드를 인증할 이메일로 전송
        emailService.send( email ,
                "EZEN WEB 인증코드" ,
                "인증코드 : " +  ecode  );

        return true;
    }

    // 2. email 인증 확인
    @GetMapping("/email/check")
    public boolean getEmailCheck(@RequestParam String ecodeinput){
        System.out.println("AuthController.getEmailCheck");
        System.out.println("ecodeinput = " + ecodeinput);
        // 1. HTTP세션에 보관했던 인증코드를 꺼내서
            // 1. 세션 속성 호출
        Object object = request.getSession().getAttribute("ecode");
            // 2. 만약에 세션 속성이 존재하면
        if( object != null ){
            String ecode = (String)object; // 강제 타입 변환
            // 3. 발급된 인증코드와 입력받은 인증코드와 같으면
            if( ecode.equals( ecodeinput ) ){  return true; }
        }
        return false;
    } // fend
}










