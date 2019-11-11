package provider.spring.bean.circle;

import lombok.Getter;
import lombok.Setter;

/**
 * @author JunWu
 * 循环依赖C
 */
@Getter
@Setter
public class CircleC {

    private CircleA circleA;

}
