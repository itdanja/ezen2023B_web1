package ezenweb.controller;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


// 1단계. V<---->C 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 함수 선언 하고 통신 체크 ( API Tester )
    // 3단계. Controller request 매개변수 매핑
        // -------------- Dto , Service ---------------//
    // 4단계. 응답 : 1.뷰 반환 : text/html;  VS  2. 데이터/값 : @ResponseBody : Application/JSON
@Controller
public class MemberController {

    @Autowired
    private HttpServletRequest request;


    // 1.=========== 회원가입 처리 요청 ===============
    @PostMapping("/member/signup") // http://localhost:80/member/signup
    @ResponseBody // 응답 방식 application/json;
    public boolean doPostSignup( MemberDto memberDto ){
        /* params  {   id ="아이디" , pw ="비밀번호" , name="이름" ,   email="이메일" , phone="전화번호" , img ="프로필사진"   }  */
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);
        // --
        boolean result = true;//Dao처리;
        return result; // Dao 요청후 응답 결과를 보내기.
    }

    // 2. =========== 로그인 처리 요청 ===============
    @PostMapping("/member/login") // http://localhost:80/member/login
    @ResponseBody  // 응답 방식 application/json;
    public boolean doPostLogin( LoginDto loginDto ){
        /* params    { id ="아이디" , pw ="비밀번호"  }   */
        System.out.println("MemberController.login");
        System.out.println("loginDto = " + loginDto);
        // --
        boolean result = true; // Dao처리

        if( result ){
            request.getSession().setAttribute("loginMno" , 3 );
            System.out.println("result = " + result);
        }

        return result; // Dao 요청후 응답 결과를 보내기
    }
    // 2. =========== 로그인  ===============
    @GetMapping("/member/login/check") // http://localhost:80/member/login
    @ResponseBody  // 응답 방식 application/json;
    public int doGetLoginInfo(  ){

        int loginMno = 0;

        Object o = request.getSession().getAttribute("loginMno" );
        if( o != null ){
            System.out.println("o = " + o);
             loginMno = (Integer)o;
        }
        System.out.println("loginMno = " + loginMno);
        return loginMno; // Dao 요청후 응답 결과를 보내기
    }

    // 2. =========== 로그인  ===============
    @GetMapping("/member/logout") // http://localhost:80/member/login
    @ResponseBody
    public boolean doGetLoginOut(  ){

        Object o = request.getSession().getAttribute("loginMno" );
        if( o != null ){
            request.getSession().setAttribute("loginMno" , null );
            return true;
        }
        return false;
    }



    // 3. =========== 회원가입 페이지 요청 ===============
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezenweb/signup";
    }
    // 4. =========== 로그인 페이지 요청 ===============
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezenweb/login";
    }
}




















