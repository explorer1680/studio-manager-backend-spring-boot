package personal.studio.manager.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ClassInfo {
	private Date date;
	@JsonProperty(value = "student_id")
	private Integer studentId;
	private Integer times;//this is only used when customize class record is used. Normally, one student only take one class per day.
	private String comment;

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

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ClassInfo [date=" + date + ", studentId=" + studentId + ", times=" + times + ", comment=" + comment
				+ "]";
	}
}
