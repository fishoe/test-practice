package fun.fishoe.testpractice.SimpleApi.Data;

import java.beans.ConstructorProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleData {
    private int id;
    private String name;
    private int value;

    @Builder
    @ConstructorProperties({"id","name","value"})
    public SimpleData(int id,String name,int value){
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
