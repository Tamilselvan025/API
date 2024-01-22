package eventmanagement.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Client  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private long contact;
	private String email;
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientEvent>events;
	public Client() {
		events=new ArrayList<ClientEvent>();
	}
	public int getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<ClientEvent> getEvents() {
		return events;
	}
	public void setEvents(List<ClientEvent> events) {
		this.events = events;
	}
	public void addEvent(ClientEvent clientEvent) {
		events.add(clientEvent);
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", contact=" + contact + ", email=" + email + ", password="
				+ password + "]";
	}
	
	
}
