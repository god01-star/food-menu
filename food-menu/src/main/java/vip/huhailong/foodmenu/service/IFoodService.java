package vip.huhailong.foodmenu.service;

import vip.huhailong.foodmenu.entity.Rs;

/**
 * @program: food-menu
 * @description: 菜谱接口
 **/
public interface IFoodService {

    Rs getFoodList(Integer page);

    Rs getFoodDetail(String url);

    Rs getSearchList(String keyword);
}
