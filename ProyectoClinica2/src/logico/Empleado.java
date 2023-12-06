package logico;

public abstract class Empleado extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7746254421760287471L;
	
	protected String id;
	protected String cargo;
	
	public Empleado(String id, String cedula, String nombre, String telefono, String direccion, String sexo, String cargo) {
		super(cedula, nombre, telefono, direccion, sexo);
		this.id = id;
		this.cargo = cargo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
