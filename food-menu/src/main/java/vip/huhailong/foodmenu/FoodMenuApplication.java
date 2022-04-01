package vip.huhailong.foodmenu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("vip.huhailong.foodmenu.mapper")
@SpringBootApplication
public class FoodMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodMenuApplication.class, args);
    }

}
