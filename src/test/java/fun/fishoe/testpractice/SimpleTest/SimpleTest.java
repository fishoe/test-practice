package fun.fishoe.testpractice.SimpleTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fun.fishoe.testpractice.SimpleApi.Data.ResponseSimpleData;
import fun.fishoe.testpractice.SimpleApi.Data.SimpleData;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static fun.fishoe.testpractice.DtoTester.CustomMockMvcResultMatcher.JsonResponseType;;

@SpringBootTest
@AutoConfigureMockMvc
public class SimpleTest {

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


    @Autowired
    private MockMvc mockMvc;

    @Test
    void SimpleDataTest() throws Exception{

        // SimpleData obj = new SimpleData(7, "kim", 123);
        SimpleData sample = new SimpleData(7, "fishoe", 211);

        mockMvc.perform(
            get("/api/one")
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(JsonResponseType(SimpleData.class).isOK())
        .andExpect(JsonResponseType(SimpleData.class).checkWith(sample));
        // .andExpect(JsonResponseType(SimpleData.class).checkWith(obj));
        // .andExpect(JsonResponseType(ResponseSimpleData.class).isOK());
    }
    

    @Test
    void SimpleDataListTest() throws Exception{

        Object sample = new ResponseSimpleData(18, database);
        ResponseSimpleData sample_2 = new ResponseSimpleData(18, database);    

        mockMvc.perform(
            get("/api/all")
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(JsonResponseType(ResponseSimpleData.class).isOK())
        .andExpect(JsonResponseType(ResponseSimpleData.class).checkWith(sample));
        // .andExpect(JsonResponseType(ResponseSimpleData.class).checkWith(obj));
        // .andExpect(JsonResponseType(ResponseSimpleData.class).isOK());
    }

}
