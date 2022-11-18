package my.chtholly.one;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("my.chtholly.one.shop.mapper")
public class Ch01SpringbootMpFruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch01SpringbootMpFruitApplication.class, args);
    }

}
