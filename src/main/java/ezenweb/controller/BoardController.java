package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import ezenweb.service.BoardService;
import ezenweb.service.FileService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/board") // 공통 URL
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

    // 1. 글쓰기 처리                    /board/write.do       post          Dto         true/false
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite( BoardDto boardDto){
        System.out.println("BoardController.doPostBoardWrite");
        // 1. 현재 로그인된 세션( 톰캣서버(자바프로그램) 메모리(JVM) 저장소 ) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if( object == null ) return -2; // 세션없다(로그인 안했다.)
        // 2. 형변환
        String mid = (String) object;
        // 3. mid를 mno 찾아오기
        long mno = memberService.doGetLoginInfo( mid ).getNo();
        // 4. 작성자번호 대입
        boardDto.setMno( mno );
        return boardService.doPostBoardWrite( boardDto );
    }
    // 2. 전체 글 출력 호출               /board/do             get            x , 페이징처리 , 검색
    @GetMapping("/do")  // (쿼리스트링)매개변수 : 현재페이지
    @ResponseBody       // @RequestParam : 쿼리스트링
    public BoardPageDto doGetBoardViewList(
            @RequestParam int page , @RequestParam int pageBoardSize ,
            @RequestParam int bcno ,
            @RequestParam("key") String field , @RequestParam("keyword") String value ){
        System.out.println("BoardController.doGetBoardViewList");
        return  boardService.doGetBoardViewList( page , pageBoardSize , bcno , field , value  );
    }

    // 3. 개별 글 출력 호출               /board/view.do         get           게시물번호      dto
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView(@RequestParam int bno ) {
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView( bno );
    }

    // 4. 글 수정 처리                   /board/update.do        put         dto
    @PutMapping("/update.do")
    @ResponseBody
    public boolean doUpdateBoard( BoardDto boardDto  ){     System.out.println("BoardController.doUpdateBoard");  System.out.println("boardDto = " + boardDto);
        return boardService.doUpdateBoard( boardDto );
    }

    // 5. 글 삭제 처리                    /board/delete.do      delete        게시물번호
    @DeleteMapping("/delete.do")
    @ResponseBody
    public boolean doDeleteBoard( @RequestParam int bno ){ System.out.println("BoardController.doDeleteBoard");
        return boardService.doDeleteBoard(bno);
    }

    @Autowired
    private FileService fileService;

    // 6. 다운로드 처리 ( 함수만들때 고민할점. 1.매개변수 : 파일명  2.반환 3.사용처 : get http요청 )
    @GetMapping("/file/download")
    @ResponseBody
    public void getBoardFileDownload( @RequestParam String bfile ){
        fileService.fileDownload( bfile );
    }

    // ==================== 머스테치는 컨트롤에서 뷰 반환. ============================= //

    // 1. 글쓰기 페이지 이동            /board/write        GET
    @GetMapping("/write")
    public String getBoardWrite(){
        return "ezenweb/board/write";
    }

    // 2. 게시판 페이지 이동            /board              GET
    @GetMapping("")
    public String getBoard(){
        return "ezenweb/board/board";
    }

    // 3. 게시판 상세 페이지 이동        /board/view         GET
    @GetMapping("/view")
    public String getBoardView( int bno ){
        return "ezenweb/board/view";
    }
    // 4. 글수정 페이지 이동            /board/update       GET

} // class end







/*
*         // *5가지
            // 1.
        FileService fileService = new FileService();
        fileService.fileDownload();
            // 2.
        new FileService().fileDownload();
            // 3.
        FileService.getInstance().fileDownload();
            // 4. static
        FileService.fileDownload();
            // 5.
        fileService.fileDownload();
* */













