package example.day08._2인과제.controller;

import example.day04._1리스트컬렉션.Board;
import example.day08._2인과제.BoardDao;
import example.day08._2인과제.BoardDto;
import ezenweb.model.dto.MemberDto;

import java.util.List;

public class BoardController {

    BoardDao boardDao = new BoardDao();

    // 1. 저장
    public boolean create(BoardDto boardDto){               System.out.println("BoardController.create");System.out.println("boardDto = " + boardDto);
        boolean result =  boardDao.create( boardDto );      System.out.println("result = " + result);
        return result;
    }
    // 2. 전체 호출
    public List<BoardDto> read( ){                          System.out.println("BoardController.read");
        List<BoardDto> result = boardDao.read();            System.out.println("result = " + result);
        return result;
    }
    // 3. 수정
    public boolean update(BoardDto boardDto) {              System.out.println("BoardController.update");System.out.println("boardDto = " + boardDto);
        boolean result = boardDao.update( boardDto );       System.out.println("result = " + result);
        return result;
    }
    // 4. 삭제
    public boolean delete( int bno ) {                      System.out.println("BoardController.delete");System.out.println("bno = " + bno);
        boolean result = boardDao.delete( bno );            System.out.println("result = " + result);
        return result;
    }
} // class end
