package logico;

import java.util.ArrayList;

public class Vivienda {

	private ArrayList<Persona> misPersonas;
	private String direccion;
	private int numVivienda;

	public Vivienda(String direccion, int numVivienda) {
		super();
		this.misPersonas = new ArrayList<Persona>();
		this.direccion = direccion;
		this.numVivienda = numVivienda;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumVivienda() {
		return numVivienda;
	}

	public void setNumVivienda(int numVivienda) {
		this.numVivienda = numVivienda;
	}

	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}

	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}

	public void insertarPersona(Persona miPersona) {
		misPersonas.add(miPersona);
	}

	public ArrayList<Persona> getLosHombres() {
		ArrayList<Persona> losHombres = new ArrayList<>();
		for (Persona miPersona : misPersonas) {
			if(miPersona.esMasculino()) {
				losHombres.add(miPersona);
			}
		}
		return losHombres;
	}

	public ArrayList<Persona> getLasMujeres() {
		ArrayList<Persona> lasMujeres = new ArrayList<>();
		for (Persona miPersona : misPersonas) {
			if(miPersona.esFemenino()) {
				lasMujeres.add(miPersona);
			}
		}
		return lasMujeres;
	}
	
}
