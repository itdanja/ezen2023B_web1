package ezenweb.model.dao;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.stereotype.Component;

@Component
public class MemberDao extends  Dao {
    // 1. 회원가입 메소드
    public boolean doPostSignup( MemberDto memberDto ){
        try{
            String sql ="insert into member(id,pw,name,email,phone,img)values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString( 1, memberDto.getId() );
            ps.setString( 2, memberDto.getPw() );
            ps.setString( 3, memberDto.getName() );
            ps.setString( 4, memberDto.getEmail() );
            ps.setString( 5, memberDto.getPhone() );
            ps.setString( 6, memberDto.getUuidFile() );
            int count = ps.executeUpdate();
            if( count == 1 ){ return true; }
        }catch (Exception e ){  System.out.println("e = " + e);   }
        return false;
    }

    // 2. 로그인 메소드
    public boolean doPostLogin( LoginDto loginDto ){
        try{
            String sql = "select * from member where id = ? and pw = ? ";
            ps = conn.prepareStatement(sql );
            ps.setString(  1 , loginDto.getId() );
            ps.setString( 2 , loginDto.getPw());
            rs = ps.executeQuery();
            if (rs.next()){  return true;  }
        }catch (Exception e ){     System.out.println("e = " + e);   }
        return false;
    }

    // 3. 회원정보 요청
    public MemberDto doGetLoginInfo( String id ){
        MemberDto memberDto = null;
        try{
            String sql = "select * from member where id = ? ";
            ps = conn.prepareStatement( sql );      ps.setString( 1 , id );
            rs = ps.executeQuery();
            if( rs.next() ){
                memberDto = new MemberDto(
                        rs.getInt(1) , rs.getString(2),
                        null, // 비밀번호 빼고.
                        rs.getString(4) , rs.getString(5),rs.getString(6) ,
                        null , // 첨부파일 필드 빼고
                        rs.getString(7)
                        );
            }
        }catch (Exception e ){  System.out.println("e = " + e);}
        return memberDto;
    }


} // class e








