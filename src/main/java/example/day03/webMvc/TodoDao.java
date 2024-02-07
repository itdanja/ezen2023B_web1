package example.day03.webMvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// 데이터 접근 객체 : db에 접근과 sql( 비지니스 로직) 역할
@Component // 스프링 컨테이너에 빈 등록
@Slf4j
public class TodoDao {
    // 1. 필드
    private Connection conn;        // DB 연동 인터페이스
    private PreparedStatement ps;   // SQL 실행,매개변수 인터페이스
    private ResultSet rs;           // SQL 실행결과를 호출하는 인터페이스

    // 2. 생성자 : db연동 코드
    public  TodoDao(){
        try {
            // 1. jdbc 라이브러리 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root","1234");
            System.out.println("DB success");
        }catch (Exception e ){
            log.error( e.toString() );
        }
    } // 생성자 end


    // 3. 메소드
    // 2. 할일등록 함수
    public boolean doPost( TodoDto todoDto ){
        try{
            // 1. SQL 작성
            String sql = "insert into todo(content,deadline) values(?,?)";
            // 2. SQL 기재
            ps = conn.prepareStatement( sql );
            // 3. SQL 매개변수 정의
            ps.setString( 1 , todoDto.getContent() );
            ps.setString( 2 , todoDto.getDeadline() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 실핼 결과
            if( count == 1 ){ return true;}
            // 6. 함수 리턴
        }catch (Exception e ){      System.out.println(e);     }
        return false;
    } // me

    // 3. 할일목록출력 함수
    public ArrayList<TodoDto> doGet(){  // 레코드1개 == TodoDto 1개   // 레코드여러개 == List<TodoDto>
        // 0. 반환할 dotoList 객체
        ArrayList<TodoDto> list = new ArrayList<>();
        try{
            // 1. SQL 작성
            String sql = "select * from todo";
            // 2. SQL 기재
            ps = conn.prepareStatement( sql );
            // 3. SQL 매개변수 정의
            // 4. SQL 실행
            rs = ps.executeQuery();
            // 5. SQL 실핼 결과
            while ( rs.next() ){  // next() 레코드 이동.
                // 레코드1개당 --> dto 1개
                TodoDto todoDto = new TodoDto(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getString("deadline"),
                        rs.getBoolean("state")
                );
                // while문 끝나면 dto 사라져..while밖에있는 객체로 이동.
                list.add( todoDto );
            } // while end
            // 6. 함수 리턴
        }catch (Exception e ){    System.out.println(e);  }
        return list; // 리스트 반환
    } // me

    // 4.할일 상태 수정 함수
    public boolean doPut( TodoDto todoDto){
        try {
            String sql = "update todo set state = ? where id = ? "; // 1. SQL 작성
            ps = conn.prepareStatement(sql);                        // 2. SQL 기재
            ps.setBoolean( 1  , todoDto.isState() ); // 3. SQL 매개변수 정의
            ps.setInt( 2 , todoDto.getId() );
            int count = ps.executeUpdate(); //  // 4. SQL 실행 executeUpdate() sql실행후 반영된 레코드수 반환
            if( count == 1 ){ return true; }  // 5. SQL 실핼 결과    // 6. 함수 리턴
        }catch (Exception e ){   System.out.println(e);    }
        return false;
    } // me

    // 5.할일 삭제 함수
    public boolean doDelete( int id ){
        try {
            String sql = "delete from todo where id = ?";// 1. SQL 작성
            ps = conn.prepareStatement(sql); // 2. SQL 기재
            ps.setInt( 1 , id );// 3. SQL 매개변수 정의
            int count = ps.executeUpdate(); // 4. SQL 실행
            if( count == 1 ){ return true; }// 5. SQL 실핼 결과// 6. 함수 리턴
        }catch (Exception e ){ System.out.println(e);}
        return false;
    } // me


}












