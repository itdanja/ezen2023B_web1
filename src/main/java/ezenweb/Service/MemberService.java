package ezenweb.Service;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private FileService fileService; // 외부 서비스
    @Autowired
    private MemberDao memberDao; // 외부 리포지토리

    // 1. 회원가입 서비스
    public boolean doPostSignup( MemberDto memberDto ){
        // 1. 파일 처리
        String fileName = fileService.fileUpload( memberDto.getImg() );
        if( fileName != null ){ // 업로드 성공 했으면
            // 2. DB 처리
            // dto에 업로드 성공한 파일명을 대입한다.
            memberDto.setUuidFile( fileName );
            return memberDao.doPostSignup( memberDto );
        }
        return false;
    } // end

    // 2. 로그인 서비스

    // 3. 회원정보 요청 서비스
    public MemberDto doGetLoginInfo( String id ){
        // 1. DAO 호출
        return memberDao.doGetLoginInfo( id );
    }

}






