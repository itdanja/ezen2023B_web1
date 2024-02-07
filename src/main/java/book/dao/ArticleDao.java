package book.dao;

import book.dto.ArticleForm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component // 스프링 컨테이너에 해당 클래스를 빈(객체) 등록
public class ArticleDao {
    // ---------- JDBC DB연동 ----------//
    // 1. DB연동 필요한 인터페이스( 구현객체 => 각 회사(mysql com.mysql.cj.jdbc패키지내 Driver클래스 ) ) 필드 선언
    private Connection conn; // DB연동 결과 객체를 연결 , 기재된 SQL Statement객체 반환.
    private PreparedStatement ps;  // 기재된 SQL에 매개변수 할당 , SQL 실행
    private ResultSet rs;          // select 결과 여러개 레코드를 호출

    public ArticleDao(){
        try {
            // 1. mysql JDBC 호출 ( 각 회사별  상이 , 라이브러리 다운로드 )
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 해당 db서버의 주소와 db연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");
        }catch (Exception e ){   System.out.println(e);      }
    }
    // ---------- ---------- ----------//
    // ---------- SQL 이벤트  ----------//
    // 1. 등록
    public boolean createArticle( ArticleForm form ){
        System.out.println("ArticleDao.createArticle");
        System.out.println("form = " + form);

        try{      // 0. try{}catch (Exception e ){}
            String sql ="insert into article( title , content ) values( ? , ? )"; // 1.
            ps = conn.prepareStatement(sql); // 2.
            ps.setString( 1 , form.getTitle() ); // 3.
            ps.setString( 2 , form.getContent() );
            // 4.
            int count = ps.executeUpdate();
            // 5.
            if( count == 1 ) return true;
        }catch (Exception e ){  System.out.println(e);  }
        return false;
    }

    // ---------- ---------- ----------//

}


















