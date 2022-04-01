package vip.huhailong.foodmenu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: food-menu
 * @description: 菜谱项
 **/
@Data
@AllArgsConstructor
public class FoodItem {
    private String foodName;
    private String detailUrl;
    private String foodImage;
}
