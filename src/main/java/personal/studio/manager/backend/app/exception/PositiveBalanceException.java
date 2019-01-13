package personal.studio.manager.backend.app.exception;

import personal.studio.manager.backend.model.Student;

import java.util.Date;

public class PositiveBalanceException extends Exception{

	private static final long serialVersionUID = 1L;
	private String name;
    private Date latestPaymentDate;
    private Integer coverdTimes;
    private Integer classTimes;

    public PositiveBalanceException(Student student){
        super();

        this.name = student.getFirstName() + ' ' + student.getLastName();
        this.latestPaymentDate = student.getLatestPayment().getDate();
        this.coverdTimes = student.getLatestPayment().getTimes();
        this.classTimes = student.getClassRecords().size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLatestPaymentDate() {
        return latestPaymentDate;
    }

    public void setLatestPaymentDate(Date latestPaymentDate) {
        this.latestPaymentDate = latestPaymentDate;
    }

    public Integer getCoverdTimes() {
        return coverdTimes;
    }

    public void setCoverdTimes(Integer coverdTimes) {
        this.coverdTimes = coverdTimes;
    }

    public Integer getClassTimes() {
        return classTimes;
    }

    public void setClassTimes(Integer classTimes) {
        this.classTimes = classTimes;
    }
}
