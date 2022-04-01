package vip.huhailong.foodmenu.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import vip.huhailong.foodmenu.entity.FoodDetail;
import vip.huhailong.foodmenu.entity.FoodItem;
import vip.huhailong.foodmenu.entity.FoodStep;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class JsoupUtil {

    /**
     * 获取菜谱列表
     * @param page
     * @return
     */
    public static List<FoodItem> getFoodList(Integer page){
        String suffix = "/?order=-new";
        String url = "https://www.meishij.net/fenlei/jiachangcai";
        if(page==null||page==0){
            return getFoodList(url +suffix);//获取最新菜谱
        }
        suffix = "/p"+page;
        return getFoodList(url +suffix);
    }

    //爬取数据
    private static  List<FoodItem> getFoodList(String url){
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements select = document.select(".list_s2_content > .list_s2_item");
            for (Element element : select) {
                String foodName = element.select(".list_s2_item_info").text();
                String detailUrl = element.select(".imgw > a").attr("href");
                String style = element.select(".imgw > a").attr("style");
                FoodItem foodItem = new FoodItem(foodName, detailUrl, makeImageUrl(style));
                foodItems.add(foodItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    /**
     * 获取查询菜谱列表
     * @param keyword
     * @return
     */
    public static List<FoodItem> getSearchList(String keyword){
        String searchUrl = "https://so.meishi.cc/?q=";
        List<FoodItem> foodItemList = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.connect(searchUrl+keyword).get();
            Elements select = document.select(".search2015_cpitem_w > .search2015_cpitem");
            for (Element element : select) {
                String foodName = element.select("a").attr("title");
                String detailUrl = element.select("a").attr("href");
                String imageUrl = element.select("a > img").attr("src");
                FoodItem foodItem = new FoodItem(foodName, detailUrl, imageUrl);
                foodItemList.add(foodItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foodItemList;
    }

    /**
     * 获取菜谱详情
     * @param url
     * @return
     */
    public static FoodDetail getFoodDetail(String url){
        FoodDetail detail = new FoodDetail();
        try {
            Document document = Jsoup.connect(url).get();
            Elements select = document.select(".recipe_header_info > .info2");
            for (Element element : select) {
                detail.setWorkmanship(element.select(".info2_item1 > strong").text());//工艺
                detail.setFlavor(element.select(".info2_item2 > strong").text());//口味
                detail.setTime(element.select(".info2_item3 > strong").text());//时间
                detail.setDifficultyLevel(element.select(".info2_item4 > strong").text());//难度
            }
            List<String> mainList = new ArrayList<>();
            List<String> subList = new ArrayList<>();
            Elements mains = document.select(".recipe_header_info > .recipe_ingredientsw > .recipe_ingredients > .right > strong");
            for (Element main : mains) {
                mainList.add(main.text());
            }
            Elements subs = document.select(".recipe_header_info > .recipe_ingredientsw > .recipe_ingredients1 > .right > strong");
            for (Element sub : subs) {
                subList.add(sub.text());
            }
            //步骤
            Elements steps = document.select(".main > .main_left > .recipe_step_box > .recipe_step");
            List<FoodStep> stepList = new ArrayList<>();
            for (Element step : steps) {
                String stepNum = step.select(".recipe_step_num > p").text();
                String stepDescribe = step.select(".step_content > p").text();
                String stepImage = step.select(".step_content > img").attr("src");
                FoodStep foodStep = new FoodStep(Integer.parseInt(stepNum), stepDescribe, stepImage);
                stepList.add(foodStep);

            }
            //轮播图
            List<String> showFoodImageList = new ArrayList<>();
            Elements swiperList = document.select(".main > .main_left > .recipe_finish_box > .swiper-container > .swiper-wrapper > .swiper-slide > img");
            for (Element element : swiperList) {
                showFoodImageList.add(element.attr("src"));
            }
            //烹饪技巧
            Elements select1 = document.select(".main > .main_left > .recipe_tips > .recipe_tips_words > p");
            List<String> tips = new ArrayList<>();
            for (Element element : select1) {
                String[] split = element.text().split("。");
                tips.addAll(Arrays.asList(split));
            }
            mainList.removeAll(subList);
            //将主料辅料添加到详情类里
            detail.setMainMaterialList(mainList);
            detail.setAccessoriesList(subList);
            //设置步骤
            detail.setFoodStepList(stepList);
            detail.setShowFoodImageList(showFoodImageList);
            detail.setCookSkill(tips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return detail;
    }

    private static String makeImageUrl(String backgroundUrl){
        String[] split = backgroundUrl.split("\\(");
        String[] split1 = split[1].split("\\)");
        return split1[0];
    }
}
