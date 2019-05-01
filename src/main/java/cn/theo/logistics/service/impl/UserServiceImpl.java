package cn.theo.logistics.service.impl;

import cn.theo.logistics.dao.UserDao;
import cn.theo.logistics.dao.impl.UserDaoImpl;
import cn.theo.logistics.domain.Sign;
import cn.theo.logistics.domain.User;
import cn.theo.logistics.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean checkBind(String openid) {
        User user = userDao.findByOpenid(openid);
        if(user==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean bindsno(User user,String openid) {
        UserDao dao = new UserDaoImpl();
        Boolean res = dao.updataOpenid(user, openid);
        if(res==true){
            return true;
        }
        return false;
    }

    @Override
    public User findUserInfo(String openid) {
        UserDao dao = new UserDaoImpl();
        User user = dao.findByOpenid(openid);
        return user;
    }

}
