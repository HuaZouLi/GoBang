package org.learn.gobang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.learn.gobang.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
