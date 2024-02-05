package example.day01.consoleMvc;

import java.util.ArrayList;
import java.util.List;

public class TodoController {

    private TodoDao todoDao = new TodoDao();

    // 2. 할일등록 함수
    public boolean doPost( TodoDto todoDto ){
        return todoDao.doPost( todoDto );
    }
    // 3. 할일목록출력 함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

}








