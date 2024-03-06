package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    // 2-2 전체 게시물 수 호출
    public int getBoardSize( int bcno , String field , String value ){

        System.out.println("bcno = " + bcno + ", field = " + field + ", value = " + value);

        try{
            String sql = "select count(*) from board b inner join member m " +
                    " on b.mno = m.no ";

            // ==================== 1.만약에 카테고리 조건이 있으면 where 추가.
            if( bcno > 0 ){ sql += " where b.bcno = "+bcno ; }
            // ==================== 2.만약에 검색 있을때.
            if(  !value.isEmpty() ){    System.out.println("★검색 키워드가 존재.");
                if( bcno > 0 ){ sql += " and "; }   // 카테고리가 있을때. and로 연결
                else{ sql += " where "; }           // 카테고리 없을때 where 로 연결
                sql += field+" like '%"+value+"%' ";
            }

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if( rs.next() ){ return rs.getInt(1); }
        }catch (Exception e ){  System.out.println("e = " + e);}
        return 0;
    }
    // 2-1. 전체 글 출력 호출
    public List<BoardDto> doGetBoardViewList( int startRow , int pageBoardSize , int bcno  , String field , String value  ){ System.out.println("BoardDao.doGetBoardViewList");

        System.out.println("startRow = " + startRow + ", pageBoardSize = " + pageBoardSize + ", bcno = " + bcno + ", field = " + field + ", value = " + value);

        BoardDto boardDto = null;   List<BoardDto> list = new ArrayList<>();
        try{  // String sql = "select * from board";
            // SQL 앞부분
            String sql = "select * from board b inner join member m " +
                    " on b.mno = m.no ";

            // --- SQL 가운데부분 [ 조건에 따라 where 절 추가 ]
            // ==================== 1.만약에 카테고리 조건이 있으면 where 추가.
            if( bcno > 0 ){ sql += " where bcno = "+bcno ; }

            // ==================== 2.만약에 검색 있을때.
            if(  !value.isEmpty() ){    System.out.println("★검색 키워드가 존재.");
                if( bcno > 0 ){ sql += " and "; }   // 카테고리가 있을때. and로 연결
                else{ sql += " where "; }           // 카테고리 없을때 where 로 연결
                sql += field+" like '%"+value+"%' ";
            }

            // SQL 뒷부분
            sql += " order by b.bdate desc " +
                    " limit ? , ?";

            ps =conn.prepareStatement(sql);
            ps.setInt( 1 , startRow );
            ps.setInt( 2 , pageBoardSize );

            rs = ps.executeQuery();
            while ( rs.next() ){
                boardDto = new BoardDto( rs.getLong( "bno" ) , rs.getString( "btitle" ) ,
                        rs.getString( "bcontent" ) , rs.getString( "bfile" ) ,
                        rs.getLong("bview") , rs.getString("bdate") ,
                        rs.getLong("mno") , rs.getLong("bcno") , null ,
                        rs.getString("id") , rs.getString("img") );
                list.add( boardDto );
            }
        }catch (Exception e ){ System.out.println("e = " + e);  }
        return list;
    }
    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno ) { System.out.println("BoardDao.doGetBoardView");
        BoardDto boardDto = null;
        try{  String sql ="select * from board b inner join member m on b.mno = m.no where b.bno = ? ";
            ps =conn.prepareStatement(sql);
            ps.setLong( 1 , bno );       rs = ps.executeQuery();
            if( rs.next() ){
                boardDto = new BoardDto( rs.getLong( "bno" ) , rs.getString( "btitle" ) ,
                        rs.getString( "bcontent" ) , rs.getString( "bfile" ) ,
                        rs.getLong("bview") , rs.getString("bdate") ,
                        rs.getLong("mno") , rs.getLong("bcno") , null ,
                        rs.getString("id") , rs.getString("img") );
            }
        }catch (Exception e ){  System.out.println("e = " + e);   }
        return boardDto;
    }
    // 4. 글 수정 처리

    // 5. 글 삭제 처리



}


