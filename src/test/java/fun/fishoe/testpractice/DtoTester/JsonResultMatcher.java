package fun.fishoe.testpractice.DtoTester;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;


public class JsonResultMatcher<T> {
    private Class<T> cls;

    public JsonResultMatcher(Class<T> cls){
        this.cls = cls;
    }

    public ResultMatcher isOK(){
        return result -> {
            T t = this.convert(result);
            Method getter = cls.getDeclaredMethod("getLength");
            int a = (int) getter.invoke(t);
            for(Field f : cls.getDeclaredFields()){
                if(f.getType().isArray() ){
                    System.out.println(f.getName());
                    System.out.println(f.getType().toString());
                }else if(Collection.class.isAssignableFrom(f.getType())){
                    
                }
            }
        };
    }

    public boolean Compare(Field[] sources, Field[] targets) throws Exception {
        for(Field f : sources){
            if(f.getType().isPrimitive()){
                // 기본형
            }else if(f.getType().isArray()){
                // 배열
            }else if(Collection.class.isAssignableFrom(f.getType())){
                // 이터러블
            }else {
                // 오브젝트
            }
        }
        return false;
    }

    public ResultMatcher equalTo(){
        return result -> {
            T t = this.convert(result);
            
        };
    }

    public T convert(MvcResult result) throws Exception {
        return new ObjectMapper().readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), cls);
    }
}
