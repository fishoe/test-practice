package fun.fishoe.testpractice.SimpleApi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.fishoe.testpractice.SimpleApi.Data.ResponseSimpleData;
import fun.fishoe.testpractice.SimpleApi.Data.SimpleData;
import fun.fishoe.testpractice.SimpleApi.Repository.Repo;

@Service
public class SimpleService {

    @Autowired
    private Repo simple_repo; 

    public SimpleData get_one(){
        return simple_repo.get_one();
    }

    public ResponseSimpleData get_all(){
        List<SimpleData> datas = simple_repo.get_all();
        return new ResponseSimpleData(datas.size(),datas);
    }
}
