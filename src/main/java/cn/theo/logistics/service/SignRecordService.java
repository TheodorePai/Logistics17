package cn.theo.logistics.service;

import cn.theo.logistics.domain.Signrecord;

public interface SignRecordService {
    public Signrecord findSignInfo(long signId);
    int insert(long signid,String sno,long signdate,String device,double latitude,double longitude);
}
