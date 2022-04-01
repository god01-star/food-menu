package vip.huhailong.foodmenu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import vip.huhailong.foodmenu.entity.CollectionFood;

/**
 * @program: food-menu
 * @description:
 **/
public interface CollectionFoodMapper extends BaseMapper<CollectionFood> {

    int addUserFoodRelation(@Param("foodId") Integer foodId, @Param("userId") Integer userId);
}
