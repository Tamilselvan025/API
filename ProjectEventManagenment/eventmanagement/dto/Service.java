package eventmanagement.dto;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Service  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double costPerDay;
	private double costPerPerson;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public double getCostPerDay() {
		return costPerDay;
	}
	public void setCostPerDay(double costPerDay) {
		this.costPerDay = costPerDay;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getCostPerPerson() {
		return costPerPerson;
	}
	public void setCostPerPerson(double costPerPerson) {
		this.costPerPerson = costPerPerson;
	}
	
	@Override
	public boolean equals(Object obj) {
		Service s=(Service)obj;
		if (this.id == s.id)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return false;
	}
	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", costPerDay=" + costPerDay + ", costPerPerson="
				+ costPerPerson + "]";
	}
	
	
	
	
}
