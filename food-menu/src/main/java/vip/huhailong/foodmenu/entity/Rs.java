package vip.huhailong.foodmenu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @program: food-menu
 * @description: 响应实体类
 **/
@Data
@Builder
public class Rs {
    private Boolean status;
    private Integer code;
    private String message;
    private Object data;
}
