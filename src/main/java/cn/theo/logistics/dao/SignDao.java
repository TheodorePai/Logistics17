package cn.theo.logistics.dao;

import cn.theo.logistics.domain.Sign;

import java.util.List;

public interface SignDao {
    public Boolean createSign(Sign sign);
    public List<Sign> querySignByTimestamp(long timestamp);
    Sign findSignById(long id);
    List<Sign> findOverdueSign();
}
