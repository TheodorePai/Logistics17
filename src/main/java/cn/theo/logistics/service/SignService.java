package cn.theo.logistics.service;

import cn.theo.logistics.domain.Sign;

import java.util.List;

public interface SignService {
    public boolean startSign(Sign sign);
    public List<Sign> querySigns(long timestamp);
    public Sign findSignById(long id);
    List<Sign> queryOverdueSign();
}
