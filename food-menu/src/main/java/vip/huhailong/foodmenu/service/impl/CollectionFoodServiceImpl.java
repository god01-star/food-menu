package vip.huhailong.foodmenu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.huhailong.foodmenu.entity.CollectionFood;
import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.entity.User;
import vip.huhailong.foodmenu.mapper.CollectionFoodMapper;
import vip.huhailong.foodmenu.service.ICollectionFoodService;
import vip.huhailong.foodmenu.service.IUserService;
import vip.huhailong.foodmenu.util.RsUtil;

import javax.annotation.Resource;

/**
 * @program: food-menu
 * @description:
 **/
@Service
public class CollectionFoodServiceImpl extends ServiceImpl<CollectionFoodMapper, CollectionFood> implements ICollectionFoodService {

    @Resource
    CollectionFoodMapper collectionFoodMapper;

    @Autowired
    IUserService userService;

    @Override
    public Rs addCollection(CollectionFood food, String openId) {
        save(food);
        User userByOpenId = userService.getUserByOpenId(openId);
        collectionFoodMapper.addUserFoodRelation(food.getId(),userByOpenId.getId());
        return RsUtil.success("添加成功");
    }

}
