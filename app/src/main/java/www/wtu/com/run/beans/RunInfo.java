package www.wtu.com.run.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/4/21.
 */
@Entity(nameInDb = "t_run_info")
public class RunInfo implements Serializable {

    private static final long serialVersionUID = 4271339305185130178L;

    @Id(autoincrement = true)
    private Long id;

    private String tilte;

    private String content;

    private String url;

    @Generated(hash = 1456805450)
    public RunInfo(Long id, String tilte, String content, String url) {
        this.id = id;
        this.tilte = tilte;
        this.content = content;
        this.url = url;
    }

    @Generated(hash = 312535208)
    public RunInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTilte() {
        return this.tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
