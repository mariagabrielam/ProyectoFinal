package logico;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -817185747685472215L;
	private String username;
	private String password;
	private Empleado empleado;
	private String tipo;
	
	public Usuario(String username, String password, Empleado empleado, String tipo) {
		super();
		this.username = username;
		this.password = password;
		this.empleado = empleado;
		this.setTipo(tipo);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setAdmin() {
		this.tipo = "Administrador";
	}
	
	public void setBasic() {
		this.tipo = "Basico";
	}
	
	public void setPrivilegio() {
		this.tipo = "Privilegiado";
	}
	
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public boolean esAdmin() {
		if(this.tipo.equalsIgnoreCase("Administrador"))
		{
			return true;
		}
		return false;
	}
	
	public boolean esBasic() {
		if(this.tipo.equalsIgnoreCase("Basico"))
		{
			return true;
		}
		return false;
	}
	
	public boolean esPrivilegiado() {
		if(this.tipo.equalsIgnoreCase("Privilegiado"))
		{
			return true;
		}
		return false;
	}
	
}
