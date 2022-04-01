package vip.huhailong.foodmenu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: food-menu
 * @description:
 **/
@Data
public class CollectionFood {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String foodName;
    private String detailUrl;
    private String foodImage;
    private String createTime;
}
