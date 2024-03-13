package ezenweb.controller;

import ezenweb.model.dto.ProductDto;
import ezenweb.service.MemberService;
import ezenweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired private ProductService productService;
    @Autowired private HttpServletRequest request;
    @Autowired private MemberService memberService;

    // ====================== 1. 서비스 요청

    // # 1. 등록 서비스/기능처리 요청
    @PostMapping("/register.do")
    @ResponseBody
    public boolean postProductRegister( ProductDto productDto){  System.out.println("ProductController.postProductRegister");
        // - 1. 등록자 세션 처리
        Object object = request.getSession().getAttribute("loginDto");
        if( object == null )return false;
        productDto.setMno( memberService.doGetLoginInfo( (String)object ).getNo() );
        // productDto.setMno( 1 ); // 1번(없으면 본인pc기준으로 아무거나) 회원 으로 테스트;
        return productService.postProductRegister( productDto );
    }
    // # 2. 제품 출력( 지도에 출력할 ) 요청
    @GetMapping("/list.do") // http://localhost/product/list.do
    @ResponseBody
    public List<ProductDto> getProductList(){   System.out.println("ProductController.getProductList");
        return productService.getProductList();
    }

    //3. 해당 제품의 찜하기 등록  // 언제실행: 로그인했고 찜하기버튼 클릭시  , 매개변수 : pno  , 리턴 : boolean(등록 성공/실패)
    @GetMapping("/plike/write.do")
    @ResponseBody
    public boolean getPlikeWrite( int pno ) {
        return productService.getPlikeWrite( pno  );
    }
    //4. 해당 제품의 찜하기 상태 출력 // 언제실행: 로그인했고 찜하기버튼 출력시   , 매개변수 : pno , 리턴 : boolean(등록 있다/없다)
    @GetMapping("/plike/view.do")
    @ResponseBody
    public boolean getPlikeView( int pno ){
        return productService.getPlikeView( pno );
    }
    //5 해당 제품의 찜하기 취소/삭제 // 언제실행: 로그인했고 찜하기버튼 클릭시  , 매개변수 : pno , 리턴 :  boolean(취소 성공/실패)
    @GetMapping("/plike/delete.do")
    @ResponseBody
    public boolean getPlikeDelete( int pno ){
        return productService.getPlikeDelete( pno );
    }

    // ====================== 2. 화면 요청
    // # 1. 등록 페이지/화면/뷰 요청
    @GetMapping("/register")
    public String productRegister(){
        return "ezenweb/product/register";
    }
    // # 2. 제품 지도 페이지/화면/뷰 요청
    @GetMapping("/list")
    public String productList(){
        return "ezenweb/product/list";
    }

}











