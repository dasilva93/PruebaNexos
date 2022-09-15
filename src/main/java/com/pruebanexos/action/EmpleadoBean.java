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
import com.pruebanexos.dao.EmpleadoDao;
import com.pruebanexos.entity.Departamento;
import com.pruebanexos.entity.Empleado;

import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class EmpleadoBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private UserManager userManager;
	private List<Departamento> dptoLst;
	private List<Empleado> emplLst;
	private Empleado empl;

	public EmpleadoBean() {
		limpiarEmpl();
		cargaUserManager();
		cargarDptoLst();
		cargarEmplLst();
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

	public void cargarEmplLst() {
		emplLst = new ArrayList<Empleado>();
		emplLst = new EmpleadoDao().getAll(userManager.getEntityManagerFactory());
		// emplLst = new EmpleadoDao().getAllEmplDpto(userManager.getEntityManager());
	}

	public void limpiarEmpl() {
		empl = new Empleado();
		empl.setDepartamentos_id(new Departamento());
	}

	public void selectEmpl(Empleado e) {
		this.empl = e;
	}

	public void crearEmpl() {
		try {
			empl.setFecha_hora_crea(new Date());
			empl.setDepartamentos_id(new DepartamentoDao().getDeptoByid(userManager.getEntityManagerFactory(),
					empl.getDepartamentos_id().getId()));
			new EmpleadoDao().gestionEmpl(userManager.getEntityManagerFactory(), empl);
			cargarEmplLst();
			addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, "Empleado", "Empleado creado correctamente"));
			PrimeFaces.current().executeScript("PF('emplDlg').hide();");
		} catch (Exception e) {
			System.err.println("Error en crearEmpl " + e.toString());
			addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, "Empleado", "Error creando empleado"));
		}
	}

	public void editarEmpl() {
		try {
			empl.setFecha_hora_modifica(new Date());
			empl.setDepartamentos_id(new DepartamentoDao().getDeptoByid(userManager.getEntityManagerFactory(),
					empl.getDepartamentos_id().getId()));
			new EmpleadoDao().editarEmpl(userManager.getEntityManagerFactory(), empl);
			cargarEmplLst();
			addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, "Empleado", "Empleado editado correctamente"));
			PrimeFaces.current().executeScript("PF('emplDlg').hide();");
		} catch (Exception e) {
			System.err.println("Error en editarDpto " + e.toString());
			addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, "Empleado", "Error editando empleado"));
		}
	}

	public void eliminarEmpl() {
		try {
			new EmpleadoDao().eliminarEmpl(userManager.getEntityManagerFactory(), empl);
			cargarEmplLst();
			addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, "Empleado", "Empleado eliminado correctamente"));
			PrimeFaces.current().executeScript("PF('emplDlg').hide();");
		} catch (Exception e) {
			System.err.println("Error en eliminarDpto " + e.toString());
			addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, "Empleado", "Error eliminando empleado"));
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

	public List<Departamento> getDptoLst() {
		return dptoLst;
	}

	public void setDptoLst(List<Departamento> dptoLst) {
		this.dptoLst = dptoLst;
	}

	public List<Empleado> getEmplLst() {
		return emplLst;
	}

	public void setEmplLst(List<Empleado> emplLst) {
		this.emplLst = emplLst;
	}

	public Empleado getEmpl() {
		return empl;
	}

	public void setEmpl(Empleado empl) {
		this.empl = empl;
	}

}
