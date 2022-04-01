package vip.huhailong.foodmenu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.util.RsUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: food-menu
 * @description: 测试接口
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/access")
    public Rs test(HttpServletRequest request){
        String openId = request.getHeader("openId");
        log.info("openId:{}",openId);
        return RsUtil.success("请求成功","access successful");
    }
}
