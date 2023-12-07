package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Hospital implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3806936024224075530L;
	private ArrayList<Persona>misPersonas;
	private ArrayList<HistorialClinico>misHistoriales;
	private ArrayList<Enfermedad>misEnfermedades;
	private static Hospital elHospital=null;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<Vacuna>misVacunas;
	private ArrayList<Consulta>misConsultas;
	private ArrayList<Vivienda>misViviendas;
	private ArrayList<Cita>misCitas;
	private static int codigoDoctor = 1;
	private static int codigoPaciente = 1;
	private static int codigoCita = 1;
	private static int codigoConsulta = 1;
	private static int codigoVacuna = 1;
	private static int codigoVivienda = 1;
	private static Usuario loginUser;
	private static int codigoEmpleado = 1;
	
	public Hospital() {
		super();
		this.misPersonas = new ArrayList<Persona>();
		this.misHistoriales = new ArrayList<HistorialClinico>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misUsuarios = new ArrayList<Usuario>();
		this.misCitas = new ArrayList<>();
		this.misVacunas = new ArrayList<>();
		this.misConsultas = new ArrayList<>();
		this.misViviendas = new ArrayList<>();
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
	
	public ArrayList<Doctor> getMisDoctores() {
		ArrayList<Doctor> misDoctores = new ArrayList<>();
			for (Persona aux : misPersonas) {
				if(aux instanceof Doctor)
				{
					misDoctores.add((Doctor)aux);
				}
			}
		return misDoctores;
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
		return codigoDoctor;
	}

	public static void setCodigoDoctor(int codigoDoctor) {
		Hospital.codigoDoctor = codigoDoctor;
	}

	public static int getCodigoPaciente() {
		return codigoPaciente;
	}

	public static void setCodigoPaciente(int codigoPaciente) {
		Hospital.codigoPaciente = codigoPaciente;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public static int getCodigoCita() {
		return codigoCita;
	}

	public static int getCodigoConsulta() {
		return codigoConsulta;
	}

	public static void setCodigoConsulta(int codigoConsulta) {
		Hospital.codigoConsulta = codigoConsulta;
	}

	public ArrayList<Vivienda> getMisViviendas() {
		return misViviendas;
	}

	public void setMisViviendas(ArrayList<Vivienda> misViviendas) {
		this.misViviendas = misViviendas;
	}

	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}

	public static int getCodigoEmpleado() {
		return codigoEmpleado;
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
	
	public Persona buscarPersonaByCedula(String cedula) {
		for (Persona aux :misPersonas) {
			if (aux instanceof Paciente && aux.getCedula().equalsIgnoreCase(cedula)) {
				return aux;
			}
		}
		return null;
	}

	public void addPersona(Persona aux) {
		misPersonas.add(aux);
		if(aux instanceof Empleado)
		{
			if(((Empleado) aux).esDoctor())
			{
				codigoDoctor++;
			}else
				codigoEmpleado++;
				
		}else {
			codigoPaciente++;
		}
			
		
	}

	public void addUsuario(Usuario aux) {
		misUsuarios.add(aux);
	}
	
	public void addEnfermedad(Enfermedad enfermedad) {
		misEnfermedades.add(enfermedad);
	}
	
	public void addVivienda(Vivienda vivienda) {
		misViviendas.add(vivienda);
		codigoVivienda = getCodigoVivienda() + 1;
	}
	
	public void addCita(Cita cita) {
		misCitas.add(cita);
		codigoCita++;
	}
	
	public void addVacuna(Vacuna vacuna) {
		misVacunas.add(vacuna);
		codigoVacuna = getCodigoVacuna() + 1;
	}
	
	public void addConsulta(Consulta aux) {
		misConsultas.add(aux);
		codigoConsulta++;
		
	}
	
	public void eliminarEnfermedad(Enfermedad selected) {
		misEnfermedades.remove(selected);
	}
	
	public boolean verificarUsuario(String username, String password) {
		for(Usuario aux:misUsuarios) {
			if(aux.getUsername().equals(username) && aux.getPassword().equals(password))
			{
				setLoginUser(aux);
				return true;
			}	
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

	public Enfermedad buscarEnfermedadByNombre(String string) {
		for(Enfermedad aux:misEnfermedades) {
			if(aux.getNombre().equalsIgnoreCase(string))
				return aux;
		}
		return null;
	}
	public Vacuna buscarVacunaByNombre(String string) {
		for(Vacuna aux:misVacunas) {
			if(aux.getNombre().equalsIgnoreCase(string))
				return aux;
		}
		return null;
	}
	public Vacuna buscarVacunaByLote(String string) {
		for(Vacuna aux:misVacunas) {
			if(aux.getLote().equalsIgnoreCase(string))
				return aux;
		}
		return null;
	}

	public Cita buscarCitaById(String id) {
		for(Cita aux:misCitas) {
			if(aux.getId().equalsIgnoreCase(id))
				return aux;
		}
		return null;
	}

	public static int getCodigoVacuna() {
		return codigoVacuna;
	}

	public static int getCodigoVivienda() {
		return codigoVivienda;
	}

	public ArrayList<Paciente> getMisPacientes() {
		ArrayList<Paciente> misPacientes = new ArrayList<>();
		for (Persona aux : misPersonas) {
			if(aux instanceof Paciente)
			{
				misPacientes.add((Paciente)aux);
			}
		}
	return misPacientes;
	}

	public static Usuario getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(Usuario loginUser) {
		Hospital.loginUser = loginUser;
	}

	public Consulta buscarConsultaById(String string) {
		for(Consulta aux:misConsultas) {
			if(aux.getId().equalsIgnoreCase(string))
				return aux;
		}
		return null;
	}

	public void eliminarVacuna(Vacuna selected) {
		misVacunas.remove(selected);
	}

	public void contarDoctor() {
		codigoDoctor++;
		
	}

	public void contarEmpleado() {
		codigoEmpleado++;
	}

	public Vivienda buscarViviendaByNumero(String text) {
		for(Vivienda aux:misViviendas) {
			if(aux.getNumVivienda().equalsIgnoreCase(text))
				return aux;
		}
		return null;
	}  
}