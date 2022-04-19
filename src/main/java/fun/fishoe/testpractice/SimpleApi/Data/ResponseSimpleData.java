package fun.fishoe.testpractice.SimpleApi.Data;

import java.beans.ConstructorProperties;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSimpleData {
    private int length;
    private List<SimpleData> documents;

    @Builder
    @ConstructorProperties({"length","documents"})
    public ResponseSimpleData(int length, List<SimpleData> documents){
        this.length = length;
        this.documents = documents;
    }
}
