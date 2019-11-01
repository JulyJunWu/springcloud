package provider.validata;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author JunWu
 *  验证注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomValidate.class)
public @interface ValidateString {
    /**
     * 错误提示 , 可以从配置读取
     * @return
     */
    String message() default "验证不通过!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
