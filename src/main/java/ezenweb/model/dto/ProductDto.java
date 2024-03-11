package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {

    private int pno;
    private String pname;
    private String pcontent;
    private int pprice;
    private byte pstate;
    private String pdate;
    private String plat;
    private String plng;
    private int mno;

    private List<MultipartFile> uploadFiles ;
    private List<String> pimgs ;
    private String mid;

}
