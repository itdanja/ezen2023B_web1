package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class MemberDto {
    private int no;         /*회원번호*/
    private String id;       /*회원번호*/
    private String pw;       /*회원번호*/
    private String name;         /*회원번호*/
    private String email;        /*회원번호*/
    private String phone;        /*회원번호*/
    private MultipartFile img;          /*회원번호*/
}
