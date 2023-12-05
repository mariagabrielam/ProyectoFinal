package logico;

import java.io.Serializable;

public abstract class Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String cedula;
	protected String nombre;
	protected String telefono;
	protected String direccion;
	protected String sexo;
	
	public Persona(String cedula, String nombre, String telefono, String direccion, String sexo) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.sexo = sexo;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public boolean esMasculino() {
		if(this.sexo.equalsIgnoreCase("Masculino"))
		{
			return true;
		}
		return false;
	}
	public boolean esFemenino() {
		if(this.sexo.equalsIgnoreCase("Femenino"))
		{
			return true;
		}
		return false;
	}
}
