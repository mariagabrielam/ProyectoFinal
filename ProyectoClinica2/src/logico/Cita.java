package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540490196035370563L;
	private String id;
	private Persona proxPaciente;
	private Doctor miDoctor;
	private Date fchProgramada;
	private boolean estado;

	public Cita(String id, Persona proxPaciente, Doctor miDoctor, Date fchProgramada) {
		super();
		this.id = id;
		this.proxPaciente = proxPaciente;
		this.miDoctor = miDoctor;
		this.fchProgramada = fchProgramada;
		this.estado = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Persona getProxPaciente() {
		return proxPaciente;
	}

	public void setProxPaciente(Persona proxPaciente) {
		this.proxPaciente = proxPaciente;
	}

	public Doctor getMiDoctor() {
		return miDoctor;
	}

	public void setMiDoctor(Doctor miDoctor) {
		this.miDoctor = miDoctor;
	}

	public Date getFchProgramada() {
		return fchProgramada;
	}

	public void setFchProgramada(Date fchProgramada) {
		this.fchProgramada = fchProgramada;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
