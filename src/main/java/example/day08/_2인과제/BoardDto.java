package example.day08._2인과제;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class BoardDto {
    private int bno;
    private String bcontent;
    private String bwriter;
    private String bpassword;
}
