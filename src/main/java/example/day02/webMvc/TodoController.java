package example.day02.webMvc;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// *스프링에게 해당 클래스가 컨트롤 이라는걸 알려야한다.
@RestController //스프링부트 시작 할떄// 스프링 컨테이너[스프링관리하는메모리공간] 에 빈(객체) 등록
public class TodoController {
    private TodoDao todoDao = new TodoDao();
    // 2. 할일등록 함수
    @PostMapping("/todo/post.do")
    public boolean doPost( TodoDto todoDto ){return todoDao.doPost( todoDto );}
    // 3. 할일목록출력 함수
    @GetMapping("/todo/get.do") // http://localhost/todo/get.do
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }
    // 4.할일 상태 수정 함수
    @PutMapping("/todo/put.do")
    public boolean doPut( TodoDto todoDto){
        return todoDao.doPut( todoDto );
    }
    // 5.할일 삭제 함수
    @DeleteMapping("/todo/delete.do")
    public boolean doDelete( int id ){return todoDao.doDelete( id );}
}


