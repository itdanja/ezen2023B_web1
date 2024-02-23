package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController //  @Controller +  @ResponseBody
@RequestMapping( value = "/day11")
public class RestController3 {
    // 1. Get   : GET http://localhost/day11/red
    // @RequestMapping( value = "/day11/red" , method = RequestMethod.GET)
    @GetMapping(value = "/red")
    public String getRed( HttpServletRequest req  )  {
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        return "안녕[클라이언트]";
    } // end

    // 2. Post : POST http://localhost/day11/red
    @PostMapping("/red")
    public Map< String , String > postRed(HttpServletRequest req  ) {
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        Map< String , String > strArray = new HashMap<>();
        strArray.put("안녕","클라이언트");
        return strArray;
    }
    // 3. Put : PUT http://localhost/day11/red
    @PutMapping("/red")
    public int putRed( HttpServletRequest req  ) {
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        return 10;
    }
    // 4. delete : DELETE http://localhost/day11/white
    @DeleteMapping("/red")
    public boolean deleteRed( HttpServletRequest req  ) {
        String sendMsg = req.getParameter("sendMsg");    System.out.println("sendMsg = " + sendMsg);
        return true;
    }
}
