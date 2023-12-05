package logico;

public class Enfermedad {
	private String id;
	private String nombre;
	private String[] sintomas;
	private String[] precauciones;
	private String procedimientos;
	private boolean vigilancia;
	private int prioridadTriaje;

	public Enfermedad(String id, String nombre, String[] sintomas, String[] precauciones, String procedimientos, boolean vigilancia, int prioridadTriaje) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.precauciones = precauciones;
		this.procedimientos = procedimientos;
		this.vigilancia = vigilancia;
		this.setPrioridadTriaje(prioridadTriaje);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String[] getSintomas() {
		return sintomas;
	}
	public void setSintomas(String[] sintomas) {
		this.sintomas = sintomas;
	}
	public String[] getPrecauciones() {
		return precauciones;
	}
	public void setPrecauciones(String[] precauciones) {
		this.precauciones = precauciones;
	}
	public boolean isVigilancia() {
		return vigilancia;
	}
	public void setVigilancia(boolean vigilancia) {
		this.vigilancia = vigilancia;
	}

	public int getPrioridadTriaje() {
		return prioridadTriaje;
	}

	public void setPrioridadTriaje(int prioridadTriaje) {
		this.prioridadTriaje = prioridadTriaje;
	}

	public String getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(String procedimientos) {
		this.procedimientos = procedimientos;
	}
	
	
}
