package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Hospital;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VacunasPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNHC;
	private JTextField txtNombre;
	private JTable tblHospital;
	private JTable tblPaciente;
	private ArrayList<Vacuna> vacunasHospital;
	private ArrayList<Vacuna> vacunasPaciente;
	private Vacuna selected = null;
	private static DefaultTableModel model;
	private static Object[] row;
	private static DefaultTableModel model1;
	private static Object[] row1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VacunasPaciente dialog = new VacunasPaciente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VacunasPaciente(Paciente paciente) {
		vacunasHospital = Hospital.getInstance().getMisVacunas();
		vacunasPaciente = paciente.getMisVacunas();
		loadVacunasHospital(paciente);
		setTitle("Ingresar Vacunas del Paciente");
		setBounds(100, 100, 537, 439);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 495, 58);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NHC:");
		lblNewLabel.setBounds(12, 24, 56, 16);
		panel.add(lblNewLabel);
		
		txtNHC = new JTextField();
		txtNHC.setEditable(false);
		txtNHC.setText(paciente.getNhc());
		txtNHC.setBounds(51, 21, 160, 22);
		panel.add(txtNHC);
		txtNHC.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(223, 24, 81, 16);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setText(paciente.getNombre());
		txtNombre.setBounds(291, 21, 192, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnMove = new JButton(">>");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMove.getText()==">>")
				{
					vacunasHospital.remove(selected);
					vacunasPaciente.add(selected);
				}
				else
				{
					vacunasHospital.add(selected);
					vacunasPaciente.remove(selected);
				}
			}
		});
		btnMove.setEnabled(false);
		btnMove.setBounds(230, 193, 59, 25);
		contentPanel.add(btnMove);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vacunas Disponibles", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 84, 206, 260);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 26, 182, 221);
		panel_1.add(scrollPane);
		
		tblHospital = new JTable();
		String[] header = {"Nombre"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(header);
		tblHospital.setModel(model);
		tblHospital.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblHospital.getSelectedRow();
				if(index>0)
				{
					btnMove.setEnabled(true);
					btnMove.setText(">>");
					selected = Hospital.getInstance().buscarVacunaByNombre(tblHospital.getValueAt(index, 0).toString());
				}
				
			}
		});
		scrollPane.setViewportView(tblHospital);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Vacunas del Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(301, 84, 206, 260);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 26, 182, 219);
		panel_2.add(scrollPane_1);
		
		tblPaciente = new JTable();
		loadTablas();
		tblPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblPaciente.getSelectedRow();
				if(index>0)
				{
					btnMove.setEnabled(true);
					btnMove.setText("<<");
					selected = Hospital.getInstance().buscarVacunaByNombre(tblPaciente.getValueAt(index, 0).toString());
				}
			}
		});
		tblPaciente.setModel(model1);
		scrollPane_1.setViewportView(tblPaciente);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						paciente.setMisVacunas(vacunasPaciente);
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
	private void loadVacunasHospital(Paciente paciente) {
		if(paciente.getMisVacunas()!=null)
		{
			for(Vacuna aux:paciente.getMisVacunas())
				vacunasHospital.remove(aux);
		}
	}
	private void loadTablas()
	{
		model.setRowCount(0);
		row =new Object[model.getColumnCount()];
		for(Vacuna aux:vacunasHospital)
		{
			row[0] = aux.getNombre();
		}
		model1.setRowCount(0);
		row1 =new Object[model.getColumnCount()];
		for(Vacuna aux:vacunasPaciente)
		{
			row[0] = aux.getNombre();
		}
	}
}
