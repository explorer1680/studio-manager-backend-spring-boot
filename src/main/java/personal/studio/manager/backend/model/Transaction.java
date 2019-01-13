package personal.studio.manager.backend.model;

import java.util.List;


public class Transaction {

	private List<PaymentInfo> payments;
	private List<ClassInfo> classes;

	public List<PaymentInfo> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentInfo> payments) {
		this.payments = payments;
	}

	public List<ClassInfo> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassInfo> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "Transaction [payments=" + payments + ", classes=" + classes + "]";
	}
}
