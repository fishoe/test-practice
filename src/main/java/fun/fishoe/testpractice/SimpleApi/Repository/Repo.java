package fun.fishoe.testpractice.SimpleApi.Repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import fun.fishoe.testpractice.SimpleApi.Data.SimpleData;

@Component
public class Repo {
    private List<SimpleData> database = Arrays.asList(
        new SimpleData(1, "kim", 100),
        new SimpleData(2, "ferry", 10),
        new SimpleData(3, "ahn", 1024),
        new SimpleData(4, "dowman", 545),
        new SimpleData(5, "nams", 232),
        new SimpleData(6, "chilly", 23),
        new SimpleData(7, "fishoe", 211),
        new SimpleData(8, "pebble", 95),
        new SimpleData(9, "tokie", 2048),
        new SimpleData(10, "helm", 15),
        new SimpleData(11, "byedle", 22),
        new SimpleData(13, "noman", 33),
        new SimpleData(14, "almar", 1),
        new SimpleData(15, "busik", 10000000),
        new SimpleData(16, "kisuman", 99),
        new SimpleData(17, "comand", 1234),
        new SimpleData(18, "dubie", 27),
        new SimpleData(19, "chainsaw", 2)
    );

    final static private int n_item_per_page = 5;

    public SimpleData get_one(){
        return database.get(6);
    }

    public List<SimpleData> get_all(){
        return database;
    }

    public List<SimpleData> get_page(int page_num){
        int skip = n_item_per_page * (page_num -1);
        int limit = n_item_per_page * (page_num);
        
        return database.subList(skip, limit);
    }
}
