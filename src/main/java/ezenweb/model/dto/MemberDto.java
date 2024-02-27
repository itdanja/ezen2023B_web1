package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class MemberDto {
    private int no;          // input type="text"(자동타입변환->int)
    private String id;       /**/
    private String pw;       /**/
    private String name;         /**/
    private String email;        /**/
    private String phone;        /**/
//    private String img;       // input type="text"(String)
    private MultipartFile img;  // input type="file"(MultipartFile) 첨부파일 형식
    private String uuidFile;    // uuid+file
}
