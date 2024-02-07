package book.dto;

import lombok.*;

// 입력 폼 전용 DTO
    // 관례적으로 모든 객체 필드는 직접 접근 권장하지 않는다. private , 안정성보장 , 캡슐화 특징 , setter,getter,생성자
    // 필드는 private , 생성자 : 빈,풀  , 메소드 : tostring() , setter,getter
    // 간단한생성자와 tostring() , setter,getter 메소드는 롬복 으로 리팩터링
    // @어노테이션 : 컴파일시 해당 클래스/함수/필드 에 (미리)정보 주입
@AllArgsConstructor // 컴파일시 모든 필드 생성자를 자동으로 만들어준다.
@NoArgsConstructor  // 컴파일시 기본 생성자를 자동으로 만들어준다.
@ToString           // 컴파일시 toString() 자동으로 만들어준다.
@Getter@Setter      // 컴파일시 setter / getter 메소드를 자동으로 만들어준다.
public class ArticleForm {
    // 1. 필드
    private String title; // 입력받은 제목 필드
    private String content;// 입력받은 내용 필드
}
