package ezenweb.service;

import ezenweb.model.dao.ProductDao;
import ezenweb.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductDao productDao;
    // 1.제품 등록 메소드
    public boolean postProductRegister(ProductDto productDto ){   System.out.println("ProductService.postProductRegister");
        return productDao.postProductRegister(productDto);
    }
    // 2.제품 출력 메소드
    public List<ProductDto> getProductList(){   System.out.println("ProductService.getProductList");
        return productDao.getProductList();
    }
}
