package fun.fishoe.testpractice.DtoTester;

public class CustomMockMvcResultMatcher {
    static public <T> JsonResultMatcher<T> JsonResponseType(Class<T> cls){
        return new JsonResultMatcher<>(cls);
    }
}
