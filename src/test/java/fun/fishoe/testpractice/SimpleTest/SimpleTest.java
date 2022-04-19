package fun.fishoe.testpractice.SimpleTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fun.fishoe.testpractice.SimpleApi.Data.SimpleData;
import fun.fishoe.testpractice.SimpleApi.Data.ResponseSimpleData;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static fun.fishoe.testpractice.DtoTester.CustomMockMvcResultMatcher.JsonResponseType;;

@SpringBootTest
@AutoConfigureMockMvc
public class SimpleTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void SimpleDataTest() throws Exception{
        // mockMvc.perform(
        //     get("/api/all")
        //     .accept(MediaType.APPLICATION_JSON)
        //     .characterEncoding("UTF-8")
        //     .contentType(MediaType.APPLICATION_JSON)
        // ).andExpect(status().isOk())
        // .andExpect(JsonResponseType(ResponseSimpleData.class).isOK());
    }
    
}
