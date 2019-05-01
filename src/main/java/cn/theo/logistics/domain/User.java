package cn.theo.logistics.domain;
import java.io.Serializable;

public class User implements Serializable{
    private int uid;
    private String sno;
    private String sname;
    private int sgender;
    private String sgrade;
    private String openid;
    private String pid;
    private int auth;

    public User() {
    }

    public User(int uid, String sno, String sname, int sgender, String sgrade, String openid, String pid, int auth) {
        this.uid = uid;
        this.sno = sno;
        this.sname = sname;
        this.sgender = sgender;
        this.sgrade = sgrade;
        this.openid = openid;
        this.pid = pid;
        this.auth = auth;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSgender() {
        return sgender;
    }

    public void setSgender(int sgender) {
        this.sgender = sgender;
    }

    public String getSgrade() {
        return sgrade;
    }

    public void setSgrade(String sgrade) {
        this.sgrade = sgrade;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }
}
