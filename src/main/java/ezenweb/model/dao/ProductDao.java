package ezenweb.model.dao;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
public class ProductDao extends  Dao {

    // 1.제품 등록 메소드
    public boolean postProductRegister(ProductDto productDto ){   System.out.println("ProductDao.postProductRegister");
        System.out.println("productDto = " + productDto);
        return false;
    }
    // 2.제품 출력 메소드
    public List<ProductDto> getProductList(){    System.out.println("ProductDao.getProductList");
        return null;
    }


} // class e


























