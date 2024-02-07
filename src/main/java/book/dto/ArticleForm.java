package book.dto;

// 입력 폼 전용 DTO
    // 관례적으로 모든 객체 필드는 직접 접근 권장하지 않는다. private , 안정성보장 , 캡슐화 특징 , setter,getter,생성자
public class ArticleForm {
    // 1. 필드
    private String title; // 입력받은 제목 필드
    private String content;// 입력받은 내용 필드
    // 2. 생성자
        // 2. 모든 필드 생성자
    public ArticleForm( String title, String content) {
        this.title = title;
        this.content = content;
    } //
    // 3. 메소드
        // 1. toString() : 객체내 필드 값 호출 함수
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
        // 2. setter/getter : 필드가 private 라서.
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
