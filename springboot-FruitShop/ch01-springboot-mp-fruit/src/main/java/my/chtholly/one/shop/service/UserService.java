package my.chtholly.one.shop.service;

import my.chtholly.one.shop.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chtholly
 * @since 2022-11-03
 */
public interface UserService extends IService<User> {
    public User checkUser(String username);

}
