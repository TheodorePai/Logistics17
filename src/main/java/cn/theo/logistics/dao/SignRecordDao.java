package cn.theo.logistics.dao;

import cn.theo.logistics.domain.Signrecord;

public interface SignRecordDao {
    public Signrecord findBySignId(long signId);
    int insertSignrecord(long signid,String sno,long signdate,String device,double latitude,double longitude);
}
