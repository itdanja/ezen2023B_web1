package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    // 3-2. 개별 글 출력시 조회수 증가
    public void boardViewIncrease( int bno ){
        try{
            String sql = "update board set bview = bview + 1 where bno = "+ bno;
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e ){  System.out.println("e = " + e); }
    }

    // 3-1. 개별 글 출력 호출
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
    public boolean doUpdateBoard( BoardDto boardDto  ){ System.out.println("BoardDao.doUpdateBoard");
        System.out.println("boardDto = " + boardDto);
        try{
            String sql = "update board set btitle = ? , bcontent = ? , bcno = ? , bfile = ? where bno = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString( 1 , boardDto.getBtitle() );   ps.setString( 2 , boardDto.getBcontent() );
            ps.setLong( 3 , boardDto.getBcno() );       ps.setString( 4 , boardDto.getBfile() );
            ps.setLong( 5 , boardDto.getBno() );

            int count = ps.executeUpdate(); if( count == 1 ){ return true; }
        }catch (Exception e ){ System.out.println("e = " + e); } return false;
    }

    // 5. 글 삭제 처리
    public boolean doDeleteBoard(  int bno ){        System.out.println("BoardDao.doDeleteBoard");
        try{
            String sql = "delete from board where bno = "+bno;
            ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate(); if( count == 1 ) return true;
        }catch (Exception e ){ System.out.println("e = " + e);} return false;
    }
    // 6. 게시물 작성자 인증
    public boolean boardWriterAuth( long bno , String mid ){
        try{
            String sql = "select * from board b inner join member m " +
                    " on b.mno = m.no " +
                    " where b.bno = ? and m.id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setLong( 1 , bno );
            ps.setString( 2 , mid );
            rs = ps.executeQuery();
            if( rs.next() ){ return true; }
        }catch (Exception e ){  System.out.println("e = " + e); }
        return  false;
    }
    // 7. 댓글 등록
    public boolean postReplyWrite( Map< String , String > map ){    System.out.println("BoardController.postReplyWrite");
        try{
            String sql = "insert into breply( brcontent , brindex,mno,bno)" +
                    " values(? ,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString( 1 , map.get("brcontent") );
            ps.setString( 2 , map.get("brindex") );
            ps.setString( 3 , map.get("mno") );
            ps.setString( 4 , map.get("bno") );
            int count = ps.executeUpdate();
            if( count == 1 ) { return  true; }
        }catch (Exception e ){  System.out.println("e = " + e);  }
        return false;
    }
    // 8. 댓글 출력
    public List< Map< String , String > > getReplyDo( int bno ){    System.out.println("BoardController.getReplyDo");
        List< Map<String,String> > list = new ArrayList<>();
        try{
            // 상위 댓글 먼저 출력
            String sql ="select * from breply where brindex = 0 and bno="+bno;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){
                Map<String,String> map = new HashMap<>(); // map vs dto
                map.put( "brno" , rs.getString("brno") );
                map.put( "brcontent" , rs.getString("brcontent") );
                map.put( "brdate" , rs.getString("brdate") );
                map.put( "mno" , rs.getString("mno") );
                list.add( map );
            }
        }catch (Exception e ){  System.out.println("e = " + e);    }
        return list;
    }



}



















