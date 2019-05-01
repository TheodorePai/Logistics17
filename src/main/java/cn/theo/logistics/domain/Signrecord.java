package cn.theo.logistics.domain;
import java.io.Serializable;

public class Signrecord implements Serializable{
    private long signId;
    private String sno;
    private long signDate;
    private String device;
    private double latitude;
    private double longitude;

    public Signrecord() {
    }

    public Signrecord(long signId, String sno, long signDate, String device, double latitude, double longitude) {
        this.signId = signId;
        this.sno = sno;
        this.signDate = signDate;
        this.device = device;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getSignId() {
        return signId;
    }

    public void setSignId(long signId) {
        this.signId = signId;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public long getSignDate() {
        return signDate;
    }

    public void setSignDate(long signDate) {
        this.signDate = signDate;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
