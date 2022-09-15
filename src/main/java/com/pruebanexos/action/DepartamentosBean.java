package com.pruebanexos.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.pruebanexos.dao.DepartamentoDao;
import com.pruebanexos.entity.Departamento;

import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class DepartamentosBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private UserManager userManager;
	private Departamento dpto;
	private List<Departamento> dptoLst;

	public DepartamentosBean() {
		cargaUserManager();
		cargarDptoLst();
	}

	public void cargaUserManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		userManager = (UserManager) session.getAttribute("userManager");
	}

	public void cargarDptoLst() {
		dptoLst = new ArrayList<Departamento>();
		dptoLst = new DepartamentoDao().getAll(userManager.getEntityManagerFactory());
	}

	public void limpiarDpto() {
		dpto = new Departamento();
	}

	public void crearDpto() {
		try {
			dpto.setFecha_hora_crea(new Date());
			new DepartamentoDao().gestionDpto(userManager.getEntityManagerFactory(), dpto);
			cargarDptoLst();
			addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, "Departamento",
					"Departamento creado correctamente"));
			PrimeFaces.current().executeScript("PF('dptDlg').hide();");
		} catch (Exception e) {
			System.err.println("Error en crearDpto " + e.toString());
			addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, "Departamento", "Error creando departamento"));
		}
	}

	public void editarDpto() {
		try {
			dpto.setFecha_hora_modifica(new Date());
			new DepartamentoDao().editarDpto(userManager.getEntityManagerFactory(), dpto);
			cargarDptoLst();
			addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, "Departamento",
					"Departamento editado correctamente"));
			PrimeFaces.current().executeScript("PF('dptDlg').hide();");
		} catch (Exception e) {
			System.err.println("Error en editarDpto " + e.toString());
			addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, "Departamento", "Error editando departamento"));
		}
	}

	public void eliminarDpto() {
		try {
			new DepartamentoDao().eliminarDpto(userManager.getEntityManagerFactory(), dpto);
			cargarDptoLst();
			addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, "Departamento",
					"Departamento eliminado correctamente"));
			PrimeFaces.current().executeScript("PF('dptDlg').hide();");
		} catch (Exception e) {
			System.err.println("Error en eliminarDpto " + e.toString());
			addMessage(
					createFacesMessage(FacesMessage.SEVERITY_ERROR, "Departamento", "Error eliminando departamento"));
		}
	}

	public FacesMessage createFacesMessage(Severity severity, String summary, String detail) {
		FacesMessage facesMessage = new FacesMessage(severity, summary, detail);
		return facesMessage;
	}

	public void addMessage(FacesMessage facesMessage) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}

	public void selectDpto(Departamento d) {
		this.dpto = d;
	}

	public List<Departamento> getDptoLst() {
		return dptoLst;
	}

	public void setDptoLst(List<Departamento> dptoLst) {
		this.dptoLst = dptoLst;
	}

	public Departamento getDpto() {
		return dpto;
	}

	public void setDpto(Departamento dpto) {
		this.dpto = dpto;
	}

}
