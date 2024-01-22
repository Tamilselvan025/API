package eventmanagement.dto;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class ClientService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double cost;
	private int days;
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
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
		generateCost();
	}
	public double getCostPerPerson() {
		return costPerPerson;
	}
	public void setCostPerPerson(double costPerPerson) {
		this.costPerPerson = costPerPerson;
		generateCost();
	}
	private void generateCost() {
		cost=costPerPerson*days;
	
	}
	
	
	@Override
	public boolean equals(Object obj) {
		ClientService cs=(ClientService) obj;
		if (this.id == cs.id)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "ClientService [id=" + id + ", name=" + name + ", cost=" + cost + ", days=" + days + ", costPerPerson="
				+ costPerPerson + "]";
	}
	

}
