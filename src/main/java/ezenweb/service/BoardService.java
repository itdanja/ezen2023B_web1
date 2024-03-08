package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
    public BoardPageDto doGetBoardViewList( int page , int pageBoardSize , int bcno , String field , String value  ){   System.out.println("BoardService.doGetBoardViewList");

        // 페이지처리시 사용할 SQL 구문 : limit 시작레코드번호(0부터) , 출력개수
        // 1. 페이지당 게시물을 출력할 개수          [ 출력개수 ]
        // int pageBoardSize = 2;

        // 2. 페이지당 게시물을 출력할 시작 레코드번호. [ 시작레코드번호(0부터) ]
        int startRow = ( page-1 ) * pageBoardSize;
        /*
            pageBoardSize  : 5
                    0 , 5 , 10 , 15 , 20
            pageBoardSize  : 10
                    0 , 10 , 20 , 30
         */
        // 3. 총 페이지수 ( 페이지네이션 사용할 페이지버튼 )
            // 1. 전체 게시물수/ 레코드수
        int totalBoardSize = boardDao.getBoardSize( bcno , field , value  );
            // 2. 총 페이지수 계산 ( 나머지값이 존재하면 +1 )
        int totalPage = totalBoardSize % pageBoardSize == 0 ?
                        totalBoardSize / pageBoardSize :
                        totalBoardSize / pageBoardSize + 1 ;
        // 4. 게시물 정보 요청
        List<BoardDto> list = boardDao.doGetBoardViewList( startRow , pageBoardSize , bcno , field , value  );

        // 5. 페이징 버튼 개수
            // 1. 페이지버튼 최대 개수
        int btnSize = 5;        // 5개씩
            // 2. 페이지버튼 시작번호
        int startBtn = (page-1)/btnSize*btnSize+1;   // 1번 버튼
            // 3. 페이지버튼 끝번호
        int endBtn = startBtn + btnSize - 1;         // 5번 버튼
            // 만약에 페이지버튼의 끝번호가 총페이지수 보다는 커질수 없다.
        if( endBtn >= totalPage ) endBtn = totalPage;

        // pageDto 구성 * 빌더패턴 : 생성자의 단점( 매개변수에 따른 유연성 부족 )을 보완
            // new 연산자 없이 builder() 함수 이용한 객체 생성 라이브러리 제공
            // 사용방법 : 클래스명.builder().필드명( 대입값 ).필드명(대입값).build();
            // + 생성자 보단 유연성 : 매개변수의 순서 와 개수 자유롭다.
                // 빌더패턴 vs 생성자 vs 빈생성자 setter
        BoardPageDto boardPageDto = BoardPageDto.builder()
                .page( page )
                .totalBoardSize( totalBoardSize )
                .totalPage( totalPage )
                .list( list )
                .startBtn( startBtn )
                .endBtn( endBtn )
                .build();
        return boardPageDto;
    } // end


    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno ) {
        System.out.println("BoardService.doGetBoardView");
        // 조회수 처리 // 강제 조회수 증가 차단 : 조회수 처리 했다/안했다 증거 남겨서 하루에 한번 또는 회원마다 한번 또는 비회원 ip ( log , 세션 ) ,
        boardDao.boardViewIncrease( bno );
        // 게시물 출력 요청
        return boardDao.doGetBoardView( bno );
    }

    // 4. 글 수정 처리 ( 매개변수 : bno , btitle , bcontent , uploadfile , bcno )
    public boolean doUpdateBoard( BoardDto boardDto  ){ System.out.println("BoardService.doUpdateBoard");
        // ** 1.기존 첨부파일명 구하고
        String bfile = boardDao.doGetBoardView( (int)boardDto.getBno()).getBfile();
        // - 새로운 첨부파일이 있다. 없다.
        if( !boardDto.getUploadfile().isEmpty() ){ // 수정시 새로운 첨부파일이 있으면
            // 새로운 첨부파일을 업로드 하고 기존 첨부파일 삭제
            String fileName =  fileService.fileUpload( boardDto.getUploadfile() );
            if( fileName != null ){ // 업로드 성공
                boardDto.setBfile( fileName ); // 새로운 첨부파일의 이름 dto 대입
                // 기존 첨부파일 삭제. // 2. 기존 첨부파일 삭제
                fileService.fileDelete( bfile );
            }else{  return false;} // 업로드 실패
        }else{  boardDto.setBfile( bfile );  }  // 새로운 첨부파일이 없으면 기존 첨부파일명을 그대로 대입.
        return boardDao.doUpdateBoard( boardDto );
    }
    // 5. 글 삭제 처리
    public boolean doDeleteBoard(  int bno ){   System.out.println("BoardService.doDeleteBoard");
        // - 레코드 삭제 하기전에 삭제할 첨부파일명을 임시로 꺼내둔다.
        String bfile = boardDao.doGetBoardView( bno ).getBfile();
        // 1. DAO 처리
        boolean result = boardDao.doDeleteBoard(bno);
        // 2. Dao 처리 성공시 첨부파일도 삭제
        if( result ){
            if( bfile != null ){ // 기존에 첨부파일이 있었으면.
                fileService.fileDelete( bfile ); // 미리 꺼내둔 삭제할 파일명 대입한다.
            }
        }
        return result;
    }
    // 6. 게시물 작성자 인증
    public boolean boardWriterAuth( long bno , String mid ){
        return  boardDao.boardWriterAuth( bno, mid );
    }

    // 7. 댓글 등록
    public boolean postReplyWrite( Map< String , String > map ){    System.out.println("BoardController.postReplyWrite");
        return boardDao.postReplyWrite(map);
    }
    // 8. 댓글 출력
    public List< Map< String , String > > getReplyDo( int bno ){    System.out.println("BoardController.getReplyDo");
        return boardDao.getReplyDo( bno );
    }

}






















