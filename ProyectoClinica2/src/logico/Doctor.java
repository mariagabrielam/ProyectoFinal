package logico;

public class Doctor extends Empleado {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4842368835252259050L;
	private String exequatur; //EXEQUATUR
	private String especialidad;
	
	public Doctor(String id, String cedula, String nombre, String telefono, String direccion, String sexo, String cargo,
			String exequatur, String especialidad) {
		super(id, cedula, nombre, telefono, direccion, sexo, cargo);
		this.exequatur = exequatur;
		this.setEspecialidad(especialidad);
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


	public String getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
}
