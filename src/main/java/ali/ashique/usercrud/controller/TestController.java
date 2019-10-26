package ali.ashique.usercrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.Map;

public class TestController {
    @GetMapping("/u/{name}")
    public String view(Map map, @PathVariable String name){
        map.put("name",name);
        return "test";
    }

    /**
     * Insert data at boot up
     */
    @PostConstruct
    public void init(){
        System.out.println("Hello Post construct");
    }
}
