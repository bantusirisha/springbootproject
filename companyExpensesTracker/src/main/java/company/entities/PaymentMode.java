package company.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Paymentmodes")
public class PaymentMode {

	@Id
	@Column(name = "paymentmodecode", length = 10)
	private String paymentmodecode;

	@Column(name = "Paymentname", length = 50)
	private String paymentName;

	@Column(name = "Paymentremarks", length = 50)
	private String paymentRemarks;

	// paymentMode relationship
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMode")
	@JsonIgnore
	private List<Expenditure> expenditure = new ArrayList<Expenditure>();

	public String getPaymentmodecode() {
		return paymentmodecode;
	}

	public void setPaymentmodecode(String paymentmodecode) {
		this.paymentmodecode = paymentmodecode;
	}

	public List<Expenditure> getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(List<Expenditure> expenditure) {
		this.expenditure = expenditure;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPaymentRemarks() {
		return paymentRemarks;
	}

	public void setPaymentRemarks(String paymentRemarks) {
		this.paymentRemarks = paymentRemarks;
	}

}
