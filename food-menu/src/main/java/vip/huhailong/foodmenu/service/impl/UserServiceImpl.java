package vip.huhailong.foodmenu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.entity.User;
import vip.huhailong.foodmenu.mapper.UserMapper;
import vip.huhailong.foodmenu.service.IUserService;
import vip.huhailong.foodmenu.util.RsUtil;

/**
 * @program: food-menu
 * @description: 用户接口实现类
 **/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Value(value = "${wx.appId}")
    private String appId;
    @Value(value = "${wx.secretId}")
    private String secretId;
    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    //获取openid的接口
    @Override
    public Rs getOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secretId+"&js_code="+code+"&grant_type=authorization_code";
        log.info("requestUrl:{}",requestUrl);
        String forObject = restTemplate.getForObject(requestUrl, String.class);
        JSONObject result = JSONObject.parseObject(forObject);
        return RsUtil.success("获取OpenId成功",result);
    }

    @Override
    public User getUserByOpenId(String openId) {
        return getOne(new QueryWrapper<User>().eq("open_id", openId));
    }
}
