package cn.theo.logistics.dao.impl;

import cn.theo.logistics.dao.SignRecordDao;
import cn.theo.logistics.domain.Signrecord;
import cn.theo.logistics.utils.JDBCUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SignRecordDaoImpl implements SignRecordDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public Signrecord findBySignId(long signId) {
        String sql = "select * from signrecords where signid = ?";
        Signrecord signrecord = null;
        try{
        signrecord = template.queryForObject(sql, new BeanPropertyRowMapper<Signrecord>(Signrecord.class), signId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return signrecord;
    }

    @Override
    public int insertSignrecord(long signid, String sno, long signdate, String device,double latitude,double longitude) {
        String sql = "insert into signrecords(signid,sno,signdate,device,latitude,longitude) values(?,?,?,?,?,?)";
        int update = template.update(sql, signid, sno, signdate, device,latitude,longitude);
        return update;
    }
}
