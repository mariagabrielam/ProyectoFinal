package logico;

import java.util.Date;

public class Cita {

	private Persona proxPaciente;
	private Doctor miDoctor;
	private Date fchProgramada;

	public Cita(Persona proxPaciente, Doctor miDoctor, Date fchProgramada) {
		super();
		this.proxPaciente = proxPaciente;
		this.miDoctor = miDoctor;
		this.fchProgramada = fchProgramada;
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
