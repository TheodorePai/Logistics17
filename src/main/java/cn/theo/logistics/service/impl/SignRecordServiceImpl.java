package cn.theo.logistics.service.impl;

import cn.theo.logistics.dao.SignRecordDao;
import cn.theo.logistics.dao.impl.SignRecordDaoImpl;
import cn.theo.logistics.domain.Signrecord;
import cn.theo.logistics.service.SignRecordService;

public class SignRecordServiceImpl implements SignRecordService {
    @Override
    public Signrecord findSignInfo(long signId) {
        SignRecordDao dao = new SignRecordDaoImpl();
        Signrecord signrecord = dao.findBySignId(signId);
        return signrecord;
    }

    @Override
    public int insert(long signid, String sno, long signdate, String device,double latitude,double longitude) {
        SignRecordDao dao = new SignRecordDaoImpl();
        int i = dao.insertSignrecord(signid, sno, signdate, device,latitude,longitude);
        return i;
    }
}
