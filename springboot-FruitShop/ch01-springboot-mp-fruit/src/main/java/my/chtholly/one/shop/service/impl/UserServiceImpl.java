package my.chtholly.one.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import my.chtholly.one.shop.entity.User;
import my.chtholly.one.shop.mapper.UserMapper;
import my.chtholly.one.shop.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chtholly
 * @since 2022-11-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User checkUser(String username) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = this.getOne(wrapper);
        if(user==null){
            return null;
        }else{
            return user;
        }
    }
}
