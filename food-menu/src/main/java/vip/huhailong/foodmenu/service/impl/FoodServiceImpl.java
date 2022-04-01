package vip.huhailong.foodmenu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.huhailong.foodmenu.entity.CollectionFood;
import vip.huhailong.foodmenu.entity.FoodDetail;
import vip.huhailong.foodmenu.entity.FoodItem;
import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.service.ICollectionFoodService;
import vip.huhailong.foodmenu.service.IFoodService;
import vip.huhailong.foodmenu.util.JsonUtil;
import vip.huhailong.foodmenu.util.JsoupUtil;
import vip.huhailong.foodmenu.util.RedisUtil;
import vip.huhailong.foodmenu.util.RsUtil;

import java.util.List;

/**
 * @program: food-menu
 * @description: 菜谱接口实现类
 **/
@Slf4j
@Service
public class FoodServiceImpl implements IFoodService {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ICollectionFoodService collectionFoodService;

    @Override
    public Rs getFoodList(Integer page) {
        String key = "food:menu:"+page;//用页数做key
        Boolean aBoolean = redisUtil.checkKey(key);
        //通过缓存获取列表，，打开频次比较多，所以用redis,
        if(aBoolean!=null&&aBoolean){
            log.info("通过redis加载数据，key:{}",key);
            String foodListJson = redisUtil.get(key);
            List<FoodItem> foodItems = JsonUtil.castList(foodListJson, FoodItem.class);
            return RsUtil.success("获取列表成功",foodItems);
        }
        //通过爬虫获取列表
        List<FoodItem> foodList = JsoupUtil.getFoodList(page);
        redisUtil.add(key, JSONObject.toJSONString(foodList));
        return RsUtil.success("获取列表成功",foodList);
    }

    @Override
    public Rs getFoodDetail(String url) {
        FoodDetail foodDetail = JsoupUtil.getFoodDetail(url);
        int detail_url = collectionFoodService.count(new QueryWrapper<CollectionFood>().eq("detail_url", url));
        foodDetail.setIsCollection(detail_url > 0);
        return RsUtil.success("查询成功",foodDetail);
    }

    @Override
    public Rs getSearchList(String keyword) {
        List<FoodItem> searchList = JsoupUtil.getSearchList(keyword);
        return RsUtil.success("查询成功",searchList);
    }
}
