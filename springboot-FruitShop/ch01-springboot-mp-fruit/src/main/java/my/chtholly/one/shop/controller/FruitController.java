package my.chtholly.one.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import my.chtholly.one.shop.entity.Fruit;
import my.chtholly.one.shop.service.FruitService;
import my.chtholly.one.shop.util.excel.OutPutExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chtholly
 * @since 2022-11-03
 */
//返回ajax请求，不能用于页面的转发
@RestController
@RequestMapping("/fruit")
public class FruitController {
    @Autowired
   private FruitService fruitService;

    @GetMapping("/select")
    public List<Fruit> select() {
        List<Fruit> list = fruitService.listAny();
        return list;
    }
    @GetMapping("/selectList")
    public List<Fruit> selectList(String number, String fruitname,String unit,Double min,Double max){
        Map<String,Object> map=new HashMap<>();
        if(!"".equals(number))
        map.put("id",number);
        if (!"".equals(fruitname))
        map.put("name",fruitname);
        if (!"".equals(unit))
        map.put("unit",unit);
        List<Fruit> list=fruitService.selectListFruit(map,min,max);
        return list;
    }
    @GetMapping("/selectnumber")
    public List<Fruit> selectnumber(@RequestParam String number) {
        QueryWrapper<Fruit> wrapper = new QueryWrapper<>();
        wrapper.eq("id", number);
        List<Fruit> list = fruitService.list(wrapper);
        if(list==null){
            System.out.println("查询失败");
            return null;
        }else{
            System.out.println("查询成功");
            return list;
        }

    }
    @GetMapping("/selectfruitname")
    public List<Fruit> selectfruitname(@RequestParam String fruitname) {
        QueryWrapper<Fruit> wrapper = new QueryWrapper<>();
        wrapper.like("name", fruitname);
        List<Fruit> list = fruitService.list(wrapper);
        if(list==null){
            System.out.println("查询失败");
            return null;
        }else{
            System.out.println("查询成功");
            return list;
        }
    }
    @GetMapping("/selectprice")
    public List<Fruit> selectprice(@RequestParam Double min,@RequestParam Double max) {

        QueryWrapper<Fruit> wrapper = new QueryWrapper<>();
        wrapper.ge("price", min);
        wrapper.le("price", max);
        List<Fruit> list = fruitService.list(wrapper);
        if(list==null){
            System.out.println("查询失败");
            return null;
        }else{
            System.out.println("查询成功");
            return list;
        }
    }
    @GetMapping("/selectunit")
    public List<Fruit> selectunit(@RequestParam String unit) {
        QueryWrapper<Fruit> wrapper = new QueryWrapper<>();
        wrapper.eq("unit", unit);
        List<Fruit> list = fruitService.list(wrapper);
        if(list==null){
            System.out.println("查询失败");
            return null;
        }else{
            System.out.println("查询成功");
            return list;
        }

    }

    @PostMapping("/addfruit")
    public void addfruit(String number, String fruitname, BigDecimal price, String unit, HttpServletResponse response, HttpSession session) throws IOException {
        boolean flag = fruitService.checkFruit(number);
        String info;

        if (flag) {
            Fruit fruit = new Fruit();
            fruit.setNumber(Integer.parseInt(number));
            fruit.setFruitname(fruitname);
            fruit.setPrice(price);
            fruit.setUnit(unit);
            boolean save = fruitService.save(fruit);
            if (save) {
                info = "ok";
                System.out.println("添加成功");

            } else {
                info = "err1";
                System.out.println("添加失败");

            }
        } else {
            info = "err2";
            System.out.println("该水果已存在，请重新输入");
        }

        response.getWriter().write(info);
    }
    @PostMapping("/delfruit")
    public void delfruit(Integer number,HttpServletResponse response, HttpSession session) throws IOException {

        String info;

        boolean save = fruitService.removeById(number);

        if (save) {
            info = "ok";
            System.out.println("删除成功");

        } else {
            info = "no";
            System.out.println("删除失败");

        }


        response.getWriter().write(info);
    }
    @PostMapping("/upfruit")
    public void upfruit(String number, String fruitname, BigDecimal price,String unit, HttpServletResponse response, HttpSession session) throws IOException {

        String info;

        QueryWrapper<Fruit> wrapper = new QueryWrapper<>();
        wrapper.eq("id",number);
        Fruit fruit = new Fruit();
        fruit.setNumber(Integer.parseInt(number));
        fruit.setFruitname(fruitname);
        fruit.setPrice(price);
        fruit.setUnit(unit);
        boolean save = fruitService.update(fruit,wrapper);
        if (save) {
            info = "ok";
            System.out.println("修改成功");

        } else {
            info = "err1";
            System.out.println("修改失败");

        }

        response.getWriter().write(info);
    }
    @GetMapping("/delmuchfruit")
    public void delmuchfruit(@RequestParam Integer[] number, HttpServletResponse response, HttpSession session) throws IOException {
        String  info;
        int j=0;
        for (int i=0;i<number.length;i++){
            fruitService.removeById(number[i]);
            j++;
        }
        if(j==number.length){
            info="ok";
            System.out.println("批量删除成功");
        }else{
            info="no";
            System.out.println("批量删除失败");
        }
        response.getWriter().write(info);

    }
    @GetMapping("/dao")
    public void daochu(HttpServletResponse response) throws IOException {
        List<Fruit> list = fruitService.listAny();
        OutPutExcel.outPutExcelMethod(list);
        response.getWriter().write("ok");
    }

}

