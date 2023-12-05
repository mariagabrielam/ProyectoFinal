package logico;

import java.util.ArrayList;

public class Paciente extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HistorialClinico historial;
	private String tipoSangre;
	private float peso;
	private float estatura;
	private ArrayList<Vacuna> misVacunas;

	public Paciente(String nhc, String tipoSangre, float peso, float estatura, Persona miPersona, ArrayList<Vacuna> misVacunas) {
		super(miPersona.getCedula(), miPersona.getNombre(), miPersona.getTelefono(), miPersona.getDireccion(),
				miPersona.getSexo());
		this.historial = new HistorialClinico(nhc);
		this.tipoSangre = tipoSangre;
		this.peso = peso;
		this.estatura = estatura;
		this.setMisVacunas(misVacunas);
	}

	public HistorialClinico getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialClinico historial) {
		this.historial = historial;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}
	
	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	public String getNhc() {
		return historial.getNhc();
	}

	public boolean esSangreABPositivo() {
		if (this.tipoSangre.equalsIgnoreCase("AB+")) {
			return true;
		}
		return false;
	}

	public boolean esSangreABNegativo() {
		if (this.tipoSangre.equalsIgnoreCase("AB-")) {
			return true;
		}
		return false;
	}

	public boolean esSangreAPositivo() {
		if (this.tipoSangre.equalsIgnoreCase("A+")) {
			return true;
		}
		return false;
	}

	public boolean esSangreANegativo() {
		if (this.tipoSangre.equalsIgnoreCase("A-")) {
			return true;
		}
		return false;
	}

	public boolean esSangreBPositivo() {
		if (this.tipoSangre.equalsIgnoreCase("B+")) {
			return true;
		}
		return false;
	}

	public boolean esSangreBNegativo() {
		if (this.tipoSangre.equalsIgnoreCase("B-")) {
			return true;
		}
		return false;
	}

	public boolean esSangreOPositivo() {
		if (this.tipoSangre.equalsIgnoreCase("O+")) {
			return true;
		}
		return false;
	}

	public boolean esSangreONegativo() {
		if (this.tipoSangre.equalsIgnoreCase("O-")) {
			return true;
		}
		return false;
	}

}
