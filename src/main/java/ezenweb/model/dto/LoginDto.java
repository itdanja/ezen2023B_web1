package ezenweb.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class LoginDto {
    private int no;         /*회원번호*/
    private String id;
    private String pw;
}
