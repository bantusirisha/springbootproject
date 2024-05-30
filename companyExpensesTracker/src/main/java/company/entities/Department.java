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

@Entity(name = "company.entities.Department")
@Table(name = "departments")
public class Department {

	@Id
	@Column(name = "deptcode", length = 10)
	private String deptcode;

	@Column(name = "deptname", length = 50)
	private String deptname;

	@Column(name = "hod", length = 50)
	private String hod;

	//expenditure relationship
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	@JsonIgnore
	private List<Expenditure> expenditure = new ArrayList<Expenditure>();

	
	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public List<Expenditure> getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(List<Expenditure> expenditure) {
		this.expenditure = expenditure;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}
}