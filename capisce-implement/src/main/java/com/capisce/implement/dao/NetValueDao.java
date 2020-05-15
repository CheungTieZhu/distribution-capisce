package com.capisce.implement.dao;

import com.capisce.entity.NetValueEntity;
import com.capisce.entity.SelectPageEntity;
import com.capisce.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NetValueDao {
    public List<NetValueEntity> getNetValueSample(SelectPageEntity selectPageEntity);

    public int insertBatch(List<NetValueEntity> list);
}
