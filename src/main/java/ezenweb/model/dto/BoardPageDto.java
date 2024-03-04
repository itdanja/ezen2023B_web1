package ezenweb.model.dto;

import ezenweb.model.dto.BoardDto;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardPageDto {

    int totalPage;
    int startBtn;
    int endBtn;
    List<BoardDto> list ;

}
