package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Doctor;
import logico.Hospital;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Toolkit;

public class ListarDoctor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7937591526581336190L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblDoctores;
	private JComboBox<Object> cbxEspecialidad;
	private JComboBox<Object> cbxSexo;
	private ArrayList<Doctor> misDoctores = Hospital.getInstance().getMisDoctores();
	private ArrayList<Doctor> doctoresFiltrado = new ArrayList<>();
	private Doctor selectedDoctor = null;
	private static DefaultTableModel model;
	private static Object[] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarDoctor dialog = new ListarDoctor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarDoctor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarDoctor.class.getResource("/Iconos/estetoscopioIcon.png")));
		setTitle("Lista de Doctores");
		setBounds(100, 100, 544, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_Doctores = new JPanel();
			panel_Doctores.setBorder(new TitledBorder(null, "Doctores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_Doctores.setBounds(10, 101, 506, 260);
			contentPanel.add(panel_Doctores);
			panel_Doctores.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 21, 486, 228);
			panel_Doctores.add(scrollPane);
			
			String[] header = {"Id" , "Nombre" , "Cedula" ,"Especialidad"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(header);
			tblDoctores = new JTable();
			tblDoctores.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					habilitarBoton();
					int index = tblDoctores.getSelectedRow();
					if(index > 0)
					{
						selectedDoctor = Hospital.getInstance().buscarDoctorById(tblDoctores.getValueAt(index, 0).toString());
					}
				}
			});
			tblDoctores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tblDoctores);
			tblDoctores.setModel(model);
		}
		
		JPanel panel_Filtros = new JPanel();
		panel_Filtros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Filtros.setBounds(10, 11, 506, 79);
		contentPanel.add(panel_Filtros);
		panel_Filtros.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Especialidad: ");
		lblNewLabel.setBounds(26, 26, 72, 14);
		panel_Filtros.add(lblNewLabel);
		
		String[] especialidades = buscarEspecialidades();
		cbxEspecialidad = new JComboBox<Object>(especialidades);
		cbxEspecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doctoresFiltrado = filtrarDoctores(misDoctores, cbxEspecialidad.getSelectedItem().toString(), cbxSexo.getSelectedItem().toString());
				imprimirDoctores(doctoresFiltrado);
			}
		});
		cbxEspecialidad.setBounds(26, 44, 134, 20);
		panel_Filtros.add(cbxEspecialidad);
		
		JLabel lblNewLabel_1 = new JLabel("Sexo: ");
		lblNewLabel_1.setBounds(186, 26, 46, 14);
		panel_Filtros.add(lblNewLabel_1);
		
		cbxSexo = new JComboBox<Object>();
		cbxSexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doctoresFiltrado = filtrarDoctores(misDoctores, cbxEspecialidad.getSelectedItem().toString(), cbxSexo.getSelectedItem().toString());
				imprimirDoctores(doctoresFiltrado);
			}
		});
		cbxSexo.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Selecione>", "Masculino", "Femenino"}));
		cbxSexo.setBounds(186, 44, 134, 20);
		panel_Filtros.add(cbxSexo);
		
		JButton btnBorrarFiltros = new JButton("Borrar Filtros");
		btnBorrarFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbxEspecialidad.setSelectedIndex(0);
				cbxSexo.setSelectedIndex(0);
				imprimirDoctores(misDoctores);
			}
		});
		btnBorrarFiltros.setBounds(346, 43, 134, 23);
		panel_Filtros.add(btnBorrarFiltros);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		imprimirDoctores(misDoctores);
	}
	
	private void imprimirDoctores(ArrayList<Doctor> doctores)
	{
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Doctor aux : doctores) {
			row[0] = aux.getId();
			row[1] = aux.getNombre();
			row[2] = aux.getCedula();
			row[3] = aux.getEspecialidad();
			model.addRow(row);
		}
	}
	
	private void habilitarBoton()
	{/*
		if(selectedDoctor!= null)
		{
			okButton.setEnabled(true);
		}else {
			okButton.setEnabled(false);
		}
		*/
	}
	private String[] buscarEspecialidades()
	{
		HashSet<String> especialidadSinRepetir = new HashSet<>();
			for (Doctor aux : misDoctores) {
				especialidadSinRepetir.add(aux.getEspecialidad());
			}
		ArrayList<String> tempList = new ArrayList<>();
		tempList.add("<Selecione>");
		tempList.addAll(especialidadSinRepetir);
		
		String[] especialidades = tempList.toArray(new String[0]);
		return especialidades;
	}
	private ArrayList<Doctor> filtrarDoctores(ArrayList<Doctor> doctores, String especialidad , String sexo)
	{
		ArrayList<Doctor> doctoresFiltrados = new ArrayList<>();
		for (Doctor aux : doctores) {
			boolean cumpleEspecialidad = aux.getEspecialidad().equalsIgnoreCase(especialidad);
			boolean cumpleSexo = aux.getSexo().equals(sexo);
			
			if ("<Selecione>".equalsIgnoreCase(especialidad) || cumpleEspecialidad) {
				if ("<Selecione>".equalsIgnoreCase(sexo) || cumpleSexo) {
					doctoresFiltrados.add(aux);
				}
			}
		}
		return doctoresFiltrados;
	}
	
}
