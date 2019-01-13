package personal.studio.manager.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//@Where(clause = "latest_payment='YES'")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "latest_payment")
@DiscriminatorValue("NO")
public class Payment {

    public static final Integer DEFAULTTIMES = 5;

    public Payment() {
    }

    public Payment(Student student, Date date, float unitPrice) {
        this.student = student;
        this.date = date;
        this.unitPrice = unitPrice;
        this.times = Payment.DEFAULTTIMES;
        this.discount = student.getDiscount();
//		this.latestPayment = YesNo.YES;
        this.amount = this.unitPrice * this.times * (1 - (this.discount == null ? 0 : this.discount));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    private Student student;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "amount", precision = 8, scale = 2)
    private float amount;

    @JsonProperty(value = "unit_price")
    @Column(name = "unit_price", precision = 8, scale = 2)
    private float unitPrice;

    private Integer times;

    @Column(name = "discount", precision = 8, scale = 2)
    private Float discount;

    @JsonProperty(value = "latest_payment")
    @Column(name = "latest_payment", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private YesNo latestPayment;

    private String comment;

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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", unitPrice=" + unitPrice +
                ", times=" + times +
                ", discount=" + discount +
                ", latestPayment=" + latestPayment +
                ", comment='" + comment + '\'' +
                '}';
    }
}
