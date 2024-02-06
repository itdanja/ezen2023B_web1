package book.dto;


import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleForm {
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드
}
