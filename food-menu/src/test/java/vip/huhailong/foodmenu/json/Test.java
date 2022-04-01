package vip.huhailong.foodmenu.json;

import com.alibaba.fastjson.JSONObject;
import vip.huhailong.foodmenu.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: food-menu
 * @description:
 **/
public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");
        String s = JSONObject.toJSONString(testList);
        List<String> strings = JsonUtil.castList(s, String.class);
        System.out.println(strings);
    }
}
