package my.chtholly.one.shop.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
       Object user = session.getAttribute("user1");

        if(user!=null && !" ".equals(user))
        {
            return true;

        }else{
            request.setAttribute("msg", "请先登录!");
            request.getRequestDispatcher("/user/loginShow").forward(request,response);
            return false;

        }
    }
}
