package vip.huhailong.foodmenu.util;

import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.enums.RsCodeEnum;

/**
 * @program: food-menu
 * @description: 响应工具类，错误的类传一个枚举
 **/
public class RsUtil {

    /**
     * 请求成功
     * @param message
     * @return
     */
    public static Rs success(String message){
        return Rs.builder().status(Boolean.TRUE).code(RsCodeEnum.SUCCESS.code()).message(message).build();
    }

    public static Rs success(String message, Object data){
        return Rs.builder().status(Boolean.TRUE).code(RsCodeEnum.SUCCESS.code()).message(message).data(data).build();
    }

    public static Rs error(String message, RsCodeEnum rsCodeEnum){
        return Rs.builder().status(Boolean.FALSE).code(rsCodeEnum.code()).message(message).build();
    }

    public static Rs error(String message, RsCodeEnum rsCodeEnum, Object data){
        return Rs.builder().status(Boolean.FALSE).code(rsCodeEnum.code()).message(message).data(data).build();
    }
}
