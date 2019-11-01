package provider.validata;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author  JunWu
 *  自定义验证类
 *
 *   <ValidateString,String> :
 *          表示为需要验证的注解
 *          表示接受验证的类型,如果注解放在不属于同一类型的字段上,则报错!
 */
@Slf4j
public class CustomValidate implements ConstraintValidator<ValidateString,String> {

    String key = "1";

    @Override
    public void initialize(ValidateString notBlank) {
        log.info("自定义验证注解初始化...");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(s) || s.contains(key)) {
            return false;
        }
        return true;
    }
}
