package ezenweb.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {

    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;

    public Dao(){ //DB연동 - 생성자
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root","1234"
            );
        }catch (Exception e ){
            System.out.println("e = " + e);
        }
    }
}
