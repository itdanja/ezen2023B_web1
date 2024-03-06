package ezenweb.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.UUID;

@Service // 해당 클래스를 스프링 컨터이너(저장소)에 빈(객체) 등록
public class FileService {
    // Controller : 중계자 역할 ( HTTP매핑 , HTTP요청/응답 , 데이터 유효성검사 )등등
    // Service : Controller <--  Service(비지니로직) --> Dao ,  Controller <--> Service(비지니로직)

    // 어디에(PATH) 누구를(파일객체 MultipartFile )
    String uploadPath = "C:\\Users\\MSI\\ezen2023B_web1\\build\\resources\\main\\static\\img\\";

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
    // 2. 다운로드
    @Autowired
    private HttpServletResponse response;
    public void fileDownload( String uuidFile ){
        // 1. 다운로드할 파일의 경로 찾기
        String downloadFilePath = uploadPath + uuidFile;
        // 2. uuid 제거된 순수 파일명 [ 다운로드 시 출력되는 파일명 이니까 uuid 제거 ]
        String fileName = uuidFile.split("_")[1]; // _ 기준으로 쪼갠후 뒷자리 파일명만 호출
        try {
            // 3. 다운로드 형식 구성 [ 브라우저가 지원하는 다운로드 형식 - 별도로 커스텀 불가능 ]
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 4. 다운로드
            // --------------------------------- 서버가 해당 파일 읽어오기 -------------------------------- //
            File file = new File( downloadFilePath ); // 1. 서버가 해당 파일 읽어오기
            BufferedInputStream fin = new BufferedInputStream( new FileInputStream(file) ); // 2. 버퍼스트림 이용한 바이트로 파일 읽어오기
            byte[] bytes = new byte[ (int)file.length() ]; // 3. 파일의 용량[바이트] 만큼 바이트배열 선언
            fin.read( bytes );  // 4. 버퍼스트림이 읽어온 바이트들을 바이트배열에 저장
            // ---------------------------------- 서버가 읽어온파일을 클라이언트에게 응답하기 -------------------------- //
            BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream() );   // 1. 버퍼스트림 이용한 response 으로 응답하기
            fout.write( bytes );  // 2. 읽어온 바이트[파일] 내보내기
            fout.flush(); fout.close(); fin.close(); // 3. 안전하게 스트림 닫기
        }catch (Exception e ){   System.out.println("e = " + e);   }
    }

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
