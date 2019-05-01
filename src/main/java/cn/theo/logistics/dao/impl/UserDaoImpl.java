package cn.theo.logistics.dao.impl;

import cn.theo.logistics.dao.UserDao;
import cn.theo.logistics.domain.User;

import cn.theo.logistics.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public User findByOpenid(String openid) {
        User user = null;
        String sql = "select * from users where openid = ?";
        try {
            user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),openid);
        }catch (Exception e){}
        return user;
    }

    @Override
    public Boolean updataOpenid(User user, String openid) {
        String pid = user.getPid();
        String sql = "update users set openid=? where (sno = ? and pid like '%"+ pid +"')";
        int update = template.update(sql, openid,user.getSno());
        if(update>=1){
            return true;
        }
        return false;
    }
}
