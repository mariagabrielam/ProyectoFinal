package logico;

public class Doctor extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exequatur; //EXEQUATUR, especialidad
	private String id;
	
	public Doctor(String cedula, String nombre, String telefono, String direccion, String sexo, String id, String exequatur) {
		super(cedula, nombre, telefono, direccion, sexo);
		this.setId(id);
		this.setExequatur(exequatur); 
	}

	public String getExequatur() {
		return exequatur;
	}

	public void setExequatur(String exequatur) {
		this.exequatur = exequatur;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
