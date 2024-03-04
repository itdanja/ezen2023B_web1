package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardPageDto;
import ezenweb.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;
    @Autowired
    private FileService fileService;

    // 1. 글쓰기 처리
    public long doPostBoardWrite( BoardDto boardDto){      System.out.println("BoardService.doPostBoardWrite");
        // 1. 첨부 파일 처리
        if( !boardDto.getUploadfile().isEmpty() ){ // 첨부파일이 존재하면
            String fileName = fileService.fileUpload( boardDto.getUploadfile() );
            if( fileName != null ){ // 업로드 성공했으면
                boardDto.setBfile( fileName ); // db저장할 첨부파일명  대입
            }else{  return -1;   } // 업로드에 문제가 발생하면 글쓰기 취소
        }
        // 2. DB 처리
        return boardDao.doPostBoardWrite( boardDto );
    }
    // 2. 전체 글 출력 호출
    public BoardPageDto doGetBoardViewList(int page , int listSize , int bcno ) {


        // 1. 페이지당 개수
        //int listSize = 3;

        // 2. 시작번호
        int startRow = ( page-1 )*listSize;

        // 3. 전체 게시물수
        // int totalBoard = boardDao.doGetBoardViewListCount();
        int totalBoard = boardDao.doGetBoardViewListCount( bcno );

        // 4. 전체 페이지수
        int totalPage = totalBoard%listSize == 0 ? // 만약에 나머지가 없으면
                        totalBoard/listSize : 	  // 몫
                        totalBoard/listSize+1;

        // 1. 페이지버튼 번호의 최대개수
        int btnsize = 5;
        // 2. 페이지버튼 번호의 시작번호
        int startbtn = ( (page-1) / btnsize ) * btnsize + 1 ;
        // 3. 페이지버튼 번호의 마지막번호
        int endbtn = startbtn+(btnsize-1);
        // * 단 마지막번호는 총페이지수 보다 커질수 없음 [
        // 만약에 마지막번호가 총 페이지수보다 크거나 같으면 총페이지 수로 제한두기
        if( endbtn >= totalPage ) endbtn = totalPage;

        // https://github.com/itdanja/ezen_web_2023_A/blob/main/jspweb/src/main/java/controller/board/BoardInfoController.java

        //List<BoardDto> list = boardDao.doGetBoardViewList( startRow , listSize );
        List<BoardDto> list = boardDao.doGetBoardViewList( startRow , listSize , bcno );

        BoardPageDto boardPageDto = new BoardPageDto( totalPage , startbtn , endbtn , list );

        return boardPageDto;

    }

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno ) {
        System.out.println("BoardService.doGetBoardView");
        return boardDao.doGetBoardView( bno );
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리

}
