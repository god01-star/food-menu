package vip.huhailong.foodmenu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.huhailong.foodmenu.entity.CollectionFood;
import vip.huhailong.foodmenu.entity.Rs;

/**
 * @program: food-menu
 * @description:
 **/
public interface ICollectionFoodService extends IService<CollectionFood> {

    Rs addCollection(CollectionFood food, String openId);
}
