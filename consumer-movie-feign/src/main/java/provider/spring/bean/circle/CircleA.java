package provider.spring.bean.circle;

import lombok.Getter;
import lombok.Setter;

/**
 * @author JunWu
 * 循环依赖bean A
 */
@Getter
@Setter
public class CircleA {

    private CircleB circleB;

}
