package ezenweb.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder // 생성자 단점을 보완한 라이브러리 함수 제공.
public class BoardPageDto {
    //  부가정보 //
    // 1. 현재페이지
    private int page;
    // 2. 총 게시물수
    private int totalPage;
    // 3. ----------------
    // 실제 내용 //
    private List<BoardDto> list;
}
