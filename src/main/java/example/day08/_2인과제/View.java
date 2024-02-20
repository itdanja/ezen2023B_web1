package example.day08._2인과제;

import example.day08._2인과제.controller.BoardController;

public class View {
    public static void main(String[] args) {
        BoardController boardController = new BoardController();
        // 1. 저장 호출 테스트
        boardController.create( new BoardDto( 0,"내용","작성자","비밀번호") );
        // 2. 전체 호출 테스트
        boardController.read();
        // 3. 수정 호출 테스트
        boardController.update( new BoardDto( 1 , "수정내용",null , null ) );
        // 4. 삭제 호출 테스트
        boardController.delete( 1 );
    }
}
