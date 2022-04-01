package vip.huhailong.foodmenu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: food-menu
 * @description:
 **/
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String openId;
    private String createTime;
    private String nickName;

}
