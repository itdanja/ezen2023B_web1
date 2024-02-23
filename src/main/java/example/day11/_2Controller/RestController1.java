package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RestController1 {

    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수           http://localhost/day11/black/value
        // 2. 쿼리스트링 변수         http://localhost/day11/balck?key=value

    // 1. Get   : GET http://localhost/day11/black
    @RequestMapping( value = "/day11/black" , method = RequestMethod.GET)
    public void getBlack(HttpServletRequest req , HttpServletResponse resp ) throws IOException  {
        System.out.println("RestController1.getBlack");
        // 요청   http://localhost/day11/black?sendMsg=안녕[컨트롤]
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().print("안녕[클라이언트]");
    } // end
    // 2. Post : POST http://localhost/day11/black
    @RequestMapping( value = "/day11/black" , method = RequestMethod.POST )
    public void postBlack( HttpServletRequest req , HttpServletResponse resp ) throws IOException {
        System.out.println("RestController1.postBlack");
        // -------- 요청/응답 -----------
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        resp.setContentType("text/html"); resp.getWriter().print("안녕[클라이언트]");
        // ----------------------------
    }
    // 3. Put : PUT http://localhost/day11/black
    @RequestMapping( value = "/day11/black" , method = RequestMethod.PUT )
    public void putBlack( HttpServletRequest req , HttpServletResponse resp ) throws IOException {
        System.out.println("RestController1.putBlack");
        // -------- 요청/응답 -----------
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        resp.setContentType("text/html"); resp.getWriter().print("안녕[클라이언트]");
        // ----------------------------
    }
    // 4. delete : DELETE http://localhost/day11/black
    @RequestMapping( value = "/day11/black" , method = RequestMethod.DELETE )
    public void deleteBlack( HttpServletRequest req , HttpServletResponse resp ) throws IOException {
        System.out.println("RestController1.deleteBlack");
        // -------- 요청/응답 -----------
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        resp.setContentType("text/html"); resp.getWriter().print("안녕[클라이언트]");
        // ----------------------------
    }

}
