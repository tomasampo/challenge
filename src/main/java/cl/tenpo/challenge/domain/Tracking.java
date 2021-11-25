package cl.tenpo.challenge.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Table(name = "tracking")
public class Tracking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    private Instant date;
    private String userName;
    private String url;
    private int responseStatus;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tracking{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", responseStatus='").append(responseStatus).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
