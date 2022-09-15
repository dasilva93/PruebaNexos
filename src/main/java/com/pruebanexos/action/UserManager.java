package com.pruebanexos.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pruebanexos.entity.Departamento;

@Named
@SessionScoped
public class UserManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title = "Prueba técnica - David Silva";	
	private EntityManager entityManager;	
	private EntityManagerFactory entityManagerFactory;	

	public UserManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.getSessionMap().put("userManager", this);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");				
		entityManager = entityManagerFactory.createEntityManager();
		System.out.println("=========== " + entityManager);
		List<Departamento> lst = (List<Departamento>) entityManager.createQuery("FROM Departamento").getResultList();
		System.out.println("lst.size() " + lst.size());
		
		entityManager.getTransaction().begin();
		entityManager.persist(new Departamento("01","Prueba", new Date()));
		entityManager.persist(new Departamento("02","Prueba2", new Date()));
		entityManager.getTransaction().commit();
		
		List<Departamento> lst2 = (List<Departamento>) entityManager.createQuery("FROM Departamento").getResultList();
		System.out.println("lst.size() " + lst2.size());
		for(Departamento d:lst2) {
			System.out.println(d.toString());
		}
		entityManager.close();
	}	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

}
