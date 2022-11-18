package my.chtholly;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class AutoMapper {
    public static void main(String[] args) {
        //创建AutoGenerator,MP中对象
        AutoGenerator ag=new AutoGenerator();
        //设置全局配置
        GlobalConfig gc=new GlobalConfig();
        //设置代码的生成的位置，磁盘的目录
        String path = System.getProperty("user.dir");
        gc.setOutputDir(path+"/ch01-springboot-mp-fruit/src/main/java/");
        //设置生成的类的名称(命名规则）
        gc.setMapperName("%sMapper");//所以的类都是默认Mapper结尾的
        //设置Service接口的命名
        gc.setServiceName("%sService");
        //设置Service实现类的名称
        gc.setServiceImplName("%sServiceImpl");
        //设置controller类的命名
        gc.setControllerName("%sController");
        //设置作者
        gc.setAuthor("chtholly");
        //设置主键id的配置
        gc.setIdType(IdType.ID_WORKER);

        ag.setGlobalConfig(gc);

        //设置数据源DateSource
        DataSourceConfig ds=new DataSourceConfig();
        //驱动
        ds.setDriverName("com.mysql.cj.jdbc.Driver");
        //设置url
        ds.setUrl("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true");
        //设置数据库的用户名
        ds.setUsername("root");
        //设置密码
        ds.setPassword("041105");
        //把DataSourceConfig赋值给AutoGenerator
        ag.setDataSource(ds);

        //设置Package信息
        PackageConfig pc=new PackageConfig();
        //设置模块名称，相当于包名，在这个包下面有mapper，service，controller
        pc.setModuleName("shop");
        //设置父包名称，order就在父包的下面生成
        pc.setParent("my.chtholly.one");
        ag.setPackageInfo(pc);

        //设置策略
        StrategyConfig sc=new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        //设置支持驼峰的命名规则
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        ag.setStrategy(sc);

        //执行代码的生成
        ag.execute();
    }
}
