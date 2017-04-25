package www.wtu.com.run.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/20.
 */
@Entity(nameInDb = "t_climb_data")
public class ClimbData implements Serializable{
    public static final long serialVersionUID = 536871008;
    @Id(autoincrement = true)
    private Long id;

    private String username;
    private String distance;
    private String bushu;

    @Property(nameInDb = "time_sum")
    private String time;
    private String kll;
    @Property(nameInDb = "create_date")
    private long date;
    @Generated(hash = 8504093)
    public ClimbData(Long id, String username, String distance, String bushu,
            String time, String kll, long date) {
        this.id = id;
        this.username = username;
        this.distance = distance;
        this.bushu = bushu;
        this.time = time;
        this.kll = kll;
        this.date = date;
    }
    @Generated(hash = 1373266264)
    public ClimbData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDistance() {
        return this.distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public String getBushu() {
        return this.bushu;
    }
    public void setBushu(String bushu) {
        this.bushu = bushu;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getKll() {
        return this.kll;
    }
    public void setKll(String kll) {
        this.kll = kll;
    }
    public long getDate() {
        return this.date;
    }
    public void setDate(long date) {
        this.date = date;
    }

    


}
