package logico;

import java.util.Date;

public class Cita {

	private String id;
	private Persona proxPaciente;
	private Doctor miDoctor;
	private Date fchProgramada;

	public Cita(String id, Persona proxPaciente, Doctor miDoctor, Date fchProgramada) {
		super();
		this.id = id;
		this.proxPaciente = proxPaciente;
		this.miDoctor = miDoctor;
		this.fchProgramada = fchProgramada;
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

}
