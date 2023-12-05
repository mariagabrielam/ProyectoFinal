package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class RegEnfermedad extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3119794586616135153L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtPrecauciones;
	private JTextField txtProcedimientos;
	private JCheckBox chbxToz;
	private JCheckBox chbxFiebre;
	private JCheckBox chbxDolor;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JSpinner spnTriaje;
	private JRadioButton rdbtnVigilancia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEnfermedad dialog = new RegEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEnfermedad() {
		setTitle("Registrar Enfermedad");
		setBounds(100, 100, 450, 452);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 408, 344);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Prioridad Triaje:");
		lblNewLabel_5.setBounds(231, 318, 108, 16);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(12, 36, 56, 16);
		panel.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(38, 33, 116, 22);
		panel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(12, 71, 56, 16);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(70, 68, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Precauciones:");
		lblNewLabel_3.setBounds(12, 216, 89, 16);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u00EDntomas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 100, 384, 103);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		chbxToz = new JCheckBox("Toz");
		chbxToz.setBounds(8, 19, 113, 25);
		panel_1.add(chbxToz);
		
		chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(8, 43, 113, 25);
		panel_1.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setBounds(8, 69, 113, 25);
		panel_1.add(chckbxNewCheckBox_1);
		
		chbxFiebre = new JCheckBox("Fiebre");
		chbxFiebre.setBounds(125, 19, 113, 25);
		panel_1.add(chbxFiebre);
		
		checkBox = new JCheckBox("New check box");
		checkBox.setBounds(125, 43, 113, 25);
		panel_1.add(checkBox);
		
		checkBox_2 = new JCheckBox("New check box");
		checkBox_2.setBounds(125, 69, 113, 25);
		panel_1.add(checkBox_2);
		
		chbxDolor = new JCheckBox("Dolor Corporal");
		chbxDolor.setBounds(248, 19, 113, 25);
		panel_1.add(chbxDolor);
		
		checkBox_1 = new JCheckBox("New check box");
		checkBox_1.setBounds(248, 43, 113, 25);
		panel_1.add(checkBox_1);
		
		checkBox_3 = new JCheckBox("New check box");
		checkBox_3.setBounds(248, 69, 113, 25);
		panel_1.add(checkBox_3);
		
		txtPrecauciones = new JTextField();
		txtPrecauciones.setBounds(96, 213, 300, 37);
		panel.add(txtPrecauciones);
		txtPrecauciones.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Procedimientos:");
		lblNewLabel_2.setBounds(12, 263, 92, 16);
		panel.add(lblNewLabel_2);
		
		txtProcedimientos = new JTextField();
		txtProcedimientos.setBounds(116, 259, 280, 46);
		panel.add(txtProcedimientos);
		txtProcedimientos.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Enfermedad en Vigilancia:");
		lblNewLabel_4.setBounds(12, 318, 174, 16);
		panel.add(lblNewLabel_4);
		
		rdbtnVigilancia = new JRadioButton("s\u00ED");
		rdbtnVigilancia.setBounds(176, 314, 127, 25);
		panel.add(rdbtnVigilancia);
		
		spnTriaje = new JSpinner();
		spnTriaje.setBounds(333, 315, 30, 22);
		panel.add(spnTriaje);
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
