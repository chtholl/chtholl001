package my.chtholly.one.shop.service;

import my.chtholly.one.shop.entity.Fruit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chtholly
 * @since 2022-11-03
 */
public interface FruitService extends IService<Fruit> {
    List<Fruit> listAny();
    boolean checkFruit(String number);
     List<Fruit> selectListFruit(Map map,Double min, Double max);
}
