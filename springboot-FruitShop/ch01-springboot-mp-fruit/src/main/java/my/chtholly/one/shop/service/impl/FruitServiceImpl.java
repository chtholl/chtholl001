package my.chtholly.one.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import my.chtholly.one.shop.entity.Fruit;
import my.chtholly.one.shop.mapper.FruitMapper;
import my.chtholly.one.shop.service.FruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chtholly
 * @since 2022-11-03
 */
@Service
public class FruitServiceImpl extends ServiceImpl<FruitMapper, Fruit> implements FruitService {
    @Resource
    private FruitMapper fruitMapper;

    @Override
    public List<Fruit> listAny() {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.ne("name","");
        List list = fruitMapper.selectList(wrapper);
        return list;
    }

    @Override
    public boolean checkFruit(String number) {
        if("".equals(number)){
            System.out.println("水果编号不能为空，请输入");
            return false;

        }else{
            QueryWrapper<Fruit> wrapper = new QueryWrapper<>();
            wrapper.eq("id", number);
            Fruit fruit = this.getOne(wrapper);
            if (fruit == null) {
                return true;
            } else {
                return false;
            }}
    }

    @Override
    public List<Fruit> selectListFruit(Map map, Double min, Double max) {
        QueryWrapper<Fruit> wrapper=new QueryWrapper<>();
        wrapper.allEq(map, false);
        if(min!=null&&max!=null){
            wrapper.ge("price", min);
            wrapper.le("price", max);
        }
        List<Fruit> list =fruitMapper.selectList(wrapper);
        return list;
    }

}
