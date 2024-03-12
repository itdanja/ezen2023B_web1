package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@ToString
@Builder // 빌더 패턴
public class ProductDto {
    private int pno;
    private String pname;
    private int pprice;
    private String pcontent;
    private byte pstate;
    private String pdate;
    private String plat;
    private String plng;
    private int mno;

    // - 등록할때 이미지
    private List< MultipartFile > uploadFiles;
    // - 출력 /업로드 후 등록 할때 이미지
    private List< String  > pimg;
    // - 출력시 작성자 번호가 아닌 작성자 아이디
    private String mid;

    // + 1. 제품 등록 [ pname,pprice,pcontent,plat,plng, ,  mno(세션) uploadFiles ]
    // + 2. 제품 지도 출력 [ pno, pname pprice pstate plat plng  ]
    // + 3. 제품 지도에서 마커 클릭시 상세 출력 [ pno , pname pprice pcontent pstate pdate plat plng mno pimg ]
}
