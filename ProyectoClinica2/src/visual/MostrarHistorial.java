package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Consulta;
import logico.Hospital;
import logico.Paciente;
import java.awt.Toolkit;

public class MostrarHistorial extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8941096458613951699L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNHC;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtMotivo;
	private JTextField txtDoctor;
	private JTextField txtVacuna;
	private Paciente selected = null;
	private Consulta selectedCon = null;
	private JTable tblConsulta;
	private static DefaultTableModel model;
	private static Object[] row;
	private JScrollPane panHistorial;
	private JSpinner spnDate;
	private JRadioButton rbtnVerde;
	private JRadioButton rbtnAmarillo;
	private JRadioButton rbtnRojo;
	private JButton btnSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarHistorial dialog = new MostrarHistorial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarHistorial() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MostrarHistorial.class.getResource("/Iconos/libroIcono.png")));
		setTitle("Historial Cl\u00EDnico");
		setBounds(100, 100, 810, 529);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(338, 13, 442, 114);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NHC:");
		lblNewLabel.setBounds(12, 35, 56, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(12, 69, 56, 16);
		panel_1.add(lblNewLabel_1);
		
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = Hospital.getInstance().buscarPacienteByNHC(txtNHC.getText());
				loadTablaHistorial();
				panHistorial.setVisible(true);
				txtNHC.setText(selected.getNhc());
				txtNombre.setText(selected.getNombre());
			}
		});
		btnNewButton.setBounds(197, 31, 97, 25);
		panel_1.add(btnNewButton);
		
		txtNHC = new JTextField();
		txtNHC.setBounds(69, 32, 116, 22);
		panel_1.add(txtNHC);
		txtNHC.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(69, 69, 116, 22);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos de la Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(338, 131, 442, 292);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		rbtnVerde = new JRadioButton("Verde");
		rbtnVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prioButtons(3);
			}
		});
		rbtnVerde.setBounds(289, 112, 69, 25);
		panel_2.add(rbtnVerde);
		
		rbtnAmarillo = new JRadioButton("Amarillo");
		rbtnAmarillo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prioButtons(2);
			}
		});
		rbtnAmarillo.setBounds(203, 112, 84, 25);
		panel_2.add(rbtnAmarillo);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha Realizada:");
		lblNewLabel_2.setBounds(29, 29, 116, 16);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Doctor:");
		lblNewLabel_3.setBounds(29, 58, 56, 16);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Motivo de Consulta:");
		lblNewLabel_4.setBounds(29, 87, 116, 16);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Prioridad Triaje:");
		lblNewLabel_5.setBounds(29, 116, 116, 16);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Descripci\u00F3n:");
		lblNewLabel_6.setBounds(29, 175, 84, 16);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Vacuna:");
		lblNewLabel_7.setBounds(29, 145, 56, 16);
		panel_2.add(lblNewLabel_7);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEditable(false);
		txtDescripcion.setBounds(29, 204, 384, 75);
		panel_2.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtMotivo = new JTextField();
		txtMotivo.setEditable(false);
		txtMotivo.setBounds(146, 84, 267, 22);
		panel_2.add(txtMotivo);
		txtMotivo.setColumns(10);
		
		txtDoctor = new JTextField();
		txtDoctor.setEditable(false);
		txtDoctor.setBounds(78, 55, 175, 22);
		panel_2.add(txtDoctor);
		txtDoctor.setColumns(10);
		
		txtVacuna = new JTextField();
		txtVacuna.setEditable(false);
		txtVacuna.setBounds(81, 144, 116, 22);
		panel_2.add(txtVacuna);
		txtVacuna.setColumns(10);
		
		spnDate = new JSpinner();
		spnDate.setModel(new SpinnerDateModel(new Date(1701403200000L), null, null, Calendar.DAY_OF_YEAR));
		spnDate.setEnabled(false);
		spnDate.setBounds(129, 26, 127, 22);
		panel_2.add(spnDate);
		
		rbtnRojo = new JRadioButton("Rojo");
		rbtnRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prioButtons(1);
			}
		});
		rbtnRojo.setBounds(126, 112, 56, 25);
		panel_2.add(rbtnRojo);
		String[] header = {"Código","Fecha","Doctor"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 13, 314, 410);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		panHistorial = new JScrollPane();
		panHistorial.setBounds(12, 24, 290, 373);
		panel.add(panHistorial);
		
		tblConsulta = new JTable();
		tblConsulta.setModel(model);
		tblConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblConsulta.getSelectedRow();
				if(index >0)
				{
					btnSelect.setEnabled(true);
				}
			}
		});
		panHistorial.setViewportView(tblConsulta);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnSelect = new JButton("Seleccionar");
				btnSelect.setEnabled(false);
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int index = tblConsulta.getSelectedRow();
						if(index >0)
						{
							selectedCon = Hospital.getInstance().buscarConsultaById(tblConsulta.getValueAt(index, 0).toString());
							loadHistorial();
						}
					}
				});
				buttonPane.add(btnSelect);
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}

	private void loadTablaHistorial() {
		model.setRowCount(0);
		row =new Object[model.getColumnCount()];
		for(Consulta aux:selected.getHistorial().getMisConsultas())
		{
			row[0] = aux.getId();
			row[1] = aux.getFchConsulta();
			row[2] = aux.getMiDoctor();
			model.addRow(row);
		}
	}
	private void loadHistorial() {
		spnDate.setValue(selectedCon.getFchConsulta());
		txtDoctor.setText(selectedCon.getMiDoctor().getNombre());
		txtMotivo.setText(selectedCon.getTriaje());
		prioButtons(selectedCon.getPrioridadDeTriaje());
		if(selectedCon.getMiVacuna()!=null)
			txtVacuna.setText(selectedCon.getMiVacuna().getNombre());
		else
			txtVacuna.setText("");
		txtDescripcion.setText(selectedCon.getDescripcion());
		
	}
	private void prioButtons(int num)
	{
		rbtnVerde.setSelected(false);
		rbtnRojo.setSelected(false);
		rbtnAmarillo.setSelected(true);
		if(num == 1)
			rbtnRojo.setSelected(true);
		if(num == 2)
			rbtnAmarillo.setSelected(true);
		else
			rbtnVerde.setSelected(false);
	}
}
