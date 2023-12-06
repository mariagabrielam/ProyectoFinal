package logico;

public class Empleado extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7746254421760287471L;
	
	protected String id;
	protected String cargo; //Secretario , Doctor , Admin
	
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
	public boolean esSecretario() {
		if(this.cargo.equalsIgnoreCase("Secretario"))
		{
			return true;
		}
		return false;
	}
	public boolean esDoctor() {
		if(this.cargo.equalsIgnoreCase("Doctor"))
		{
			return true;
		}
		return false;
	}
	public boolean esAdmin() {
		if(this.cargo.equalsIgnoreCase("Admin"))
		{
			return true;
		}
		return false;
	}
}
