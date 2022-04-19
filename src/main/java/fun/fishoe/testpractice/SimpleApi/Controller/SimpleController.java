package fun.fishoe.testpractice.SimpleApi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.fishoe.testpractice.SimpleApi.Data.ResponseSimpleData;
import fun.fishoe.testpractice.SimpleApi.Data.SimpleData;
import fun.fishoe.testpractice.SimpleApi.Service.SimpleService;

@RestController
@RequestMapping(path="/api")
public class SimpleController {

    private SimpleService service;

    public SimpleController(SimpleService service){
        this.service = service;
    }

    @GetMapping(path="/one")
    public SimpleData get_one_obj(){
        return service.get_one();
    }

    @GetMapping(path="/all")
    public ResponseSimpleData get_all_obj(){
        return service.get_all();
    }
}
