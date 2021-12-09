package com.campus.nicecampus.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.nicecampus.base.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
