package provider.spring.bean;

import java.util.List;
import java.util.Map;

/**
 * @author JunWu
 */
public class Human {

    private List list;
    private Map map;

    public Human(Map map) {
        this.map = map;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
