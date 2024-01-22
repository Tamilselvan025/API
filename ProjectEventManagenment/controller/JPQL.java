package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import eventmanagement.dto.*;

public class JPQL {
	static EntityManager em=Persistence.createEntityManagerFactory("Tamil").createEntityManager();
	static Query q;
	
	public static Admin getAdminByEmail(String email) {
		 q=em.createQuery("select a from Admin a where a.mail=?1");
		q.setParameter(1, email);
		Admin a=(Admin) q.getSingleResult();
		return a;
	}
	public static List<Service> getServices() {
		 q=em.createQuery("select l from Service l");
		return q.getResultList();
	}
	public static Admin getAdminByServiceId(int id) {
		 q=em.createQuery("select l from Admin l where Service s.id=?1");
		 q.setParameter(1, id);
		 return (Admin)q.getSingleResult();
		 
	}
	public static Client getClientByEmail(String email) {
		 q=em.createQuery("select c from Client c where c.email=?1");
		q.setParameter(1, email);
		Client a=(Client) q.getSingleResult();
		return a;
	}

}
