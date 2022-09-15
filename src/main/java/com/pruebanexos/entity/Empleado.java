package com.pruebanexos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
@NamedQueries({
		@NamedQuery(name = "Empleado.findByName", query = "SELECT a FROM Empleado a WHERE a.nombres = :nombres"),
		@NamedQuery(name = "Empleado.All", query = "SELECT a, b FROM Empleado a, Departamento b where a.departamentos_id = b") })
public class Empleado implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "documento_tipo", nullable = false)
	private String documento_tipo;

	@Column(name = "documento_numero", nullable = false)
	private String documento_numero;

	@Column(name = "nombres", nullable = false)
	private String nombres;

	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	@ManyToOne
	private Departamento departamentos_id;

	@Column(name = "ciudad", nullable = false)
	private String ciudad;

	@Column(name = "dirección", nullable = false)
	private String dirección;

	@Column(name = "correo_electrónico", nullable = false)
	private String correo_electrónico;

	@Column(name = "telefono", nullable = false)
	private String telefono;

	@Column(name = "fecha_hora_crea")
	private Date fecha_hora_crea;

	@Column(name = "fecha_hora_modifica")
	private Date fecha_hora_modifica;

	public Empleado() {
		this.departamentos_id = new Departamento();
	}

	public Empleado(String documento_tipo, String documento_numero, String nombres, String apellidos,
			Departamento departamentos_id, String ciudad, String dirección, String correo_electrónico, String telefono,
			Date fecha_hora_crea) {
		this.documento_tipo = documento_tipo;
		this.documento_numero = documento_numero;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.departamentos_id = departamentos_id;
		this.ciudad = ciudad;
		this.dirección = dirección;
		this.correo_electrónico = correo_electrónico;
		this.telefono = telefono;
		this.fecha_hora_crea = fecha_hora_crea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocumento_tipo() {
		return documento_tipo;
	}

	public void setDocumento_tipo(String documento_tipo) {
		this.documento_tipo = documento_tipo;
	}

	public String getDocumento_numero() {
		return documento_numero;
	}

	public void setDocumento_numero(String documento_numero) {
		this.documento_numero = documento_numero;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Departamento getDepartamentos_id() {
		return departamentos_id;
	}

	public void setDepartamentos_id(Departamento departamentos_id) {
		this.departamentos_id = departamentos_id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDirección() {
		return dirección;
	}

	public void setDirección(String dirección) {
		this.dirección = dirección;
	}

	public String getCorreo_electrónico() {
		return correo_electrónico;
	}

	public void setCorreo_electrónico(String correo_electrónico) {
		this.correo_electrónico = correo_electrónico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("id=[" + this.id + "] ");
		string.append("documento_tipo=[" + this.documento_tipo + "] ");
		string.append("documento_numero=[" + this.documento_numero + "] ");
		string.append("nombres=[" + this.nombres + "] ");
		string.append("apellidos=[" + this.apellidos + "] ");
		string.append("departamentos_id=[" + this.departamentos_id + "] ");
		string.append("ciudad=[" + this.ciudad + "] ");
		string.append("dirección=[" + this.dirección + "] ");
		string.append("correo_electrónico=[" + this.correo_electrónico + "] ");
		string.append("telefono=[" + this.telefono + "] ");
		string.append("fecha_hora_crea=[" + this.fecha_hora_crea + "] ");
		string.append("fecha_hora_modifica=[" + this.fecha_hora_modifica + "] ");
		return string.toString();
	}

}
