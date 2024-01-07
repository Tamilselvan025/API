package hibernate.dto;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import antlr.collections.List;

public class Main {

	public static void main(String[] args) {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Tamil");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Course c1=new Course();
		c1.setName("Java");
		Course c2=new Course();
		c2.setName("SQL");
		Course c3=new Course();
		c3.setName("Python");
		Course c4=new Course();
		c4.setName("JS");
		
		
		Student s1=new Student();
		s1.setName("Tamil");
		//Student s2=new Student();
//		s2.setName("guna");
//		Student s3=new Student();
//		s3.setName("pravin");
//		
		java.util.List<Course>s=new ArrayList(java.util.List.of(c1,c2,c3,c4));
		s1.setCourses(s);
		
		et.begin();
		em.persist(s1);

		et.commit();
	}

}
