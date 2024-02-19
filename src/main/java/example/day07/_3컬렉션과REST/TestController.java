package example.day07._3컬렉션과REST;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class TestController {

    public static Map<String , Integer > map = new HashMap<>();
    public static Stack<String > stack = new Stack<>();

    @GetMapping("/index")
    public String index(Model model){
        map.put("강호동" , 10);
        map.put("유재석" , 30);
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        model.addAttribute( "map" , entrySet );

        return "testindex";
    }

    @GetMapping("/index2")
    public String index2(Model model){
        stack.push("휴지1");
        stack.push("휴지2");
        model.addAttribute( "stack" , stack );
        return "testindex";
    }

}






