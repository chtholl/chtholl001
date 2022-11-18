package my.chtholly.one.shop.entity;

import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author chtholly
 * @since 2022-11-03
 */
public class Fruit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    @ExcelProperty(value = "id")
    private Integer number;
    @ExcelProperty(value = "水果名称")
    @TableField("name")
    private String fruitname;
    @ExcelProperty(value = "水果价格")
    private BigDecimal price;
    @ExcelProperty(value = "水果类型")
    private String unit;


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFruitname() {
        return fruitname;
    }

    public void setFruitname(String fruitname) {
        this.fruitname = fruitname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "number=" + number +
                ", fruitname='" + fruitname + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                '}';
    }
}
