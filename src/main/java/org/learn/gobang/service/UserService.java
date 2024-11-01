package org.learn.gobang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.learn.gobang.pojo.User;

public interface UserService extends IService<User> {
    User login(User user);
}
