package fun.fishoe.testpractice.DtoTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import fun.fishoe.testpractice.SimpleApi.Data.SimpleData;


public class JsonResultMatcher<T> {
    
    private Class<T> cls;
    
    public static void main(){
    }

    public JsonResultMatcher(Class<T> cls){
        this.cls = cls;
    }

    public ResultMatcher isOK(){
        return result -> {
            Object t = this.convert(result);
        };
    }

    public ResultMatcher checkWith(Object target) {
        return result -> {
            Object t = this.convert(result);
            boolean compared = CompareJsonObject(t,target);
            // System.out.println(compared);
            assertTrue(compared);
        };
    }

    private boolean Compare(Field f, Object source, Object target) throws Exception{
        if(f.getType().isPrimitive() || f.getType() == String.class){
            return ComparePrimitiveAndString(f,source,target);
        }else if(f.getType().isArray()){
            return CompareArray(f, source, target);
        }else if(Collection.class.isAssignableFrom(f.getType())){
            // 이터러블
            System.out.println(f.canAccess(source));
            return false;
        }else {
            Object Obj_source = get_object(f, source);
            Object Obj_target = get_object(f, target);
            return CompareJsonObject(Obj_source,Obj_target);
        }
    }

    private boolean CompareJsonObject(Object source, Object target) throws Exception {
        if (source.getClass() != target.getClass()) return false;
        for(Field f : target.getClass().getDeclaredFields()){
            Object Obj_source = get_object(f, source);
            Object Obj_target = get_object(f, target);

            if (!Compare(f,source,target)) return false;
        }
        return true;
    }
    
    public ResultMatcher arrayTest() {
        // Method getter = cls.getDeclaredMethod("getValue");
        // int a = (int) getter.invoke(t);
        // System.out.println(a);
        // for(Field f : cls.getDeclaredFields()){
        //     if(f.getType().isArray() ){
        //         System.out.println(f.getName());
        //         System.out.println(f.getType().toString());
        //     }else if(Collection.class.isAssignableFrom(f.getType())){
                
        //     }
        // }
        return result -> {
            Object t = convert(result);
            Method getter = cls.getDeclaredMethod("getDocuments");
            Object ojb = getter.invoke(t);
            Method get_size = ojb.getClass().getDeclaredMethod("size");
            int s = (int)get_size.invoke(ojb);
            System.out.println(s);
            List<SimpleData> aaaa = new ArrayList<SimpleData>();
            System.out.println(aaaa.size());
            int[] z = {6,4,3};
            Object asd = z;
            // Field f = asd.getClass().getDeclaredField("length");
            // System.out.println(z.length);
            System.out.println(Array.getLength(asd));
            for(int i = 0 ; i < Array.getLength(asd);i++){
                System.out.println(i + ":" + Array.get(asd, i));
            }
        };
    }

    private boolean ComparePrimitiveAndString(Field f ,Object source, Object target) throws Exception{
        if(Modifier.isStatic(f.getModifiers())){
            return true;
        }else {
            Object Obj_source = get_object(f, source);
            Object Obj_target = get_object(f, target);
            if(Obj_source.equals(Obj_target)) return true;
            else return false;
        }
    }

    private boolean CompareArray(Field f, Object source, Object target) throws Exception{
        if(Modifier.isStatic(f.getModifiers())){
            return true;
        }else {
            Object Obj_source = get_object(f, source);
            Object Obj_target = get_object(f, target);

            int source_length = Array.getLength(Obj_source);
            int target_length = Array.getLength(Obj_target);

            if(source_length != target_length ) return false;

            for(int i = 0; i< source_length; i++){
                Object elem_source_arr = Array.get(Obj_source,i);
                Object elem_target_arr = Array.get(Obj_target,i);
                if(!Compare(elem_source_arr,elem_target_arr)) return false;
                // todo tomorrow
                // compareObject 만들고 compare 변환 도큐먼트 관련 정보 모아서 자동 생성기 만들어
            }
        }
        return true;
    }

    private T convert(MvcResult result) throws Exception {
        return new ObjectMapper().readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), cls);
    }

    private static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    private static Object get_object(Field f, Object obj) throws Exception{
        if(Modifier.isPublic(f.getModifiers())){
            return f.get(obj);
        }else{
            String field_name = f.getName();
            Method getter = obj.getClass().getDeclaredMethod("get"+upperCaseFirst(field_name));
            return getter.invoke(obj);
        }
    }
  
}
