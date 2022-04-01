package vip.huhailong.foodmenu.util;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: food-menu
 * @description:
 **/
public class JsonUtil {

    public static <T> List<T> castList(String jsonString, Class<T> clazz) {
        return JSONArray.parseArray(jsonString, clazz);
    }
}
