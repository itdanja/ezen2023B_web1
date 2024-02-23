package example.day11._2Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/day11")
public class RestController5 {
    // 1.
//    @PostMapping("/ajax5")
//    public String ajax5( int id , @RequestParam String content ){
//        System.out.println("RestController5.ajax5");
//        System.out.println("id = " + id + ", content = " + content);
//        return "응답5";
//    }
    // 2.
//    @PostMapping("/ajax5")
//    public String ajax5( @RequestParam Map<String,String> map){
//        System.out.println("RestController5.ajax5");
//        System.out.println("map = " + map);
//        return "응답5";
//    }
    @PostMapping("/ajax5")
    public String ajax5( AjaxDto ajaxDto ){
        System.out.println("RestController5.ajax5");
        System.out.println("ajaxDto = " + ajaxDto);
        return "응답5";
    }

}









