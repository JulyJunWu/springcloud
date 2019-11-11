package provider.spring.bean.circle;

import lombok.Getter;
import lombok.Setter;

/**
 * @author JunWu
 * 循环依赖B
 */
@Getter
@Setter
public class CircleB {

    private CircleC circleC;

}
