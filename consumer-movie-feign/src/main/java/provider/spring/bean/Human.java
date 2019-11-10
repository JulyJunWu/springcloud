package provider.spring.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author JunWu
 */
@Data
public class Human {

    private List list;
    private Map map;

    public Human(Map map) {
        this.map = map;
    }

    public Human(List list) {
        this.list = list;
    }
}
