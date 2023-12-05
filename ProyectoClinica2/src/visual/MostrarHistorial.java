package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MostrarHistorial extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8941096458613951699L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtNHC;
	private JTextField txtNombre;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		setTitle("Historial Cl\u00EDnico");
		setBounds(100, 100, 650, 529);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 13, 154, 410);
		contentPanel.add(panel);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(178, 13, 442, 114);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NHC:");
		lblNewLabel.setBounds(12, 35, 56, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(12, 69, 56, 16);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(174, 31, 97, 25);
		panel_1.add(btnNewButton);
		
		txtNHC = new JTextField();
		txtNHC.setBounds(47, 32, 116, 22);
		panel_1.add(txtNHC);
		txtNHC.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(69, 69, 116, 22);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos de la Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(178, 131, 442, 292);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
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
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(29, 204, 384, 75);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(146, 84, 267, 22);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(78, 55, 175, 22);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spinner.setEnabled(false);
		spinner.setBounds(129, 113, 30, 22);
		panel_2.add(spinner);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(81, 144, 116, 22);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel(new Date(1701403200000L), null, null, Calendar.DAY_OF_YEAR));
		spinner_1.setEnabled(false);
		spinner_1.setBounds(129, 26, 127, 22);
		panel_2.add(spinner_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
