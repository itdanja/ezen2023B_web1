package ezenweb.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class MemberDto {
    private int no;         /*회원번호*/
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String img;
}
