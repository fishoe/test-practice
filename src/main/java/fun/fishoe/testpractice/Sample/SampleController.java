package fun.fishoe.testpractice.Sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/")
public class SampleController {

    @GetMapping
    public String SampleMain(){
        return "Hello, world";
    }
}
