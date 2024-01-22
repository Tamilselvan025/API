package eventmanagement.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.OneToMany;

@Entity
public class Admin{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String mail;
	private String password;
	private long contact;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Service> services;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	public void addService(Service service) {
		services.add(service);
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", mail=" + mail + ", password=" + password + ", contact="
				+ contact + ", services=" + services + "]";
	}
	

}
