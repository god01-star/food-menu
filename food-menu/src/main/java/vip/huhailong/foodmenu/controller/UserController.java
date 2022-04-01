package vip.huhailong.foodmenu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.huhailong.foodmenu.entity.CollectionFood;
import vip.huhailong.foodmenu.entity.Rs;
import vip.huhailong.foodmenu.entity.User;
import vip.huhailong.foodmenu.enums.RsCodeEnum;
import vip.huhailong.foodmenu.service.ICollectionFoodService;
import vip.huhailong.foodmenu.service.IUserService;
import vip.huhailong.foodmenu.util.RsUtil;
import vip.huhailong.foodmenu.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: food-menu
 * @description: 用户接口
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    private final ICollectionFoodService collectionFoodService;

    @Autowired
    public UserController(IUserService userService, ICollectionFoodService collectionFoodService){
        this.userService = userService;
        this.collectionFoodService = collectionFoodService;
    }

    //增加用户接口
    @GetMapping("/addUser")
    public Rs addUser(HttpServletRequest request){
        String openId = request.getHeader("openId");
        int open_id = userService.count(new QueryWrapper<User>().eq("open_id", openId));
        if(open_id==0){
            User user = new User();
            user.setOpenId(openId);
            user.setCreateTime(TimeUtil.getDateTime());
            userService.save(user);
            return RsUtil.success("创建用户成功");
        }
        return RsUtil.success("您已经是注册用户");
    }

    @GetMapping("/getOpenId")
    public Rs getOpenId(String code){
        return userService.getOpenId(code);
    }

    @PostMapping("/addCollection")
    public Rs collectionFood(@RequestBody CollectionFood food, HttpServletRequest request){
        String detailUrl = food.getDetailUrl();
        int detail_url = collectionFoodService.count(new QueryWrapper<CollectionFood>().eq("detail_url", detailUrl));
        if(detail_url>0){
            return RsUtil.error("该菜谱已收藏过了",RsCodeEnum.ERROR);
        }
        food.setCreateTime(TimeUtil.getDateTime());
        String openId = request.getHeader("openId");
        if(openId==null||openId.isEmpty()||food.getFoodName()==null){
            return RsUtil.error("收藏失败", RsCodeEnum.ERROR);
        }
        return collectionFoodService.addCollection(food,openId);
    }

    @GetMapping("/getCollection")
    public Rs getCollection(String keyword){
        QueryWrapper<CollectionFood> queryWrapper = new QueryWrapper<>();
        if(keyword!=null&&!keyword.isEmpty()){
            queryWrapper.like("food_name",keyword);
        }
        List<CollectionFood> list = collectionFoodService.list(queryWrapper);
        return RsUtil.success("查询成功",list);
    }

    @GetMapping("/delCollection")
    public Rs delCollection(Integer id){
        collectionFoodService.removeById(id);
        return RsUtil.success("删除成功");
    }

    @PostMapping("/test")
    public Rs test(@RequestBody CollectionFood food){
        System.out.println(food.getFoodName());
        return RsUtil.success("success");
    }
}
