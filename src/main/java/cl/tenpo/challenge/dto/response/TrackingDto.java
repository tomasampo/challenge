package cl.tenpo.challenge.dto.response;

import cl.tenpo.challenge.domain.Tracking;

import java.time.Instant;

public class TrackingDto {
    private Instant date;
    private String userName;
    private String url;
    private int responseStatus;

    public TrackingDto(Tracking tracking) {
        this.date = tracking.getDate();
        this.userName= tracking.getUserName();
        this.url = tracking.getUrl();
        this.responseStatus = tracking.getResponseStatus();
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
        final StringBuffer sb = new StringBuffer("TrackingDto{");
        sb.append("date=").append(date);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", responseStatus=").append(responseStatus);
        sb.append('}');
        return sb.toString();
    }
}
