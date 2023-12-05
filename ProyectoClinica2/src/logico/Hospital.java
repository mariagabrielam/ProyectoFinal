package logico;

import java.util.ArrayList;

public class Hospital {

	private ArrayList<Persona>misPersonas;
	private ArrayList<HistorialClinico>misHistoriales;
	private ArrayList<Enfermedad>misEnfermedades;
	private static Hospital elHospital=null;
	private ArrayList<Usuario> misUsuarios;
	private static int CodigoDoctor = 1;
	private static int CodigoPaciente = 1;
	
	public Hospital() {
		super();
		this.misPersonas = new ArrayList<Persona>();
		this.misHistoriales = new ArrayList<HistorialClinico>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misUsuarios = new ArrayList<Usuario>();
	}
	
	public static Hospital getInstance() {
		if(elHospital==null) {
			elHospital= new Hospital();
		}
		return elHospital;
	}
	
	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}

	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}

	public ArrayList<HistorialClinico> getMisHistoriales() {
		return misHistoriales;
	}

	public void setMisHistoriales(ArrayList<HistorialClinico> misHistoriales) {
		this.misHistoriales = misHistoriales;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}

	public static Hospital getElHospital() {
		return elHospital;
	}

	public static void setElHospital(Hospital elHospital) {
		Hospital.elHospital = elHospital;
	}
	
	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}

	public static int getCodigoDoctor() {
		return CodigoDoctor;
	}

	public static void setCodigoDoctor(int codigoDoctor) {
		CodigoDoctor = codigoDoctor;
	}

	public static int getCodigoPaciente() {
		return CodigoPaciente;
	}

	public static void setCodigoPaciente(int codigoPaciente) {
		CodigoPaciente = codigoPaciente;
	}

	public Doctor buscarDoctorById(String id) {
		for(Persona aux:misPersonas)
		{
			if(aux instanceof Doctor&&((Doctor)aux).getId().equalsIgnoreCase(id))
				{
					return (Doctor)aux;
				}
		}
		return null;
	}
	
	public Paciente buscarPacienteByNHC(String NHC) {
		for(Persona aux:misPersonas)
		{
			if(aux instanceof Paciente&&((Paciente)aux).getNhc().equalsIgnoreCase(NHC))
				{
					return (Paciente)aux;
				}
		}
		return null;
	}

	public void contarDoctor() {
		CodigoDoctor++;
	}
	public void contarPaciente() {
		CodigoPaciente++;
	}

	public void addPersona(Persona aux) {
		misPersonas.add(aux);
		if(aux instanceof Doctor)
			contarDoctor();
		else
			contarPaciente();
		
	}

	public void addUsuario(Usuario aux) {
		misUsuarios.add(aux);
	}

	public boolean verificarUsuario(String username, String password) {
		for(Usuario aux:misUsuarios) {
			if(aux.getUsername().equalsIgnoreCase(username)&&aux.getPassword().equals(password))
				return true;
		}
		return false;
		
	}

	public Usuario buscarUsuarioByName(String username) {
		for(Usuario aux:misUsuarios) {
			if(aux.getUsername().equalsIgnoreCase(username))
				return aux;
		}
		return null;
	}
}
