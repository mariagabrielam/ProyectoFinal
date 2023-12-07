package logico;

import java.util.ArrayList;

public class Vivienda {

	private ArrayList<Paciente> misPacientes;
	private String direccion;
	private String numVivienda;

	public Vivienda(String direccion, String numVivienda) {
		super();
		this.misPacientes = new ArrayList<Paciente>();
		this.direccion = direccion;
		this.numVivienda = numVivienda;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumVivienda() {
		return numVivienda;
	}

	public void setNumVivienda(String numVivienda) {
		this.numVivienda = numVivienda;
	}

	public ArrayList<Paciente> getMisPersonas() {
		return misPacientes;
	}

	public void setMisPersonas(ArrayList<Paciente> pacientesVivienda) {
		this.misPacientes = pacientesVivienda;
	}

	public void insertarPersona(Paciente miPersona) {
		misPacientes.add(miPersona);
	}

	public ArrayList<Persona> getLosHombres() {
		ArrayList<Persona> losHombres = new ArrayList<>();
		for (Persona miPersona : misPacientes) {
			if(miPersona.esMasculino()) {
				losHombres.add(miPersona);
			}
		}
		return losHombres;
	}

	public ArrayList<Persona> getLasMujeres() {
		ArrayList<Persona> lasMujeres = new ArrayList<>();
		for (Persona miPersona : misPacientes) {
			if(miPersona.esFemenino()) {
				lasMujeres.add(miPersona);
			}
		}
		return lasMujeres;
	}
	
}
