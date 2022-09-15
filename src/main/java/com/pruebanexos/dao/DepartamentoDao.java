package com.pruebanexos.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.pruebanexos.entity.Departamento;

public class DepartamentoDao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public List<Departamento> getAll(EntityManagerFactory entityManagerFactory) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			List<Departamento> dp = (List<Departamento>) entityManager
					.createQuery("FROM Departamento", Departamento.class).getResultList();
			entityManager.close();
			return dp;
		} catch (Exception e) {
			return new ArrayList<Departamento>();
		}
	}

	public Departamento getDeptoByid(EntityManagerFactory entityManagerFactory, int id) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Departamento d = entityManager.find(Departamento.class, id);
			entityManager.close();
			return d;
		} catch (Exception e) {
			return null;
		}
	}

	public void gestionDpto(EntityManagerFactory entityManagerFactory, Departamento dpto) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(dpto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void editarDpto(EntityManagerFactory entityManagerFactory, Departamento dpto) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(dpto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void eliminarDpto(EntityManagerFactory entityManagerFactory, Departamento dpto) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.contains(dpto) ? dpto : entityManager.merge(dpto));
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
