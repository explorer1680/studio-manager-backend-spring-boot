package personal.studio.manager.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PaymentInfo {
	private Date date;
	@JsonProperty(value = "student_id")
	private Integer studentId;
	private boolean regular;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public boolean isRegular() {
		return regular;
	}

	public void setRegular(boolean regular) {
		this.regular = regular;
	}

	@Override
	public String toString() {
		return "PaymentInfo [date=" + date + ", studentId=" + studentId + ", regular=" + regular + "]";
	}
}
