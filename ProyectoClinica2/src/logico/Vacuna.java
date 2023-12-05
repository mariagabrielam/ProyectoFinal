package logico;

import java.util.ArrayList;

public class Vacuna {

	private String lote;
	private String nombre;
	private ArrayList<Enfermedad> misEnfermedad;
	private int cant;

	public Vacuna(String lote, String nombre, ArrayList<Enfermedad> misEnfermedad, int cant) {
		super();
		this.lote = lote;
		this.nombre = nombre;
		this.misEnfermedad = misEnfermedad;
		this.cant = cant;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public ArrayList<Enfermedad> getMisEnfermedad() {
		return misEnfermedad;
	}

	public void setMisEnfermedad(ArrayList<Enfermedad> misEnfermedad) {
		this.misEnfermedad = misEnfermedad;
	}

}
