package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    // 1. 글쓰기 처리
    public boolean doPostBoardWrite( BoardDto boardDto){
        System.out.println("BoardService.doPostBoardWrite");
        return boardDao.doPostBoardWrite( boardDto );
    }
    // 2. 전체 글 출력 호출

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno ) {
        System.out.println("BoardService.doGetBoardView");
        return boardDao.doGetBoardView( bno );
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리

}
