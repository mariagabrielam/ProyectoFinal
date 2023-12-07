package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540490196035370563L;
	private String id;
	private Paciente proxPaciente;
	private Doctor miDoctor;
	private Date fchProgramada;
	private boolean estado;

	public Cita(String id, Paciente proxPaciente, Doctor miDoctor, Date fchProgramada) {
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

	public Paciente getProxPaciente() {
		return proxPaciente;
	}

	public void setProxPaciente(Paciente proxPaciente) {
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
