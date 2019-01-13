package personal.studio.manager.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassSearchResult {
    @JsonProperty(value="payment_times")
    private Integer paymentTimes;
    @JsonProperty(value="total_amount")
    private Float totalAmount;
    @JsonProperty(value="class_record_numbers")
    private Integer classRecordNumbers;

    public Integer getPaymentTimes() {
        return paymentTimes;
    }

    public void setPaymentTimes(Integer paymentTimes) {
        this.paymentTimes = paymentTimes;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getClassRecordNumbers() {
        return classRecordNumbers;
    }

    public void setClassRecordNumbers(Integer classRecordNumbers) {
        this.classRecordNumbers = classRecordNumbers;
    }
}
