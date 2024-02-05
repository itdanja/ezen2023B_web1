package example.day01.webMvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController // 해당 클래스를 스프링mvc 환경에 등록
public class TodoController {

    private TodoDao todoDao = new TodoDao();
    // 2. 할일등록 함수
    @GetMapping("/todo/post.do")
    public boolean doPost( TodoDto todoDto ){
        return todoDao.doPost( todoDto );
    }
    // JS[외부] 가 JAVA에게 요청하는 경로
    // http://localhost:80/todo/post.do?content=안녕하세요&deadline=2024-02-05

    // 3. 할일목록출력 함수
    @GetMapping("/todo/get.do")
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }
    // JS[외부] 가 JAVA에게 요청하는 경로
    // http://localhost:80/todo/get.do

    // 제출 : cmd -> ipconfig
    // http://192.168.17.96:80
}








