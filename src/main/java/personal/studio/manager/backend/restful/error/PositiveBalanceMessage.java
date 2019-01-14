package personal.studio.manager.backend.restful.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import personal.studio.manager.backend.app.exception.PositiveBalanceException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

public class PositiveBalanceMessage {
    private String name;
    @JsonProperty("latest_payment_date")
    private Date lastPaymentDate;
    @JsonProperty("coverd_times")
    private Integer coverdTimes;
    @JsonProperty("class_times")
    private Integer classTimes;

    public PositiveBalanceMessage(){}

    public PositiveBalanceMessage(PositiveBalanceException exception){
        this.name = exception.getName();
        this.lastPaymentDate = exception.getLatestPaymentDate();
        this.coverdTimes = exception.getCoverdTimes();
        this.classTimes = exception.getClassTimes();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
