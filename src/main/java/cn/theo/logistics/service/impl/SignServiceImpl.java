package cn.theo.logistics.service.impl;

import cn.theo.logistics.dao.SignDao;
import cn.theo.logistics.dao.impl.SignDaoImpl;
import cn.theo.logistics.domain.Sign;
import cn.theo.logistics.service.SignService;

import java.util.ArrayList;
import java.util.List;

public class SignServiceImpl implements SignService {
    @Override
    public boolean startSign(Sign sign) {
        SignDao dao = new SignDaoImpl();
        Boolean flag = dao.createSign(sign);
        if (flag){
            return true;
        }
        return false;
    }

    @Override
    public List<Sign> querySigns(long timestamp) {
        SignDao dao = new SignDaoImpl();
        List<Sign> signs = dao.querySignByTimestamp(timestamp);
        System.out.println(signs);
        return signs;
    }

    @Override
    public Sign findSignById(long id) {
        SignDao dao = new SignDaoImpl();
        Sign sign = dao.findSignById(id);
        return sign;
    }

    @Override
    public List<Sign> queryOverdueSign() {
        SignDao dao = new SignDaoImpl();
        List<Sign> overdueSigns = dao.findOverdueSign();
        return overdueSigns;
    }
}
