package vip.huhailong.foodmenu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.entity.User;

/**
 * @program: food-menu
 * @description:
 **/
public interface IUserService extends IService<User> {

    User getUserByOpenId(String openId);

    Rs getOpenId(String code);
}
