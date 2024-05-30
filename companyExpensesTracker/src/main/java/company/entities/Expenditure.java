package company.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Expenditures")
public class Expenditure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "catcode", length = 10)
	private String catcode;

	@Column(name = "deptcode", length = 10)
	private String deptcode;

	@NotBlank(message = "Amount is not null")
	@Column(name = "amount", length = 50)
	private Double amount;

	@Column(name = "expdate")
	private LocalDate expdate;

	@Column(name = "authorizedby", length = 50)
	private String authorizedby;

	@Column(name = "description", length = 50)
	private String description;

	@Column(name = "paymentmodecode", length = 50)
	private String paymentmodecode;

	@Column(name = "remarks", length = 50)
	private String remarks;

	// category relationship
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "catcode", insertable = false, updatable = false)
	private Category category;

	// department relationship
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "deptcode", insertable = false, updatable = false)
	private Department department;

	// paymentMode relationship
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "paymentmodecode", insertable = false, updatable = false)
	private PaymentMode paymentMode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatcode() {
		return catcode;
	}

	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getExpdate() {
		return expdate;
	}

	public void setExpdate(LocalDate expdate) {
		this.expdate = expdate;
	}

	public String getAuthorizedby() {
		return authorizedby;
	}

	public void setAuthorizedby(String authorizedby) {
		this.authorizedby = authorizedby;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPaymentmodecode() {
		return paymentmodecode;
	}

	public void setPaymentmodecode(String paymentmodecode) {
		this.paymentmodecode = paymentmodecode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
}