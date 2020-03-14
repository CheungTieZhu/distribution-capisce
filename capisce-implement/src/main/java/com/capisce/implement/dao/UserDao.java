package com.capisce.implement.dao;

import com.capisce.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public boolean insert(UserInfoEntity userInfoEntity);
}
