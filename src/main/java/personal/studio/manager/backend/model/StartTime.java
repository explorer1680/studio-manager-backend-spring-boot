package personal.studio.manager.backend.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class StartTime {

    private Integer hour;
    private Integer minute;
    @Enumerated(EnumType.STRING)
    private AMPM ampm;

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public AMPM getAmpm() {
        return ampm;
    }

    public void setAmpm(AMPM ampm) {
        this.ampm = ampm;
    }

    @Override
    public String toString() {
        return "StartTime{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", ampm=" + ampm +
                '}';
    }
}
