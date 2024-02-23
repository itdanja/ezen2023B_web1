package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestController2 { // HttpServletResponse 없애기

    // 1. Get   : GET http://localhost/day11/white
    @RequestMapping( value = "/day11/white" , method = RequestMethod.GET)
    @ResponseBody   // 응답 contentType : application/json
    public String getWhite(HttpServletRequest req  ) throws IOException  {
        // 요청
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        // 응답
        return "안녕[클라이언트]";
    } // end
    
    // 2. Post : POST http://localhost/day11/white
    @RequestMapping( value = "/day11/white" , method = RequestMethod.POST )
    @ResponseBody   // 응답 contentType   컬렉션프레임워크 또는 배열  -->  	application/json;
    public Map< String , String > postWhite(HttpServletRequest req  ) throws IOException {
        // -------- 요청/응답 -----------
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        // ----------------------------
//        String[] strArray = new String[2];
//        strArray[0] = "안녕";  strArray[1] = "클라이언트";
        //
//        List<String> strArray = new ArrayList<>();
//        strArray.add("안녕"); strArray.add("클라이언트");
        //
        Map< String , String > strArray = new HashMap<>();
        strArray.put("안녕","클라이언트");
        return strArray;
    }
    // 3. Put : PUT http://localhost/day11/white
    @RequestMapping( value = "/day11/white" , method = RequestMethod.PUT )
    @ResponseBody   // 응답 contentType   int ---> application/json;
    public int putWhite( HttpServletRequest req  ) throws IOException {
        // -------- 요청/응답 -----------
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        return 10;
    }
    // 4. delete : DELETE http://localhost/day11/white
    @RequestMapping( value = "/day11/white" , method = RequestMethod.DELETE )
    @ResponseBody   // 응답 contentType   int ---> application/json;
    public boolean deleteWhite( HttpServletRequest req  ) throws IOException {
        // -------- 요청/응답 -----------
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        // ----------------------------
        return true;
    }

}
