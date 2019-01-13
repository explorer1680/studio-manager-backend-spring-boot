package personal.studio.manager.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StudentSearchResult {
	
	@JsonProperty(value="payment_list")
	private List<Payment> paymentList;
	
	@JsonProperty(value="class_record_list")
	private List<ClassRecord> classRecordList;

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public List<ClassRecord> getClassRecordList() {
		return classRecordList;
	}

	public void setClassRecordList(List<ClassRecord> classRecordList) {
		this.classRecordList = classRecordList;
	}
}
