package vip.huhailong.foodmenu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @program: food-menu
 * @description: 菜谱详情
 **/
@Data
public class FoodDetail {
    private String workmanship;     //工艺
    private String flavor;          //口味
    private String time;            //时间
    private String difficultyLevel; //难度
    private List<String> mainMaterialList;  //主料
    private List<String> accessoriesList;   //辅料
    private List<FoodStep> foodStepList;    //步骤列表
    private List<String> showFoodImageList; //成品图展示
    private List<String> cookSkill;         //烹饪技巧
    @TableField(exist = false)
    private Boolean isCollection;   //是否被收藏
}
