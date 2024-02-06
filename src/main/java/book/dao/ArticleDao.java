package book.dao;

import book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class ArticleDao {
    // 1. 필드
    private Connection conn;        // DB 연동 인터페이스
    private PreparedStatement ps;   // SQL 실행,매개변수 인터페이스
    private ResultSet rs;           // SQL 실행결과를 호출하는 인터페이스

    // 2. 생성자 : db연동 코드
    public  ArticleDao(){
        try {
            // 1. jdbc 라이브러리 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root","1234");
            System.out.println("DB success");
        }catch (Exception e ){
            System.out.println("DB fail: " + e);
        }
    } // 생성자 end

    public boolean createArticle( ArticleForm form ){
        try{
            // 1. SQL 작성
            String sql = "insert into article( title , content ) values(?,?)";
            // 2. SQL 기재
            ps = conn.prepareStatement( sql );
            // 3. SQL 매개변수 정의
            ps.setString( 1 , form.getTitle() );
            ps.setString( 2 , form.getContent() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 실핼 결과
            if( count == 1 ){ return true;}
            // 6. 함수 리턴
        }catch (Exception e ){      System.out.println(e);     }
        return false;
    }
}
