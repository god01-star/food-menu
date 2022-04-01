package vip.huhailong.foodmenu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.enums.RsCodeEnum;
import vip.huhailong.foodmenu.util.RsUtil;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    /**
     * @description 全局异常处理方法
     * @param e 报错实例
     * @return JSONObject
     */
    @ExceptionHandler
    public Rs exceptionHandler(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return RsUtil.error(e.getMessage(), RsCodeEnum.ERROR);
    }
}
