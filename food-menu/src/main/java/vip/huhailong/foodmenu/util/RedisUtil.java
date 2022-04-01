package vip.huhailong.foodmenu.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: food-menu
 * @description: 存储json字符串
 **/
@Component
public class RedisUtil {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 增加缓存
     * @param key
     * @param jsonStr
     */
    public void add(String key, String jsonStr){
        redisTemplate.opsForValue().set(key,jsonStr);
        redisTemplate.expire(key,6, TimeUnit.HOURS);//过期时间
    }

    /**
     * 检查key是否存在
     * @param key
     */
    public Boolean checkKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

}
