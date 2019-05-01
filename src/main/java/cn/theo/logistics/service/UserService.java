package cn.theo.logistics.service;

import cn.theo.logistics.domain.Sign;
import cn.theo.logistics.domain.User;

public interface UserService {
    public boolean checkBind(String openid);
    public boolean bindsno(User user, String openid);
    public User findUserInfo(String openid);
}
