package logico;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Persona persona;
	private String tipo;
	
	public Usuario(String username, String password, Persona persona, String tipo) {
		super();
		this.username = username;
		this.password = password;
		this.persona = persona;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
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
	
	public boolean esAdmin() {
		if(this.tipo == "Administrador")
		{
			return true;
		}
		return false;
	}
	
	public boolean esBasic() {
		if(this.tipo == "Basico")
		{
			return true;
		}
		return false;
	}
	
	public boolean esPrivilegiado() {
		if(this.tipo == "Privilegiado")
		{
			return true;
		}
		return false;
	}
	
}
