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
import logico.Hospital;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegDoctor dialog = new RegDoctor();
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
	public RegDoctor() throws ParseException {
		setTitle("Registrar Doctor");
		setBounds(100, 100, 554, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		panel_General.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_General.setBounds(12, 13, 408, 156);
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
			lblNewLabel_4.setBounds(12, 129, 65, 16);
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
			txtDireccion.setBounds(70, 123, 296, 22);
			panel_General.add(txtDireccion);
			txtDireccion.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Especialidad");
			lblNewLabel_5.setBounds(204, 93, 65, 16);
			panel_General.add(lblNewLabel_5);
		}

		cbxEspecialidad = new JComboBox<Object>();
		cbxEspecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarButton();
			}
		});
		cbxEspecialidad.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "<Seleccione>", "Cardiologo", "Podologo", "Ginecologo" }));
		cbxEspecialidad.setToolTipText("");
		cbxEspecialidad.setBounds(282, 90, 116, 22);
		panel_General.add(cbxEspecialidad);
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

				JLabel lblNewLabel_6 = new JLabel("Exequatur: ");
				lblNewLabel_6.setBounds(10, 94, 67, 14);
				panel_General.add(lblNewLabel_6);

				MaskFormatter formatterExequatur = new MaskFormatter("##-####");
				txtExequatur = new JFormattedTextField(formatterExequatur);
				txtExequatur.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						habilitarButton();
					}
				});
				txtExequatur.setBounds(82, 89, 116, 20);
				panel_General.add(txtExequatur);

				JPanel panel_Sexo = new JPanel();
				panel_Sexo
						.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_Sexo.setBounds(12, 180, 408, 59);
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
						Doctor aux = new Doctor(txtID.getText(), txtCedula.getText(), txtNombre.getText(),
								txtTelefono.getText(), txtDireccion.getText(), determinarSexo(), "Doctor",
								txtExequatur.getText(), cbxEspecialidad.getSelectedItem().toString());
						Hospital.getInstance().addPersona(aux);
						JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Resgistro",
								JOptionPane.INFORMATION_MESSAGE);
						borrarCampos();
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
		if (!txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty()
				&& cbxEspecialidad.getSelectedIndex() > 0 && txtCedula.getText().charAt(0) != ' '
				&& txtTelefono.getText().charAt(0) != ' ' && txtExequatur.getText().charAt(0) != ' ') {
			okButton.setEnabled(true);
		} else {
			okButton.setEnabled(false);
		}

	}
	private void borrarCampos() {
		txtCedula.setText("");
		txtDireccion.setText("");
		txtExequatur.setText("");
		txtID.setText("D-"+Hospital.getCodigoDoctor());
		txtTelefono.setText("");
		txtNombre.setText("");
	}
}
