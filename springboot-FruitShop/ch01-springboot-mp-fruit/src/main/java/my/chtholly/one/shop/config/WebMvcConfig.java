package my.chtholly.one.shop.config;

import my.chtholly.one.shop.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:login.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
     HandlerInterceptor interceptor=new LoginInterceptor();
     String path[]={"/fruit.html","/user/fruitShow"};
     String excludePath[]={"/user/loginShow","/register.html","/login.html"};
     registry.addInterceptor(interceptor).addPathPatterns(path).excludePathPatterns(excludePath);
    }
}
