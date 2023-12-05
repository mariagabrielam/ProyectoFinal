package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cita;
import logico.Consulta;
import logico.Doctor;
import logico.Enfermedad;
import logico.Hospital;
import logico.Paciente;
import logico.Persona;
import logico.Vacuna;

public class RegConsulta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5167795281497040413L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNHC;
	private JTextField txtNombre;
	private JTextField txtMotivo;
	private JTextField txtPeso;
	private JTextField txtEstatura;
	private JRadioButton rbtnYes;
	private JRadioButton rbtnNo;
	private JRadioButton rbtnRojo;
	private JRadioButton rbtnAmarillo;
	private JRadioButton rbtnVerde;
	private JComboBox<Object> cbxSangre;
	private JSpinner spnFchConsulta;
	private JButton okButton;
	private JTextArea txtDescripcion;
	private JComboBox<Object> cbxVacuna;
	private JComboBox<Object> cbxEnfermedades;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta(Cita cita) {
		load(cita.getProxPaciente());
		setTitle("Registrar Consulta");
		setBounds(100, 100, 456, 562);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 11, 423, 132);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("NHC:");
				label.setBounds(12, 35, 56, 16);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setBounds(180, 35, 56, 16);
				panel.add(label);
			}
			{
				txtNHC = new JTextField();
				txtNHC.setEditable(false);
				txtNHC.setColumns(10);
				txtNHC.setBounds(52, 32, 116, 22);
				panel.add(txtNHC);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEnabled(false);
				txtNombre.setColumns(10);
				txtNombre.setBounds(235, 32, 176, 22);
				panel.add(txtNombre);
			}

			JLabel lblNewLabel_1 = new JLabel("Peso: ");
			lblNewLabel_1.setBounds(12, 66, 46, 14);
			panel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("Estatura: ");
			lblNewLabel_2.setBounds(176, 66, 60, 14);
			panel.add(lblNewLabel_2);

			txtPeso = new JTextField();
			txtPeso.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					habilitarButton();
				}

				public void keyTyped(KeyEvent e) {
					char caracter = e.getKeyChar();
					if ((caracter < '0' || caracter > '9') && (caracter != KeyEvent.VK_BACK_SPACE)
							&& (caracter != '.' || txtPeso.getText().contains("."))) {
						e.consume();
					}
				}
			});
			txtPeso.setBounds(52, 62, 116, 20);
			panel.add(txtPeso);
			txtPeso.setColumns(10);

			txtEstatura = new JTextField();
			txtEstatura.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
				}

				public void keyTyped(KeyEvent e) {
					char caracter = e.getKeyChar();
					if ((caracter < '0' || caracter > '9') && (caracter != KeyEvent.VK_BACK_SPACE)
							&& (caracter != '.' || txtEstatura.getText().contains("."))) {
						e.consume();
					}

				}
			});
			txtEstatura.setBounds(235, 63, 176, 20);
			panel.add(txtEstatura);
			txtEstatura.setColumns(10);
			{
				JLabel lblNewLabel_3 = new JLabel("Tipo de Sangre: ");
				lblNewLabel_3.setBounds(12, 93, 116, 14);
				panel.add(lblNewLabel_3);
			}
			
			cbxSangre = new JComboBox<Object>();
			cbxSangre.setEnabled(false);
			cbxSangre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarButton();
				}
			});
			cbxSangre.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Seleccione>", "AB+", "AB-", "A+", "A-", "B+", "B-", "O+", "O-"}));
			cbxSangre.setBounds(120, 90, 127, 20);
			panel.add(cbxSangre);
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(
					new TitledBorder(null, "Datos de la Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 146, 423, 321);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Fecha Realizada:");
				label.setBounds(29, 47, 116, 16);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Motivo de Consulta:");
				label.setBounds(29, 76, 116, 16);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Prioridad Triaje:");
				label.setBounds(29, 155, 116, 16);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Descripci\u00F3n:");
				label.setBounds(29, 176, 84, 16);
				panel.add(label);
			}
			{
				txtMotivo = new JTextField();
				txtMotivo.setColumns(10);
				txtMotivo.setBounds(146, 73, 267, 22);
				panel.add(txtMotivo);
			}
			{
				spnFchConsulta = new JSpinner();
				spnFchConsulta.setEnabled(false);
				spnFchConsulta.setModel(new SpinnerDateModel(new Date(1701403200000L), null, null, Calendar.DAY_OF_YEAR));
				spnFchConsulta.setBounds(129, 44, 127, 22);
				panel.add(spnFchConsulta);
			}
			{
				JLabel lblNewLabel = new JLabel("\u00BFFue importante la consulta?");
				lblNewLabel.setBounds(29, 282, 175, 16);
				panel.add(lblNewLabel);
			}
			{
				rbtnNo = new JRadioButton("no");
				rbtnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rbtnYes.setEnabled(false);
						rbtnNo.setEnabled(true);
					}
				});
				rbtnNo.setBounds(247, 278, 48, 25);
				panel.add(rbtnNo);
			}

			rbtnYes = new JRadioButton("s\u00ED");
			rbtnYes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnYes.setEnabled(true);
					rbtnNo.setEnabled(false);
				}
			});
			rbtnYes.setSelected(true);
			rbtnYes.setBounds(203, 278, 41, 25);
			panel.add(rbtnYes);

			rbtnRojo = new JRadioButton("Rojo");
			rbtnRojo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnRojo.setEnabled(true);
					rbtnAmarillo.setSelected(false);
					rbtnVerde.setSelected(false);
				}
			});
			rbtnRojo.setBounds(129, 152, 56, 23);
			panel.add(rbtnRojo);

			rbtnAmarillo = new JRadioButton("Amarillo");
			rbtnAmarillo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnAmarillo.setEnabled(true);
					rbtnVerde.setSelected(false);
					rbtnRojo.setSelected(false);
				}
			});
			rbtnAmarillo.setBounds(203, 152, 84, 23);
			panel.add(rbtnAmarillo);

			rbtnVerde = new JRadioButton("Verde");
			rbtnVerde.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnVerde.setEnabled(true);
					rbtnAmarillo.setSelected(false);
					rbtnRojo.setSelected(false);
				}
			});
			rbtnVerde.setSelected(true);
			rbtnVerde.setBounds(291, 152, 70, 23);
			panel.add(rbtnVerde);

			txtDescripcion = new JTextArea();
			txtDescripcion.setBounds(29, 195, 384, 80);
			panel.add(txtDescripcion);
			{
				JLabel lblNewLabel_4 = new JLabel("Vacuna:");
				lblNewLabel_4.setBounds(29, 103, 56, 16);
				panel.add(lblNewLabel_4);
			}
			{
				cbxVacuna = new JComboBox<Object>();
				cbxVacuna.setModel(new DefaultComboBoxModel<Object>(loadVacunas()));
				cbxVacuna.setBounds(88, 100, 116, 22);
				panel.add(cbxVacuna);
			}
			{
				JLabel lblEnfermedad = new JLabel("Enfermedad:");
				lblEnfermedad.setBounds(29, 129, 84, 16);
				panel.add(lblEnfermedad);
			}
			{
				cbxEnfermedades = new JComboBox<Object>();
				cbxEnfermedades.setModel(new DefaultComboBoxModel<Object>(loadEnfermedades()));
				cbxEnfermedades.setBounds(110, 126, 116, 22);
				panel.add(cbxEnfermedades);
			}
			
			JLabel lblNewLabel_5 = new JLabel("C\u00F3digo:");
			lblNewLabel_5.setBounds(29, 23, 56, 16);
			panel.add(lblNewLabel_5);
			
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setBounds(88, 20, 116, 22);
			panel.add(txtId);
			txtId.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int prio = 1;
						if(rbtnVerde.isEnabled())
							prio = 3;
						else if(rbtnAmarillo.isEnabled())
							prio = 2;
						Consulta consulta = new Consulta(txtId.getText(),cita.getMiDoctor(),txtDescripcion.getText(),Hospital.getInstance().buscarEnfermedadByNombre(cbxEnfermedades.getSelectedItem().toString()),Hospital.getInstance().buscarVacunaByNombre(cbxVacuna.getSelectedItem().toString()),txtMotivo.getText(),prio);
						Hospital.getInstance().addConsulta(consulta);
						if(((Paciente) cita.getProxPaciente()).getNhc()!=null)
						{
							Paciente paciente = new Paciente(cita.getProxPaciente(),txtNHC.getText(),cbxSangre.getSelectedItem().toString(),Float.valueOf(txtPeso.getText()),Float.valueOf(txtEstatura.getText()));
							if(rbtnYes.isEnabled())
								paciente.getHistorial().getMisConsultas().add(consulta);
						}
						else if(rbtnYes.isEnabled())
							((Paciente)cita.getProxPaciente()).getHistorial().getMisConsultas().add(consulta);
					}
				});
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

	private Object[] loadVacunas() {
		String[] vacunas=null;
		vacunas[0] = "<Seleccione>";
		int i=1;
		for(Vacuna aux:Hospital.getInstance().getMisVacunas())
		{
			vacunas[i] = aux.getNombre();
			i++;
		}
		return vacunas;
	}
	private Object[] loadEnfermedades() {
		String[] enfermedades=null;
		enfermedades[0] = "<Seleccione>";
		int i=1;
		for(Enfermedad aux:Hospital.getInstance().getMisEnfermedades())
		{
			enfermedades[i] = aux.getNombre();
			i++;
		}
		return enfermedades;
	}

	private void habilitarButton() {
		if(!txtPeso.getText().isEmpty()&&!txtEstatura.getText().isEmpty()&&!txtMotivo.getText().isEmpty() && cbxSangre.getSelectedIndex()>0)
			okButton.setEnabled(true);
		
	}
	private void load(Persona paciente) {
		txtId.setText("Con-"+Hospital.getCodigoConsulta());
		txtNombre.setText(paciente.getNombre());
		if(((Paciente) paciente).getNhc()!=null) {
			txtNHC.setText(((Paciente) paciente).getNhc());
			txtPeso.setText(String.valueOf(((Paciente) paciente).getPeso()));
			txtEstatura.setText(String.valueOf(((Paciente) paciente).getEstatura()));
			cbxSangre.setSelectedIndex(sangreIndex((Paciente)paciente));
		}
		else {
			cbxSangre.setEnabled(true);
			txtNHC.setText("NHC-"+Hospital.getCodigoPaciente());
		}
	}

	private int sangreIndex(Paciente paciente) {
		if(paciente.esSangreABPositivo())
			return 1;
		if(paciente.esSangreABNegativo())
			return 2;
		if(paciente.esSangreAPositivo())
			return 3;
		if(paciente.esSangreANegativo())
			return 4;
		if(paciente.esSangreBPositivo())
			return 5;
		if(paciente.esSangreBNegativo())
			return 6;
		if(paciente.esSangreOPositivo())
			return 7;
		if(paciente.esSangreONegativo())
			return 8;
		return 0;
	}
}
