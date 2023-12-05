package logico;

import java.util.Date;

public class Consulta {

	private Date fchConsulta;
	private Doctor miDoctor;
	private String descripcion;
	private Enfermedad enfermedad;
	private Vacuna miVacuna;
	private String triaje; // Motivo de consulta
	private int prioridadDeTriaje; // Del 1-3 siendo el 1 de mayor prioridad

	public Consulta(Doctor miDoctor, String descripcion, Enfermedad enfermedad, Vacuna miVacuna, String triaje,
			int prioridadDeTriaje) {
		super();
		fchConsulta = new Date();
		this.miDoctor = miDoctor;
		this.descripcion = descripcion;
		this.enfermedad = enfermedad;
		this.miVacuna = miVacuna;
		this.triaje = triaje;
		this.prioridadDeTriaje = prioridadDeTriaje;
	}

	public Date getFchConsulta() {
		return fchConsulta;
	}

	public void setFchConsulta(Date fchConsulta) {
		this.fchConsulta = fchConsulta;
	}

	public Doctor getMiDoctor() {
		return miDoctor;
	}

	public void setMiDoctor(Doctor miDoctor) {
		this.miDoctor = miDoctor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Vacuna getMiVacuna() {
		return miVacuna;
	}

	public void setMiVacuna(Vacuna miVacuna) {
		this.miVacuna = miVacuna;
	}

	public String getTriaje() {
		return triaje;
	}

	public void setTriaje(String triaje) {
		this.triaje = triaje;
	}

	public int getPrioridadDeTriaje() {
		return prioridadDeTriaje;
	}

	public void setPrioridadDeTriaje(int prioridadDeTriaje) {
		this.prioridadDeTriaje = prioridadDeTriaje;
	}

	public int calcPrioridadTriaje() {
		if (enfermedad.getPrioridadTriaje() == 1) { // Maxima Prioridad
			return 1;
		} else if (enfermedad.getPrioridadTriaje() == 2) { // Maxima Prioridad
			return 2;
		}
		return 3;

	}
}
