package company.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "categories")
public class Category {

	@Id
	@Column(name = "Catcode", length = 10)
	private String catCode;

	@Column(name = "Catname", length = 50)
	private String catName;
	
	//expenditure relationship
	@OneToMany(cascade=CascadeType.ALL, mappedBy="category")
	@JsonIgnore
	private List<Expenditure> expenditure=new ArrayList<Expenditure>();

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@Override
	public String toString() {
		return "Category [catCode=" + catCode + ", catName=" + catName + ", expenditure=" + expenditure + "]";
	}
	
	
}
