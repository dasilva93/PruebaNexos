package com.pruebanexos.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.pruebanexos.entity.Empleado;

public class EmpleadoDao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public List<Empleado> getAll(EntityManagerFactory entityManagerFactory) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			List<Empleado> es = (List<Empleado>) entityManager.createQuery("FROM Empleado", Empleado.class)
					.getResultList();
			entityManager.close();
			return es;
		} catch (Exception e) {
			return new ArrayList<Empleado>();
		}
	}

	public List<Empleado> getAllEmplDpto(EntityManagerFactory entityManagerFactory) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			return (List<Empleado>) entityManager
					.createQuery("SELECT e FROM Empleado e JOIN FETCH e.departamentos_id", Empleado.class)
					.getResultList();
		} catch (Exception e) {
			return new ArrayList<Empleado>();
		}
	}

	public void gestionEmpl(EntityManagerFactory entityManagerFactory, Empleado empl) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(empl);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void editarEmpl(EntityManagerFactory entityManagerFactory, Empleado empl) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(empl);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void eliminarEmpl(EntityManagerFactory entityManagerFactory, Empleado empl) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.contains(empl) ? empl : entityManager.merge(empl));
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
