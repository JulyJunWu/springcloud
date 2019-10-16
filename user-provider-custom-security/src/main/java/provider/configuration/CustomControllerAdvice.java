package provider.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/16 0016 14:06
 */
@ControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handlerException(Exception e){

        log.error(e.getMessage());

        return "页面错误,请稍后尝试!";
    }

}
