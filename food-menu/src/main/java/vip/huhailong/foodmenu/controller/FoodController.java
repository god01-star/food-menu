package vip.huhailong.foodmenu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.huhailong.foodmenu.entity.CollectionFood;
import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.service.ICollectionFoodService;
import vip.huhailong.foodmenu.service.IFoodService;

/**
 * @program: food-menu
 * @description: 菜谱接口
 **/
@RestController
@RequestMapping("/food")
public class FoodController {

    private final IFoodService foodService;


    @Autowired
    public FoodController(IFoodService foodService){
        this.foodService = foodService;
    }

    @GetMapping("/getFoodList")
    public Rs getFoodList(Integer page){
        return foodService.getFoodList(page);
    }

    @GetMapping("/getFoodDetail")
    public Rs getFoodDetail(String url){

        return foodService.getFoodDetail(url);
    }

    @GetMapping("/searchFood")
    public Rs searchFoodList(String keyword){
        return foodService.getSearchList(keyword);
    }
}
