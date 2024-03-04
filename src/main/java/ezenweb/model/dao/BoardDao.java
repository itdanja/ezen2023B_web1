package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Statement;

@Component
public class BoardDao extends Dao {

    // 1. 글쓰기 처리 [ 글쓰기를 성공했을때 자동 생성된 글번호 반환 , 실패시 0 ]
    public long doPostBoardWrite( BoardDto boardDto){  System.out.println("boardDto = " + boardDto);
        try{
            String sql = "insert into board( btitle,bcontent,bfile,mno,bcno)value(?,?,?,?,?)";
            ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS );
            ps.setString( 1 , boardDto.getBtitle() );
            ps.setString( 2 , boardDto.getBcontent() );
            ps.setString( 3 , boardDto.getBfile() );
            ps.setLong( 4 , boardDto.getMno() );
            ps.setLong( 5 , boardDto.getBcno() );
            int count = ps.executeUpdate();
            if( count == 1 ){
                rs = ps.getGeneratedKeys();
                if( rs.next() ){ return rs.getLong( 1 ); } // 생성된 pk번호 반환
            }
        }catch (Exception e ){   System.out.println("e = " + e);    }
        return 0; // 실패시 0
    }
    // 2. 전체 글 출력 호출

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno ) { System.out.println("BoardDao.doGetBoardView");
        BoardDto boardDto = null;
        try{
            String sql ="select * from board where bno = ? ";
            ps =conn.prepareStatement(sql);
            ps.setLong( 1 , bno );       rs = ps.executeQuery();
            if( rs.next() ){
                boardDto = new BoardDto( rs.getLong( "bno" ) , rs.getString( "btitle" ) ,
                        rs.getString( "bcontent" ) , rs.getString( "bfile" ) ,
                        rs.getLong("bview") , rs.getString("bdate") ,
                        rs.getLong("mno") , rs.getLong("bcno") , null );
            }
        }catch (Exception e ){  System.out.println("e = " + e);   }
        return boardDto;
    }
    // 4. 글 수정 처리

    // 5. 글 삭제 처리



}


