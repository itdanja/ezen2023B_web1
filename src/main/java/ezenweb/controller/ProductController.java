package ezenweb.controller;

import ezenweb.model.dto.ProductDto;
import ezenweb.service.ProductService;
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


    // 1.제품 등록 메소드
    @PostMapping("/register.do")
    @ResponseBody
    public boolean postProductRegister( ProductDto productDto ){   System.out.println("ProductController.postProductRegister");
        return productService.postProductRegister(productDto);
    }
    // 2.제품 출력 메소드
    @GetMapping("/list")
    @ResponseBody
    public List<ProductDto> getProductList(){   System.out.println("ProductController.getProductList");
        return productService.getProductList();
    }

}

/*

    화 [ 제품등록 , 제품출력 ]
    수 [ 지도 사이드바 출력
    목 [ 쪽지 DB처리 ]
    금 [ 소켓 알림처리 ]

 */