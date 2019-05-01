package cn.theo.logistics.domain;
import java.io.Serializable;

public class Sign implements Serializable{
    private long signid;
    private String signname;
    private long starttime;
    private int duration;
    private double longitude;
    private double latitude;
    private int precise;
    private String sno;

    public Sign() {
    }

    public Sign(long signid, String signname, long starttime, int duration, double longitude, double latitude, int precise, String sno) {
        this.signid = signid;
        this.signname = signname;
        this.starttime = starttime;
        this.duration = duration;
        this.longitude = longitude;
        this.latitude = latitude;
        this.precise = precise;
        this.sno = sno;
    }

    public long getSignid() {
        return signid;
    }

    public void setSignid(long signid) {
        this.signid = signid;
    }

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }


    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public int getPrecise() {
        return precise;
    }

    public void setPrecise(int precise) {
        this.precise = precise;
    }
}
