package cn.theo.logistics.dao;

import cn.theo.logistics.domain.User;

public interface UserDao {
    public User findByOpenid(String openid);
    public Boolean updataOpenid(User user, String openid);
}
