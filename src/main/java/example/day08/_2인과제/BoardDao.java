package example.day08._2인과제;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BoardDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public  BoardDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/day08",
                    "root", "1234"
            );
        }catch (Exception e ){
            System.out.println("e = " + e);
        }
    }

    // 1. 저장
    public boolean create(BoardDto boardDto){  System.out.println("BoardDao.create");       System.out.println("boardDto = " + boardDto);
        try{
            String sql = "insert into board( bcontent , bwriter , bpassword )values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString( 1 , boardDto.getBcontent() );
            ps.setString( 2 , boardDto.getBwriter() );
            ps.setString( 3 , boardDto.getBpassword() );
            ps.executeUpdate();   return true;
        }catch (Exception e ){  System.out.println("e = " + e);  }  return false;
    }
    // 2. 전체 호출
    public List<BoardDto> read( ){ System.out.println("BoardDao.read");
        List<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select * from board";
            ps = conn.prepareStatement(sql);         rs = ps.executeQuery();
            while ( rs.next() ){
                list.add( new BoardDto( rs.getInt( 1 ) , rs.getString(2) ,
                        rs.getString(3) , rs.getString(4) ));
            }
        }catch (Exception e ){  System.out.println("e = " + e);  }  return list;
    }
    // 3. 수정
    public boolean update(BoardDto boardDto) {  System.out.println("BoardDao.update");System.out.println("boardDto = " + boardDto);
        try{
            String sql = "update board set bcontent = ? where bno = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString( 1 , boardDto.getBcontent() );
            ps.setInt( 2 , boardDto.getBno()  );
            ps.executeUpdate();    return true;
        }catch (Exception e ){  System.out.println("e = " + e);  }  return false;
    }
    // 4. 삭제
    public boolean delete( int bno ) {   System.out.println("BoardDao.delete");  System.out.println("bno = " + bno);
        try{
            String sql = "delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt( 1 , bno );
            ps.executeUpdate();    return true;
        }catch (Exception e ){  System.out.println("e = " + e);  }return false;
    }
}






