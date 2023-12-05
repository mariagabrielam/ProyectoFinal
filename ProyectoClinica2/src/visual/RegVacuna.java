package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RegVacuna extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460001246198462049L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtLote;
	private JTextField txtNombre;
	private JTable table_allEnfermedades;
	private JTable table_enfermedadesVacuna;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacuna dialog = new RegVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVacuna() {
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 515, 467);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 479, 121);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lote:");
		lblNewLabel.setBounds(40, 32, 46, 14);
		panel.add(lblNewLabel);
		
		txtLote = new JTextField();
		txtLote.setBounds(80, 29, 86, 20);
		panel.add(txtLote);
		txtLote.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(24, 82, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(80, 79, 187, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad de Vacuna:");
		lblNewLabel_2.setBounds(272, 32, 122, 14);
		panel.add(lblNewLabel_2);
		
		JSpinner spnCantVacuna = new JSpinner();
		spnCantVacuna.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spnCantVacuna.setBounds(389, 29, 68, 20);
		panel.add(spnCantVacuna);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 143, 479, 243);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(19, 49, 167, 155);
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table_allEnfermedades = new JTable();
		scrollPane.setViewportView(table_allEnfermedades);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(289, 49, 167, 155);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		table_enfermedadesVacuna = new JTable();
		String[] header= {"Nombre"};
		
		scrollPane_1.setViewportView(table_enfermedadesVacuna);
		
		JButton btnNewButton = new JButton(">>");
		btnNewButton.setBounds(196, 99, 83, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<<");
		btnNewButton_1.setBounds(196, 133, 83, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Enfermedades:");
		lblNewLabel_3.setBounds(19, 22, 105, 16);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Enfermedad/es Vacuna:");
		lblNewLabel_4.setBounds(292, 23, 144, 14);
		panel_1.add(lblNewLabel_4);
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
