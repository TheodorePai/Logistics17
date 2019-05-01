package cn.theo.logistics.dao.impl;

import cn.theo.logistics.dao.SignDao;
import cn.theo.logistics.domain.Sign;
import cn.theo.logistics.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SignDaoImpl implements SignDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public Boolean createSign(Sign sign) {
        //1.定义sql
        String sql = "insert into signs(signid,signname,starttime,duration,longitude,latitude,precise,sno) values(?,?,?,?,?,?,?,?)";
        //2.执行sql

        int update = template.update(sql, sign.getSignid(),
                sign.getSignname(),
                sign.getStarttime(),
                sign.getDuration(),
                sign.getLongitude(),
                sign.getLatitude(),
                sign.getPrecise(),
                sign.getSno()
        );
        if(update>=1){
            return true;
        }
        return false;
    }

    @Override
    public List<Sign> querySignByTimestamp(long timestamp) {
        String sql = "select * from signs where starttime + duration >"+ timestamp;
        List<Sign> signs = template.query(sql, new BeanPropertyRowMapper<Sign>(Sign.class));
        System.out.println(signs);
        return signs;
    }

    @Override
    public Sign findSignById(long id) {
        String sql = "select * from signs where signid=?";
        Sign sign = template.queryForObject(sql, new BeanPropertyRowMapper<Sign>(Sign.class), id);
        return sign;
    }

    @Override
    public List<Sign> findOverdueSign() {
        String sql = "select * from signs where (starttime + duration) <"+ System.currentTimeMillis()/1000;
        List<Sign> signs = template.query(sql, new BeanPropertyRowMapper<Sign>(Sign.class));
        return signs;
    }
}
