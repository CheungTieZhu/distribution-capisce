package com.capisce.implement.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.capisce.entity.NetValueEntity;
import com.capisce.entity.SelectPageEntity;
import com.capisce.entrance.IUserService;
import com.capisce.implement.dao.NetValueDao;
import com.capisce.implement.dao.UserDao;
import com.capisce.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhangxb
 */
@Service(version = "1.0.0", interfaceClass = IUserService.class, timeout = 15000)
@Component
public class UserServiceImpl implements IUserService {
    @Resource
    private UserDao userDao;

    @Resource
    private NetValueDao netValueDao;

    @Override
    public boolean createNewAccount(@RequestBody UserInfoEntity userInfoEntity) {
        return userDao.insert(userInfoEntity);
    }

    @Override
    public List<NetValueEntity> getNetValueSample(SelectPageEntity selectPageEntity) {
        selectPageEntity.setBeginIndex((selectPageEntity.getPage() - 1)*selectPageEntity.getRow());
        return netValueDao.getNetValueSample(selectPageEntity);
    }

    @Override
    public int insertMockNetValue(NetValueEntity netValueEntity) throws ParseException {
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date targetDate = simpleDateFormat.parse(netValueEntity.getTargetDate());
        List<NetValueEntity> netValueList = new ArrayList<>();
        String currentNetValue = netValueEntity.getNetValue();
        while (c.getTime().compareTo(targetDate) > 0){
            NetValueEntity temp = new NetValueEntity();
            currentNetValue = String.valueOf(Double.parseDouble(currentNetValue)+0.0001);
            temp.setNetValue(currentNetValue);
            c.add(Calendar.DATE, -1);
            temp.setNetDate(simpleDateFormat.format(c.getTime()));
            temp.setFundCode(netValueEntity.getFundCode());
            netValueList.add(temp);
        }
        return netValueDao.insertBatch(netValueList);
    }
}
