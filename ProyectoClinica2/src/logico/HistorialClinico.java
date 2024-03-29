package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class HistorialClinico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8101625344138419162L;
	private String nhc; // Numero de historial clinico
	private Date fchCreacion;
	private ArrayList<Consulta> consultasImportantes; //el doctor decide si son importante -CD

	public HistorialClinico(String nhc) {
		super();
		this.nhc = nhc;
		this.fchCreacion = new Date();
		this.consultasImportantes = new ArrayList<>();
	}

	public ArrayList<Consulta> getMisConsultas() {
		return consultasImportantes;
	}

	public void insertarConsulta(Consulta consulta) {
		consultasImportantes.add(consulta);
	}

	public String getNhc() {
		return nhc;
	}

	public void setNhc(String nhc) {
		this.nhc = nhc;
	}

	public Date getFchCreacion() {
		return fchCreacion;
	}

	public void setFchCreacion(Date fchCreacion) {
		this.fchCreacion = fchCreacion;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.consultasImportantes = misConsultas;
	}

	public boolean tenerEnfermedad(Enfermedad enfermedad) {
		for(Consulta aux:consultasImportantes)
		{
			if(aux.getEnfermedad()==enfermedad)
				return true;
		}
		return false;
	}

}
