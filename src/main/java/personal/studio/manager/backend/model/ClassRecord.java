package personal.studio.manager.backend.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


/*
    historyRecord:
        when this record belong to latest payment, it is NO, otherwise, YES
    payment:
        this part is set when new payment is made, in that moment, some class record's historyRecord is set YES, and payment is set to previous payment.
 */
@Entity
public class ClassRecord {

    public ClassRecord() {
    }

    public ClassRecord(Student student, Date date, String comment) {
        this.student = student;
        this.date = date;
        this.historyRecord = YesNo.NO;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    private Student student;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String comment;

    //when this record belong to latest payment, it is NO
    //otherwise, YES
    @JsonProperty(value = "history_record")
    @Column(name = "history_record")
    @Enumerated(EnumType.STRING)
    private YesNo historyRecord;

    @JsonIgnore
    @ManyToOne
    private Payment payment;
    
    @JsonProperty(value="payment_id")
    @Column(name="payment_id", updatable=false, insertable = false)
    private Integer paymentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public YesNo getHistoryRecord() {
        return historyRecord;
    }

    public void setHistoryRecord(YesNo historyRecord) {
        this.historyRecord = historyRecord;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	@Override
    public String toString() {
        return "ClassRecord{" +
                "id=" + id +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", historyRecord=" + historyRecord +
                ", paymentId=" + paymentId +
                '}';
    }
}
