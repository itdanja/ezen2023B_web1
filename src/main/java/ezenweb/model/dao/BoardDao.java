package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;

@Component
public class BoardDao extends Dao {

    // 1. 글쓰기 처리
    public boolean doPostBoardWrite( BoardDto boardDto){
        System.out.println("BoardDao.doPostBoardWrite");
        System.out.println("boardDto = " + boardDto);
        return true;
    }
    // 2. 전체 글 출력 호출

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno ) {
        System.out.println("BoardDao.doGetBoardView");
        return null;
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리



}


