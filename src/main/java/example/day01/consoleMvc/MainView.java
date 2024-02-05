package example.day01.consoleMvc;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView {

    // 필드
    private Scanner scanner = new Scanner(System.in);
    private TodoController todoController = new TodoController();

    // 1. 메인페이지
    public void home(){
        while (true){
            doGet(); // 할일목록출력
            System.out.print("1.할일등록 : ");
            int ch = scanner.nextInt();
            if( ch == 1 ){ doPost(); } // 할일등록
        }
    } // m e
    // 2. 할일등록 함수
    public void doPost(){
        // 1. 입력받기
        System.out.print("할일 내용 : ");
        String content = scanner.next();
        System.out.print("마감일[yyyy-mm-dd] : ");
        String deadline = scanner.next();
        // 2. 객체화
        TodoDto todoDto = new TodoDto();
        todoDto.setContent( content );
        todoDto.setDeadline( deadline );
        // 3. 컨트롤에게 요청 응답 받기
        boolean result = todoController.doPost( todoDto );
        // 4. 응답결과 출력하기
        System.out.println( result );
    } // me
    // 3. 할일목록출력 함수
    public void doGet(){
        // 1. 입력받기 - 전체 출력이라서 조건이 없음x    // 2. 객체화x
        // 3. 컨트롤에게 요청 응답 받기
        ArrayList<TodoDto> result = todoController.doGet();
        // 4. 응답결과 출력하기
        for( int i = 0 ; i<result.size(); i++ ){
            TodoDto todoDto = result.get(i);  // i번째 dto를 호출
            System.out.printf(" %-2s %-10s %-5s %-30s \n",
                    todoDto.getId() ,   todoDto.getDeadline() ,
                    todoDto.isState() ,   todoDto.getContent() );
        }
    } // me



}
