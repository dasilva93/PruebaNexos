package com.pruebanexos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "departamento_codigo", nullable = false)
	private String departamento_codigo;

	@Column(name = "departamento_nombre", nullable = false)
	private String departamento_nombre;

	@Column(name = "fecha_hora_crea")
	private Date fecha_hora_crea;

	@Column(name = "fecha_hora_modifica")
	private Date fecha_hora_modifica;

	@OneToMany
	private List<Empleado> empleados;

	public Departamento() {
		empleados = new ArrayList<Empleado>();
	}

	public Departamento(String departamento_codigo, String departamento_nombre, Date fecha_hora_crea) {
		this.departamento_codigo = departamento_codigo;
		this.departamento_nombre = departamento_nombre;
		this.fecha_hora_crea = fecha_hora_crea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartamento_codigo() {
		return departamento_codigo;
	}

	public void setDepartamento_codigo(String departamento_codigo) {
		this.departamento_codigo = departamento_codigo;
	}

	public String getDepartamento_nombre() {
		return departamento_nombre;
	}

	public void setDepartamento_nombre(String departamento_nombre) {
		this.departamento_nombre = departamento_nombre;
	}

	public Date getFecha_hora_crea() {
		return fecha_hora_crea;
	}

	public void setFecha_hora_crea(Date fecha_hora_crea) {
		this.fecha_hora_crea = fecha_hora_crea;
	}

	public Date getFecha_hora_modifica() {
		return fecha_hora_modifica;
	}

	public void setFecha_hora_modifica(Date fecha_hora_modifica) {
		this.fecha_hora_modifica = fecha_hora_modifica;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("id=[" + this.id + "] ");
		string.append("departamento_codigo=[" + this.departamento_codigo + "] ");
		string.append("departamento_nombre=[" + this.departamento_nombre + "] ");
		string.append("fecha_hora_crea=[" + this.fecha_hora_crea + "] ");
		string.append("fecha_hora_modifica=[" + this.fecha_hora_modifica + "] ");
		return string.toString();
	}

}
