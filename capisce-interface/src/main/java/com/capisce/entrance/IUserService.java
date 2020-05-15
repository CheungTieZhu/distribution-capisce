package com.capisce.entrance;

import com.capisce.entity.NetValueEntity;
import com.capisce.entity.SelectPageEntity;
import com.capisce.entity.UserInfoEntity;

import java.text.ParseException;
import java.util.List;

/**
 * @author zxb
 */
public interface IUserService {
    public boolean createNewAccount(UserInfoEntity userInfoEntity);

    public List<NetValueEntity> getNetValueSample(SelectPageEntity selectPageEntity);

    public int insertMockNetValue(NetValueEntity netValueEntity) throws ParseException;
}
