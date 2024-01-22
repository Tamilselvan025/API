package eventmanagement.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class ClientEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int noOfPeople;
	private LocalDate startDate;
	private int clientEventNoOfDays;
	private String location;
	private double cost;
	@ManyToOne
	private Client client;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientService> services;
	private EventType eventType;
	public ClientEvent() {
		services=new ArrayList<ClientService>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
		generateCost();
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public int getClientEventNoOfDays() {
		return clientEventNoOfDays;
	}
	public void setClientEventNoOfDays(int clientEventNoOfDays) {
		this.clientEventNoOfDays = clientEventNoOfDays;
		generateCost();
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<ClientService> getServices() {
		return services;
	}
	public void setServices(List<ClientService> services) {
		this.services = services;
		generateCost();
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	private void generateCost() {
		double a=0;
		for(ClientService s:services) {
			if(s.getCost()>=1)
			a=a+s.getCost();
		}
		this.cost=a*noOfPeople;
	}
	public void addClientService(ClientService service) {
		services.add(service);
	}
	@Override
	public String toString() {
		return "ClientEvent [id=" + id + ", noOfPeople=" + noOfPeople + ", startDate=" + startDate
				+ ", clientEventNoOfDays=" + clientEventNoOfDays + ", location=" + location + ", cost=" + cost
				+ ", client=" + client + ", eventType=" + eventType + "]";
	}
	
	
	
}
