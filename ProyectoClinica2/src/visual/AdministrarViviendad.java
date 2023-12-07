package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Hospital;
import logico.Paciente;
import logico.Persona;
import logico.Vacuna;
import logico.Vivienda;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdministrarViviendad extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6167109698954110585L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNum;
	private JTextField txtDireccion;
	private JTable tblHospital;
	private JTable tblVivienda;
	private ArrayList<Paciente> pacientesHospital;
	private ArrayList<Paciente> pacientesVivienda;
	private Paciente selected = null;
	private Vivienda selectedViv = null;
	private static DefaultTableModel model;
	private static Object[] row;
	private static DefaultTableModel model1;
	private static Object[] row1;
	private JPanel panTablas;
	private JButton btnMove;
	private JButton btnBuscar;
	private JButton btnReg;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdministrarViviendad dialog = new AdministrarViviendad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	 * Create the dialog.
	 */
	public AdministrarViviendad() {
		pacientesHospital = Hospital.getInstance().getMisPacientes();
		loadPacientesHospital();
		setTitle("Administrar Viviendas");
		setBounds(100, 100, 537, 510);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vivienda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 13, 495, 89);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("N\u00FAmero:");
		lblNewLabel.setBounds(12, 24, 56, 16);
		panel.add(lblNewLabel);
		
		txtNum = new JTextField();
		txtNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				panTablas.setVisible(false);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtNum.getText().isEmpty())
					btnBuscar.setEnabled(false);
			}
		});
		txtNum.setBounds(70, 21, 116, 22);
		panel.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_1.setBounds(213, 24, 81, 16);
		panel.add(lblNewLabel_1);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(276, 21, 192, 22);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnBuscar.getText().equalsIgnoreCase("Buscar"))
				{
					if(Hospital.getInstance().buscarViviendaByNumero(txtNum.getText())!=null)
					{
						JOptionPane.showMessageDialog(null, "Vivienda Encontrada", "Busqueda", JOptionPane.INFORMATION_MESSAGE);
						selectedViv = Hospital.getInstance().buscarViviendaByNumero(txtNum.getText());
						txtDireccion.setText(selectedViv.getDireccion());
						panTablas.setVisible(true);
						btnBuscar.setEnabled(false);
						btnReg.setEnabled(false);
						txtNum.setEditable(false);
						pacientesVivienda = selectedViv.getMisPersonas();
						loadTablas();
					}	
					else
					{
						JOptionPane.showMessageDialog(null, "Vivienda No Encontrada", "Busqueda", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnBuscar.setBounds(367, 54, 97, 25);
		panel.add(btnBuscar);
		
		btnReg = new JButton("Registrar Nueva Vivienda\r\n");
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnReg.getText().equalsIgnoreCase("Registrar Nueva Vivienda")) {
					txtNum.setText("V-"+Hospital.getCodigoVivienda());
					txtNum.setEditable(false);
					txtDireccion.setEditable(true);
					btnReg.setText("Agregar Vivienda");
				}
				else
				{
					Vivienda vivienda = new Vivienda(txtDireccion.getText(),txtNum.getText());
					JOptionPane.showMessageDialog(null, "Vivienda Creada", "Registro", JOptionPane.INFORMATION_MESSAGE);
					panTablas.setVisible(true);
					txtDireccion.setEditable(false);
					btnReg.setEnabled(false);
					btnBuscar.setEnabled(false);
					pacientesVivienda = selectedViv.getMisPersonas();
					loadTablas();
				}
			}
		});
		btnReg.setBounds(180, 54, 175, 25);
		panel.add(btnReg);
		String[] header = {"NHC","Nombre"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(header);
		loadTablas();
		
		panTablas = new JPanel();
		panTablas.setBounds(12, 112, 495, 283);
		contentPanel.add(panTablas);
		panTablas.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 13, 206, 260);
		panTablas.add(panel_1);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes Disponibles", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 26, 182, 221);
		panel_1.add(scrollPane);
		
		tblHospital = new JTable();
		tblHospital.setModel(model);
		tblHospital.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblHospital.getSelectedRow();
				if(index>0)
				{
					btnMove.setEnabled(true);
					btnMove.setText(">>");
					selected = Hospital.getInstance().buscarPacienteByNHC(tblHospital.getValueAt(index, 0).toString());
				}
				
			}
		});
		scrollPane.setViewportView(tblHospital);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(289, 13, 206, 260);
		panTablas.add(panel_2);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes en la Vivienda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 26, 182, 219);
		panel_2.add(scrollPane_1);
		
		tblVivienda = new JTable();
		tblVivienda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblVivienda.getSelectedRow();
				if(index>0)
				{
					btnMove.setEnabled(true);
					btnMove.setText("<<");
					selected = Hospital.getInstance().buscarPacienteByNHC(tblVivienda.getValueAt(index, 0).toString());
				}
			}
		});
		tblVivienda.setModel(model1);
		scrollPane_1.setViewportView(tblVivienda);
		
		btnMove = new JButton(">>");
		btnMove.setBounds(218, 114, 59, 25);
		panTablas.add(btnMove);
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMove.getText()==">>")
				{
					pacientesHospital.remove(selected);
					pacientesVivienda.add(selected);
				}
				else
				{
					pacientesHospital.add(selected);
					pacientesVivienda.remove(selected);
				}
			}
		});
		btnMove.setEnabled(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar Cambios");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedViv.setMisPersonas(pacientesVivienda);
						txtNum.setEnabled(true);
						txtNum.setText("");
						txtDireccion.setText("");
						btnReg.setEnabled(true);
						panTablas.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
	}
	private void loadPacientesHospital() {
		for(Paciente aux:getPacientesEnViviendas()){
			pacientesHospital.remove(aux);
		}
	}
	private ArrayList<Paciente> getPacientesEnViviendas() {
		ArrayList<Paciente> losPacientes = new ArrayList<Paciente>();
		for(Vivienda aux:Hospital.getInstance().getMisViviendas())
		{
			for(Paciente auxP:aux.getMisPersonas())
			{
				losPacientes.add(auxP);
			}
				
		}
		return losPacientes;
	}

	private void loadTablas()
	{
		model.setRowCount(0);
		row =new Object[model.getColumnCount()];
		for(Paciente aux:pacientesHospital)
		{
			row[0] = aux.getCedula();
			row[1] = aux.getNombre();
		}
		model1.setRowCount(0);
		row1 =new Object[model1.getColumnCount()];
		for(Paciente aux:pacientesVivienda)
		{
			row1[0] = aux.getCedula();
			row1[0] = aux.getNombre();
		}
	}
}
