package ezenweb.controller;

import ezenweb.Service.MemberService;
import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;


// 1단계. V<---->C 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 함수 선언 하고 통신 체크 ( API Tester )
    // 3단계. Controller request 매개변수 매핑
        // -------------- Dto , Service ---------------//
    // 4단계. 응답 : 1.뷰 반환 : text/html;  VS  2. 데이터/값 : @ResponseBody : Application/JSON
@Controller
public class MemberController {
    // * Http요청 객체
    @Autowired
    private HttpServletRequest request;
    // * memberDao 객체
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberService memberService;
    // 1.=========== 회원가입 처리 요청 ===============
    @PostMapping("/member/signup") // http://localhost:80/member/signup
    @ResponseBody // 응답 방식 application/json;
    public boolean doPostSignup( MemberDto memberDto ){
        return memberService.doPostSignup(memberDto);
    }

    // 2. =========== 로그인 처리 요청  / 세션 저장 ===============
    @PostMapping("/member/login") // http://localhost:80/member/login
    @ResponseBody  // 응답 방식 application/json;
    public boolean doPostLogin( LoginDto loginDto ){
        boolean result = memberDao.doPostLogin( loginDto ); // Dao처리
        if( result ){ // 만약에 로그인 성공이면 세션 부여
            // 세션에 저장할 내용물들을 구성( 식별키 만 )
            request.getSession().setAttribute( "loginDto" , loginDto.getId() );    // loginDto : 3
        }
        return result; // Dao 요청후 응답 결과를 보내기
    }// f end
    // 2-2 ============= 로그인 여부 확인 요청 / 세션 호출  ================
    @GetMapping("/member/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        // * 로그인 여부 확인 = 세션이 있다 없다 확인   // 1-> http 요청 객체 호출 , 2->http세션 객체 호출 -> 3.http 세션 데이터 호출
        // null은 형변환이 불가능하기 때문에 유효성검사.
        String loginDto = null;
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if( sessionObj != null ){     loginDto = (String) sessionObj;    }
        // 만약에 로그인했으면(세션에 데이터가 있으면) 강제형변환을 통해 데이터 호출 아니면 0
        return loginDto;
    } // f end
    // 2-3 ============= 로그인 로그아웃 / 세션 초기화  ================
    @GetMapping("/member/logout")
    @ResponseBody // 응답받을 대상이 JS ajax
    public boolean doGetLoginOut(){
        // 1. 로그인 관련 세션 초기화.
            //1. 모든 세션 초기화( 모든 세션의 속성이 초기화 -> 로그인세션 외 다른 세션도 고려 )
            request.getSession().invalidate(); // 현재 요청 보낸 브라우저의 모든 세션 초기화
            //2. 특정 세션속성 초기화 => 동일한 세션속성명에 null 대입한다.
            // request.getSession().setAttribute("loginDto", null );
        return true; // 로그아웃 성공시 => 메인페이지 또는 로그인페이지 이동
    }
    // 3 ============== 회원정보 요청 ( 로그인된 회원 요청 , 패스워드 제외 ) ============
    @GetMapping("/member/login/info")
    @ResponseBody
    public MemberDto doGetLoginInfo( String id ){
        return memberService.doGetLoginInfo( id ); // 서비스 요청과 응답 전달
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


// *로그인 성공시
// 세션 저장소 : 톰캣서버에 *브라우저 마다의 메모리 할당
// 세션 객체 타입 : Object ( 여러가지의 타입들을 저장할려고 )
// 1. Http요청 객체 호출.     HttpServletRequest
// 2. Http세션 객체 호출.     .getSession()
// 3. Http세션 데이터 저장.    .setAttribute( "세션속성명" , 데이터 );     -- 자동형 변환 ( 자 --> 부 )
// -  Http세션 데이터 호출.    .getAttribute( "세션속성명" );             -- 강제형 변환 ( 부 --> 자 ) / 캐스팅
// -  Http세션 초기화         .invalidate();
