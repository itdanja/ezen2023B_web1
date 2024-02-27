package ezenweb.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service // 해당 클래스를 스프링 컨터이너(저장소)에 빈(객체) 등록
public class FileService {
    // Controller : 중계자 역할 ( HTTP매핑 , HTTP요청/응답 , 데이터 유효성검사 )등등
    // Service : Controller <--  Service(비지니로직) --> Dao ,  Controller <--> Service(비지니로직)

    // 어디에(PATH) 누구를(파일객체 MultipartFile )
    String uploadPath = "C:\\Users\\504-t\\Desktop\\ezen2023B_web1\\build\\resources\\main\\static\\img\\";

    // 1. 업로드 서비스 메소드
    public String fileUpload( MultipartFile multipartFile){
        // * 파일 이름 조합하기 : 새로운 식별이름과 실제 파일 이름
        String uuid = UUID.randomUUID().toString();
        String filename  = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");
        // 1. [어디에] 첨부파일을 저장할 경로
        // File 클래스 : 파일 관련된 메소드 제공.
        File file = new File( uploadPath+filename );
        // 2. [무엇을] 첨부파일 객체
        // .transferTo( 경로 )
        try { multipartFile.transferTo( file );}
        catch ( Exception e ){
            System.out.println("e = " + e);
            return null;
        }
        return filename; // 반환 : 어떤 이름으로 업로드 했는지 식별명 반환해서
    }
    // 2. 다운로드 메소드
}
/*


        System.out.println("memberDto = " + memberDto);
        // 확인 : 첨부파일 MultipartFile 타입
        MultipartFile 첨부파일 = memberDto.getImg();
        System.out.println( 첨부파일 );         // 첨부파일이 들어있는 객체 주소
        System.out.println( 첨부파일.getSize() );   // 첨부파일 용량 : 18465 바이트
        System.out.println( 첨부파일.getContentType()); //  image/png : 첨부파일의 확장자
        System.out.println( 첨부파일.getOriginalFilename()); // logo.png : 첨부파일의 이름(확장자포함)
        System.out.println( 첨부파일.getName() );   // img : form input name


        // 서버에 업로드 했을때 설계
        // 1. 여러 클라이언트[다수]가 동일한 파일명으로 서버[1명]에게 업로드 했을떄 [ 식별깨짐]
        // 식별이름 : 1.(아이디어)날짜조합+데이터(PK)  2.UUID( 중복 거의 없음 식별 난수 생성 , 가독성 떨어짐 )
        // 2. 클라이언트 화면 표시
        // 업로드 경로 : 아파치 톰캣( static )에 업로드./
        // * 업로드 할 경로 설정( 내장 톰캣 경로 )

        // * 파일 이름 조합하기 : 새로운 식별이름과 실제 파일 이름
        // 식별키 와 실제 이름 구분 : 왜?? 나중에 쪼개서 구분할려고.[ 다운로드시 식별키 빼고 제공할려고 ]
        // 혹시나 파일 이름이 구분문자 가 있을경우 기준이 꺠짐.
        // .replaceAll() : 문자열 치환/교체


 // 1 첨부파일 업로드 하기. [ 업로드란 : 클라이언트의 바이트(대용량/파일)을 서버로 복사 ]
        // 1. [어디에] 첨부파일을 저장할 경로
        // File 클래스 : 파일 관련된 메소드 제공.
        // new File( 파일경로 );
                System.out.println("file = " + file);  // c:\java\test.png
        System.out.println("file.exists() = " + file.exists() );

 */
