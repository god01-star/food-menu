package vip.huhailong.foodmenu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: food-menu
 * @description: 菜谱步骤
 **/
@Data
@AllArgsConstructor
public class FoodStep {
    private Integer step;               //步骤
    private String stepDescribe;        //步骤描述
    private String stepDescribeImageUrl;//步骤描述对应图片URL
}
