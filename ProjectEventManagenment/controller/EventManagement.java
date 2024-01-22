package controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.DAO;
import eventmanagement.dto.*;

public class EventManagement {
	static Scanner sc=new Scanner(System.in);
	DAO<Admin> adminDAO=new DAO<Admin>(Admin.class);
	DAO<Service> serviceDAO=new DAO<Service>(Service.class);
	DAO<ClientEvent> clientEventDAO=new DAO<ClientEvent>(ClientEvent.class);
	DAO<ClientService> clientServiceDAO=new DAO<ClientService>(ClientService.class);
	DAO<Client> clientDAO=new DAO<Client>(Client.class);
	
	public static void main(String[] args) {
		
		EventManagement em=new EventManagement();
		System.out.println(em.removeClientService());
		
	}
	
	public Admin saveAdmin() {
		Admin a=new Admin();
		System.out.println("Enter Admin name");
		a.setName(sc.next());
		System.out.println("Enter Admin Email");
		a.setMail(sc.next());
		System.out.println("Enter Admin password");
		a.setPassword(sc.next());
		System.out.println("Enter Admin contact");
		a.setContact(sc.nextLong());
		
		return adminDAO.save(a);
	}
	public Admin adminLogin() {
		System.out.println("Verify it's Admin");
		System.out.println("Enter the Admin email");
		String email=sc.next();
		System.out.println("Enter the Admin password");
		String password=sc.next();
		Admin a=JPQL.getAdminByEmail(email);
		if(a.getPassword().equals(password)) {
			System.out.println("Verification success");
			return a;
		}
		System.out.println("Verification failed");
		return null;
	}
	public Service saveService() {
		Admin a=adminLogin();
		if(a==null)return null;
			Service s=new Service();
			System.out.println("Enter the Service name");
			s.setName(sc.next());
			System.out.println("Enter the Service cost per day");
			s.setCostPerDay(sc.nextDouble());
			System.out.println("Enter the Service cost per person");
			s.setCostPerPerson(sc.nextDouble());
			a.addService(s);
			adminDAO.upadte(a, a.getId());
			
			return s;
		
			
	}
	public List<Service> getAllServices(){
		Admin a=adminLogin();
		if(a!=null) {
			System.out.println(JPQL.getServices()!=null? JPQL.getServices():"NO SERVICE AVAILABLE");
		}
		return a.getServices();
		
	}
	public Service updateService() {
		Admin a=adminLogin();
		if(a!=null) {
		List<Service>s=JPQL.getServices();
		
		if(s==null)System.out.println("There is no service available to update");
		
		System.out.println("-".repeat(7)+"ID"+"-".repeat(7)+"NAME"+"-".repeat(7)+"-".repeat(7)+"COST PER DAY "+"-".repeat(7)+"COST PER PERSON");
		for(Service w:s) {
			System.out.println("-".repeat(7)+w.getId()+"-".repeat(7)+w.getName()+"-".repeat(14)+w.getCostPerDay()+"-".repeat(14)+w.getCostPerPerson());                                    
		}
		System.out.println("Select the id to be updated");
		int id=sc.nextInt();
		Service service=serviceDAO.find(id);
		
		if(service==null)System.out.println("No service id present for id "+id);
		
		System.out.println("Enter the cost per day for "+service.getName());
		service.setCostPerDay(sc.nextDouble());
		System.out.println("Enter the cost per person for "+service.getName());
		service.setCostPerPerson(sc.nextDouble());
		s.remove(service);
		s.add(service);
		a.setServices(s);
		serviceDAO.upadte(service, service.getId());
		return service;
		}
		System.out.println("Can't update service");
		return null;
		}
	public Service deleteService() {
		Admin a=adminLogin();
		
		if(a==null) {
			System.out.println("Can't delete the service");
			return null;
		}
		
		List<Service>s=a.getServices();
		System.out.println("-".repeat(7)+"ID"+"-".repeat(7)+"NAME"+"-".repeat(7)+"-".repeat(7)+"COST PER DAY "+"-".repeat(7)+"COST PER PERSON");
		for(Service w:s) {
			System.out.println("-".repeat(7)+w.getId()+"-".repeat(7)+w.getName()+"-".repeat(14)+w.getCostPerDay()+"-".repeat(14)+w.getCostPerPerson());                                    
		}
		System.out.println("Select the id to delete");
		int id=sc.nextInt();
		Service service=serviceDAO.find(id);
		
		if(service==null)System.out.println("No service id present for id "+id);
		a.getServices().remove(service);
		adminDAO.upadte(a, a.getId());
		return serviceDAO.delete(id);
		}
	public Client clientSignUp() {
		System.out.println("CLIENT SIGNUP");
		Client c=new Client();
		System.out.println("Enter the client name");
		c.setName(sc.next());
		System.out.println("Enter the client contact number");
		c.setContact(sc.nextLong());
		System.out.println("Enter the client email");
		c.setEmail(sc.next());
		System.out.println("Enter the  password to set for "+c.getName());
		c.setPassword(sc.next());
		clientDAO.save(c);
		return c;
	}
	public Client clientLogin() {
		System.out.println("CLIENT LOGIN");
		System.out.println("Enter the client email");
		String email=sc.next();
		System.out.println("Enter the client password");
		String password=sc.next();
		Client c=JPQL.getClientByEmail(email);
		if(c.getPassword().equals(password)) {
			System.out.println("Verification success");
			return c;
		}
		System.out.println("Verification failed");
		return null;
		
	}
	public void showService() {
		List<Service>s=JPQL.getServices();
		System.out.println("-".repeat(7)+"ID"+"-".repeat(7)+"NAME"+"-".repeat(7)+"-".repeat(7)+"COST PER DAY "+"-".repeat(7)+"COST PER PERSON");
		for(Service w:s) {
			System.out.println("-".repeat(7)+w.getId()+"-".repeat(7)+w.getName()+"-".repeat(14)+w.getCostPerDay()+"-".repeat(14)+w.getCostPerPerson());                                    
		}
	}
	
	public EventType selectEventTypes() {
		System.out.println("Enter 1 for Marriage");
		System.out.println("Enter 2 for Engagement");
		System.out.println("Enter 3 for Birthday");
		System.out.println("Enter 4 for Anniversary");
		System.out.println("Enter 5 for Baby Shower");
		System.out.println("Enter 6 for Reunion");
		System.out.println("Enter 7 Naming Ceremony");
		System.out.println("Enter 8 for Bachelor's Party");
		
		byte num=sc.nextByte();
		
		switch(num) {
		case 1: return EventType.Marriage;
		case 2: return EventType.Engagement;
		case 3: return EventType.Birthday;
		case 4: return EventType.Anniversary;
		case 5: return EventType.BabyShower;
		case 6: return EventType.Reunoin;
		case 7: return EventType.NamingCeremony;
		case 8: return EventType.BachelorParty;
		
		default : return null;
		}
		
	}
	public LocalDate getLocalDate() {
		System.out.println("Enter the date of service to start");
		byte date=sc.nextByte();
		System.out.println("Enter the month");
		byte month=sc.nextByte();
		
		
		
	return	LocalDate.of(2024, month, date);
		
		
		
	}
	
	public ClientEvent createClientEvent() {
		System.out.println("Login to add the services");
		Client c=clientLogin();
		ClientEvent event=new ClientEvent();
		if(c==null) return  null;
	
	
			else {
				
				System.out.println("SELECT THE EVENT TYPE");
				EventType e=selectEventTypes();
				System.out.println(e);
				if(e==null) {
					System.out.println("Invalid event type is selected");
					return null;
				}
				else {
					System.out.println("-".repeat(15)+"AVAILABLE SERVICES"+"-".repeat(15));
					showService();
					
					System.out.println("select the id to Add the Service");
					int id=sc.nextInt();
					
					Service s=serviceDAO.find(id);
					if(s==null) {
						System.out.println("Invalid Service");
						return null;
					}
					else {
					System.out.println("Enter number of people for the Service : "+s.getName());
					int noOfPeople=sc.nextInt();
					System.out.println("Enter number of days to provide service for Service :"+s.getName());
					int noOfDays=sc.nextInt();
					LocalDate l=getLocalDate();
					System.out.println("Enter the location ");
					String location=sc.next();
					
					
					
					event.setEventType(e);
					event.setClientEventNoOfDays(noOfDays);
					event.setNoOfPeople(noOfPeople);
					event.setClient(c);
					event.setStartDate(l);
					event.setLocation(location);
					System.out.println(event);
			ClientService cs=new ClientService();
			cs.setCostPerPerson(s.getCostPerPerson());
			cs.setDays(noOfDays);
			cs.setName(s.getName());
		event.addClientService(cs);
	
		c.addEvent(event);
		clientDAO.upadte(c, c.getId());
		return event;
			
		}
				}
			}
	}
		


	private ClientService addClientService() {
		Client c=clientLogin();
		
		EventType e=null;
		int ceType=-1;
		ClientEvent ce=null;
		if(c==null) return null;
		else {
		
		System.out.println("The Available events present in client "+c.getName()+" is shown below");
		System.out.println("-".repeat(10)+"ID"+"-".repeat(10)+"EventType"+"-".repeat(10));
		
		for(int i=0;i<c.getEvents().size();i++) {
		int a=i+1;
			System.out.println("-".repeat(10)+c.getEvents().get(i).getId()+"-".repeat(7)+c.getEvents().get(i).getEventType()+"-".repeat(10));
		}
		 ceType=sc.nextInt();
		ce=clientEventDAO.find(ceType);
		if(ce==null) {
			System.out.println("Invalid event to add the service");
			return null;
		}
		else {
			System.out.println("-".repeat(15)+"AVAILABLE SERVICES"+"-".repeat(15));
			showService();
			
			
			System.out.println("select the id to Add the Service");
			int id=sc.nextInt();
			
			Service s=serviceDAO.find(id);
			if(s==null) {
				System.out.println("Invalid Service");
				return null;
			}
			
			else {
			
			System.out.println("Enter number of days to provide service for Service :"+s.getName());
			int noOfDays=sc.nextInt();
			ClientService cs=new ClientService();
			cs.setDays(noOfDays);
			cs.setCostPerPerson(s.getCostPerPerson());
			cs.setName(s.getName());
			ce.addClientService(cs);
			clientEventDAO.upadte(ce, ce.getId());
			return cs;
		}
		
		}

	
}
	}
public ClientService removeClientService() {
	Client c=clientLogin();
	ClientEvent ce=null;
	EventType e=null;
	int ceType;
	if(c!=null) {
		
		System.out.println("The Available events present in client "+c.getName()+" is shown below");
		System.out.println("-".repeat(10)+"ID"+"-".repeat(10)+"EventType"+"-".repeat(10));
		
		for(int i=0;i<c.getEvents().size();i++) {
		int a=i+1;
			System.out.println("-".repeat(10)+c.getEvents().get(i).getId()+"-".repeat(7)+c.getEvents().get(i).getEventType()+"-".repeat(10));
		}
		 ceType=sc.nextInt();
		ce=clientEventDAO.find(ceType);
		if(ce==null) {
			System.out.println("Invalid event to delete the service");
			
		}
		else {
			System.out.println("The Services Present in the selected Events are ");
			System.out.println("-".repeat(15)+"ID"+"-".repeat(15)+"Service"+"-".repeat(15));
			List<ClientService>services=ce.getServices();
			for(ClientService cli:ce.getServices()) {
				System.out.println("-".repeat(15)+cli.getId()+"-".repeat(15)+cli.getName()+"-".repeat(15));
			}
			System.out.println("enter the ID to delete the Service");
			int u=sc.nextInt();
			ClientService clientService=clientServiceDAO.find(u);
			if(clientService==null) {
				System.out.println("Invalid Serevice ID to delete");
				
			}
			else {
				services.remove(clientService);
				ce.setServices(services);
				clientEventDAO.upadte(ce, ce.getId());
				return clientService;
			}
			
		}

	}
	return null;
}
}

















