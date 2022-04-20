package fun.fishoe.testpractice.DtoTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.ClassUtils;

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
            boolean compared = Compare(t,target);
            System.out.println(compared);
            assertTrue(compared);
        };
    }

    private boolean Compare(Object source, Object target) throws Exception{
        if (!is_same_type(source, target)) {
            return false;
        }else if(
            source.getClass().isPrimitive() || 
            ClassUtils.isPrimitiveOrWrapper(source.getClass())|| 
            source.getClass() == String.class)
        {
            return ComparePrimitiveAndString(source,target);
        }else if(source.getClass().isArray()){
            return CompareArray(source, target);
        }else if(Collection.class.isAssignableFrom(source.getClass())){
            return CompareIterable(source, target);
        }else {
            return CompareJsonObject(source,target);
        }
    }

    private boolean ComparePrimitiveAndString(Object source, Object target) throws Exception{
        if(source.equals(target)) return true;
        else return false;
    }

    private boolean CompareArray(Object source, Object target) throws Exception{

        int source_length = Array.getLength(source);
        int target_length = Array.getLength(target);

        if(source_length != target_length ) return false;

        for(int i = 0; i< source_length; i++){
            Object elem_source_arr = Array.get(source,i);
            Object elem_target_arr = Array.get(target,i);
            if(!Compare(elem_source_arr,elem_target_arr)) return false;
        }
        return true;
    }
    
    private boolean CompareIterable(Object source, Object target) throws Exception {        

        Class[] cArg = {int.class};

        Method get_size = Collection.class.getMethod("size");
        Method get_elem = List.class.getMethod("get", cArg );
        
        int length_source = (int) get_size.invoke(source);
        int length_target = (int) get_size.invoke(target);

        if (length_source != length_target) return false;
        
        for(int i = 0;i<length_source; i++){
            Object source_elem = get_elem.invoke(source, i);
            Object target_elem = get_elem.invoke(target, i);
            if (!Compare(source_elem,target_elem)) return false;
        }

        return true;
    }

    private boolean CompareJsonObject(Object source, Object target) throws Exception {
        for(Field f : target.getClass().getDeclaredFields()){
            if (Modifier.isStatic(f.getModifiers())) continue;

            Object mem_source = get_object(f, source);
            Object mem_target = get_object(f, target);

            if (!Compare(mem_target,mem_source)) return false;
        }
        return true;
    }

    private T convert(MvcResult result) throws Exception {
        return new ObjectMapper().readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), cls);
    }

    private static boolean is_same_type(Object source, Object target){
        if (source.getClass() == target.getClass()) 
            return true;
        else if (
            Collection.class.isAssignableFrom(source.getClass()) ||
            Collection.class.isAssignableFrom(target.getClass())) 
            return true;
        else 
            return false;
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
