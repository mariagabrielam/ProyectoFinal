package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Enfermedad;

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
	private JCheckBox chbx;
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JRadioButton rdbtnVigilancia;
	private JButton okButton;
	private int contSintomas = 0;

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
	public RegEnfermedad(Enfermedad enfermedad) {
		
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
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(12, 36, 30, 16);
		panel.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(38, 33, 116, 22);
		panel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(166, 36, 56, 16);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBoton();
			}
		});
		txtNombre.setBounds(228, 33, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Precauciones:");
		lblNewLabel_3.setBounds(12, 197, 89, 16);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u00EDntomas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 63, 384, 103);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		chbxToz = new JCheckBox("Tos");
		chbxToz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioSintomas(chbxToz.isSelected());
				habilitarBoton();
			}
		});
		chbxToz.setBounds(8, 19, 113, 25);
		panel_1.add(chbxToz);
		
		chbx = new JCheckBox("New check box");
		chbx.setBounds(8, 43, 113, 25);
		panel_1.add(chbx);
		
		chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setBounds(8, 69, 113, 25);
		panel_1.add(chckbxNewCheckBox_1);
		
		chbxFiebre = new JCheckBox("Fiebre");
		chbxFiebre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioSintomas(chbxFiebre.isSelected());
				habilitarBoton();
			}
		});
		chbxFiebre.setBounds(125, 19, 113, 25);
		panel_1.add(chbxFiebre);
		
		checkBox = new JCheckBox("New check box");
		checkBox.setBounds(125, 43, 113, 25);
		panel_1.add(checkBox);
		
		checkBox_2 = new JCheckBox("New check box");
		checkBox_2.setBounds(125, 69, 113, 25);
		panel_1.add(checkBox_2);
		
		chbxDolor = new JCheckBox("Dolor Corporal");
		chbxDolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioSintomas(chbxDolor.isSelected());
				habilitarBoton();
			}
		});
		chbxDolor.setBounds(248, 19, 113, 25);
		panel_1.add(chbxDolor);
		
		checkBox_1 = new JCheckBox("New check box");
		checkBox_1.setBounds(248, 43, 113, 25);
		panel_1.add(checkBox_1);
		
		checkBox_3 = new JCheckBox("New check box");
		checkBox_3.setBounds(248, 69, 113, 25);
		panel_1.add(checkBox_3);
		
		txtPrecauciones = new JTextField();
		txtPrecauciones.setBounds(96, 197, 300, 37);
		panel.add(txtPrecauciones);
		txtPrecauciones.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Procedimientos:");
		lblNewLabel_2.setBounds(9, 239, 92, 16);
		panel.add(lblNewLabel_2);
		
		txtProcedimientos = new JTextField();
		txtProcedimientos.setBounds(112, 239, 284, 61);
		panel.add(txtProcedimientos);
		txtProcedimientos.setColumns(10);
		
		rdbtnVigilancia = new JRadioButton(" Vigilancia");
		rdbtnVigilancia.setBounds(12, 275, 127, 25);
		panel.add(rdbtnVigilancia);
		
		JRadioButton radioButton = new JRadioButton("Verde");
		radioButton.setSelected(true);
		radioButton.setBounds(274, 303, 70, 23);
		panel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Amarillo");
		radioButton_1.setBounds(186, 303, 84, 23);
		panel.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("Rojo");
		radioButton_2.setBounds(112, 303, 56, 23);
		panel.add(radioButton_2);
		
		JLabel label = new JLabel("Prioridad Triaje:");
		label.setBounds(12, 306, 116, 16);
		panel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
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
	private void CambioSintomas(boolean selected) {
		if(selected) {
			contSintomas++;
		}else {
			contSintomas--;
		}
		
	}

	private void habilitarBoton() {
		if(!txtNombre.getText().isEmpty() && contSintomas>0) {
			okButton.setEnabled(true);
		}else {
			okButton.setEnabled(false);
		}
	}
}
