package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logico.Doctor;
import logico.Empleado;
import logico.Hospital;
import logico.Persona;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;

public class RegDoctor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8041536731931685269L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel panel_General = new JPanel();
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JComboBox<Object> cbxEspecialidad;
	private JFormattedTextField txtCedula;
	private JRadioButton rdbtnF;
	private JRadioButton rdbtnM;
	private JFormattedTextField txtTelefono;
	private JButton okButton;
	private JFormattedTextField txtExequatur;
	private JRadioButton rbtnDoctor;
	private JRadioButton rbtnSecretario;
	private JPanel panelDoctor;
	private Persona miEmpleado = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegDoctor dialog = new RegDoctor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws ParseException
	 */
	public RegDoctor(Persona unEmpleado) throws ParseException {
		miEmpleado = unEmpleado;
		if (miEmpleado == null) // Registrando
		{
			setTitle("Registrar Empleado");
		} else // Modificando
		{
			setTitle("Modificando Empleado");
			rbtnDoctor.setEnabled(false);
			rbtnSecretario.setEnabled(false);
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegDoctor.class.getResource("/Iconos/estetoscopioIcon.png")));
		setBounds(100, 100, 445, 392);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		panel_General.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Generales",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_General.setBounds(12, 13, 408, 126);
		contentPanel.add(panel_General);
		panel_General.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("ID:");
			lblNewLabel.setBounds(12, 29, 56, 16);
			panel_General.add(lblNewLabel);
		}
		{
			txtID = new JTextField();
			txtID.setEditable(false);
			txtID.setBounds(82, 26, 116, 22);
			panel_General.add(txtID);
			txtID.setColumns(10);
			txtID.setText("D-" + Hospital.getCodigoDoctor());
		}
		{
			JLabel lblNewLabel_1 = new JLabel("C\u00E9dula:");
			lblNewLabel_1.setBounds(208, 29, 56, 16);
			panel_General.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setBounds(12, 62, 56, 16);
			panel_General.add(lblNewLabel_2);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					habilitarButton();
				}
			});
			txtNombre.setBounds(82, 56, 116, 22);
			panel_General.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Tel\u00E9fono:");
			lblNewLabel_3.setBounds(208, 61, 56, 16);
			panel_General.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Direcci\u00F3n:");
			lblNewLabel_4.setBounds(12, 98, 65, 16);
			panel_General.add(lblNewLabel_4);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					habilitarButton();
				}
			});
			txtDireccion.setBounds(82, 95, 316, 22);
			panel_General.add(txtDireccion);
			txtDireccion.setColumns(10);
		}
		{
			try {
				MaskFormatter formatter = new MaskFormatter("###-#######-#");
				txtCedula = new JFormattedTextField(formatter);
				txtCedula.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						habilitarButton();
					}
				});
				txtCedula.setBounds(282, 29, 116, 20);
				panel_General.add(txtCedula);
				{
					MaskFormatter formatterTelefono = new MaskFormatter("###-###-####");
					txtTelefono = new JFormattedTextField(formatterTelefono);
					txtTelefono.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							habilitarButton();
						}
					});
					txtTelefono.setBounds(282, 62, 116, 20);
					panel_General.add(txtTelefono);
				}

				MaskFormatter formatterExequatur = new MaskFormatter("##-####");

				JPanel panel_Sexo = new JPanel();
				panel_Sexo
						.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_Sexo.setBounds(12, 143, 408, 51);
				contentPanel.add(panel_Sexo);
				panel_Sexo.setLayout(null);

				rdbtnM = new JRadioButton("Masculino");
				rdbtnM.setBounds(74, 17, 92, 25);
				panel_Sexo.add(rdbtnM);
				rdbtnM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnM.setSelected(true);
						rdbtnF.setSelected(false);
					}
				});
				rdbtnM.setSelected(true);

				rdbtnF = new JRadioButton("Femenino");
				rdbtnF.setBounds(240, 17, 92, 25);
				panel_Sexo.add(rdbtnF);
				{
					panelDoctor = new JPanel();
					panelDoctor.setBorder(
							new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Espec\u00EDficos",
									TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					panelDoctor.setBounds(12, 246, 408, 61);
					contentPanel.add(panelDoctor);
					panelDoctor.setLayout(null);

					JLabel lblNewLabel_6 = new JLabel("Exequatur: ");
					lblNewLabel_6.setBounds(12, 25, 67, 14);
					panelDoctor.add(lblNewLabel_6);
					txtExequatur = new JFormattedTextField(formatterExequatur);
					txtExequatur.setBounds(84, 22, 116, 20);
					panelDoctor.add(txtExequatur);
					{
						JLabel lblNewLabel_5 = new JLabel("Especialidad:");
						lblNewLabel_5.setBounds(206, 24, 79, 16);
						panelDoctor.add(lblNewLabel_5);
					}

					cbxEspecialidad = new JComboBox<Object>();
					cbxEspecialidad.setBounds(284, 22, 116, 22);
					panelDoctor.add(cbxEspecialidad);
					cbxEspecialidad.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							habilitarButton();
						}
					});
					cbxEspecialidad.setModel(new DefaultComboBoxModel<Object>(
							new String[] { "<Seleccione>", "Cardiologo", "Podologo", "Ginecologo" }));
					cbxEspecialidad.setToolTipText("");
					{
						JPanel panel_1 = new JPanel();
						panel_1.setLayout(null);
						panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
								"Tipo de Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
						panel_1.setBounds(12, 193, 408, 51);
						contentPanel.add(panel_1);
						{
							rbtnDoctor = new JRadioButton("Doctor");
							rbtnDoctor.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									rbtnDoctor.setSelected(true);
									rbtnSecretario.setSelected(false);
									panelDoctor.setVisible(true);
									txtID.setText("D-" + Hospital.getCodigoDoctor());
									habilitarButton();
								}
							});
							rbtnDoctor.setSelected(true);
							rbtnDoctor.setBounds(74, 17, 92, 25);
							panel_1.add(rbtnDoctor);
						}
						{
							rbtnSecretario = new JRadioButton("Secretario");
							rbtnSecretario.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									rbtnDoctor.setSelected(false);
									rbtnSecretario.setSelected(true);
									panelDoctor.setVisible(false);
									txtID.setText("S-" + Hospital.getCodigoEmpleado());
									habilitarButton();
								}
							});
							rbtnSecretario.setBounds(240, 17, 92, 25);
							panel_1.add(rbtnSecretario);
						}
					}
					txtExequatur.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							habilitarButton();
						}
					});
				}
				rdbtnF.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnF.setSelected(true);
						rdbtnM.setSelected(false);
					}
				});
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Registar");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (miEmpleado == null) { // Resgitrando
							if (rbtnDoctor.isSelected()) {
								miEmpleado = new Doctor(txtID.getText(), txtCedula.getText(), txtNombre.getText(),
										txtTelefono.getText(), txtDireccion.getText(), determinarSexo(), "Doctor",
										txtExequatur.getText(), cbxEspecialidad.getSelectedItem().toString());
							} else {
								miEmpleado = new Empleado(txtID.getText(), txtCedula.getText(), txtNombre.getText(),
										txtTelefono.getText(), txtDireccion.getText(), determinarSexo(), "Secretario");
							}
							Hospital.getInstance().addPersona(miEmpleado);
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Resgistro",
									JOptionPane.INFORMATION_MESSAGE);
							borrarCampos();
						} 

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

	private String determinarSexo() {
		if (rdbtnF.isSelected()) {
			return "Femenino";
		}
		return "Masculino";
	}

	private void habilitarButton() {
		if (rbtnDoctor.isSelected())
			if (!txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty()
					&& cbxEspecialidad.getSelectedIndex() > 0 && txtCedula.getText().charAt(0) != ' '
					&& txtTelefono.getText().charAt(0) != ' ' && txtExequatur.getText().charAt(0) != ' ') {
				okButton.setEnabled(true);
			} else {
				okButton.setEnabled(false);
			}
		else if (!txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty()
				&& txtCedula.getText().charAt(0) != ' ' && txtTelefono.getText().charAt(0) != ' ') {
			okButton.setEnabled(true);
		} else {
			okButton.setEnabled(false);
		}

	}

	private void borrarCampos() {
		txtCedula.setText("");
		txtDireccion.setText("");
		txtExequatur.setText("");
		txtTelefono.setText("");
		txtNombre.setText("");
		if (rbtnDoctor.isSelected()) {
			txtID.setText("D-" + Hospital.getCodigoDoctor());
		} else {
			txtID.setText("S-" + Hospital.getCodigoEmpleado());
		}
		cbxEspecialidad.setSelectedIndex(0);
	}
}
