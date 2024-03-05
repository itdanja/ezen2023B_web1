package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
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
    public int getBoardSize( int bcno ,  String key , String keyword  ){
        try{

            String sql = "select count(*) from board b natural join member m ";

            // -만약에 전체보기 가 아니면 [ 카테고리별 개수 ]
            if( bcno != 0 ) { sql += " where b.bcno = "+ bcno; }

            // -만약에 검색이 있으면
            if( !key.isEmpty() && !keyword.isEmpty() ) {
                if( bcno !=0 ) sql += " and ";
                else sql += " where ";
                sql += " "+key+" like '%"+keyword+"%' ";
            }

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if( rs.next() ){ return rs.getInt(1); }
        }catch (Exception e ){  System.out.println("e = " + e);}
        return 0;
    }

    // 2-1. 전체 글 출력 호출
    public List<BoardDto> doGetBoardViewList( int startRow , int pageBoardSize ,int bcno ,  String key , String keyword   ){ System.out.println("BoardDao.doGetBoardViewList");
        BoardDto boardDto = null;   List<BoardDto> list = new ArrayList<>();
        try{


            String sql = "select * from board b inner join member m on b.mno = m.no" ;

            // -만약에 카테고리를 선택했으면 [ 전체보기 가 아니면 ]
            if( bcno != 0) { sql += " where b.bcno = " + bcno; }

            // -만약에 검색이 있으면 [ key 와 keyword 모두 빈문자열이 아니면 ]
            // 문자열.isEmpty() : 문자열이 비어 있으면 [ '' ] null vs '' 다름
            if( !key.isEmpty() && !keyword.isEmpty()  ) {

                // -만약에 카테고리내 검색이면 [ 이미 where 구문이 존재하기 때문에 and 조건 추가 ]
                if( bcno != 0 ) sql+=" and ";
                else sql += " where "; // [ 카테고리가 전체검색이면 where 구문이 없었으므로 where 추가 ]

                sql += " "+key+" like '%"+keyword+"%' ";

            }

            // +뒤부분 공통 SQL
            sql += " order by b.bdate desc limit ? , ?";
            // order by b.bdate desc :  최신글부터 정렬/출력
            // limit ? , ? : 시작번호 부터 최대게시물수 만큼 출력

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


