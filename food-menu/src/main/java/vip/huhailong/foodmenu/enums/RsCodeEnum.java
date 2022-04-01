package vip.huhailong.foodmenu.enums;

/**
 * @program: food-menu
 * @description: 枚举的包，用redis来
 **/
public enum RsCodeEnum {

    SUCCESS(200),
    ERROR(500);

    private final Integer code;

    RsCodeEnum(Integer code){
        this.code = code;
    }

    public Integer code(){
        return this.code;
    }
}
