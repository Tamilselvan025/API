package dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class DAO<T > {
	
	private  T t;
	private Class<T> e;
	private EntityManager em;
	private EntityTransaction et;
	
	{
	em=Persistence.createEntityManagerFactory("Tamil").createEntityManager();
	et=em.getTransaction();
	}
	public DAO(Class<T> type ){
		this.e=type;
	}
	
	public T save(T obj) {
			et.begin();
			em.persist(obj);
			et.commit();
			return obj;
	}
	public T find(int id) {
		return em.find(e, id);
	}
	public T upadte(T updatedObject,int id) {
		 t=find(id);
		if(t!=null) {
			et.begin();
			em.merge(updatedObject);
			et.commit();
			return updatedObject;
		}
		return null;
	}
	public T delete(int id) {
		T t=find(id);
		if(t!=null) {
			et.begin();
			em.remove(t);
			et.commit();
			return t;
		}
		return null;
	}
	
	  
}
