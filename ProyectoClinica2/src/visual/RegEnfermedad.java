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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Enfermedad;
import logico.Hospital;

public class RegEnfermedad extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3119794586616135153L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtPrecauciones;
	private JTextField txtProcedimientos;
	private JCheckBox ckbxTos;
	private JCheckBox ckbxFiebre;
	private JCheckBox ckbxDolorCor;
	private JCheckBox ckbxVomitos;
	private JCheckBox ckbxDiarrea;
	private JCheckBox ckbxDolorCab;
	private JCheckBox ckbxNauseas;
	private JCheckBox ckbxMareo;
	private JCheckBox ckbxFatiga;
	private JRadioButton rdbtnVigilancia;
	private JButton btnRegistrar;
	private int contSintomas = 0;
	private JRadioButton rdbtnRojo;
	private JRadioButton rdbtnAmarillo;
	private JRadioButton rdbtnVerde;
	private Enfermedad miEnfermedad = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEnfermedad dialog = new RegEnfermedad(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEnfermedad(Enfermedad laEnfermedad) {
		miEnfermedad = laEnfermedad;
		setResizable(false);
		if (miEnfermedad == null) {
			setTitle("Registrar Enfermedad");
		} else {
			setTitle("Modificar Enfermedad");
		}
		setBounds(100, 100, 457, 510);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 421, 426);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(12, 36, 56, 16);
		panel.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBoton();
			}
		});
		txtNombre.setBounds(64, 33, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Precauciones:");
		lblNewLabel_3.setBounds(12, 188, 122, 16);
		panel.add(lblNewLabel_3);

		{
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u00EDntomas",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 73, 401, 103);
			panel.add(panel_1);
			panel_1.setLayout(null);

			ckbxTos = new JCheckBox("Tos");
			ckbxTos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CambioSintomas(ckbxTos.isSelected());
					habilitarBoton();
				}
			});
			ckbxTos.setBounds(8, 19, 113, 25);
			panel_1.add(ckbxTos);

			ckbxVomitos = new JCheckBox("V\u00F3mitos ");
			ckbxVomitos.setBounds(8, 43, 113, 25);
			panel_1.add(ckbxVomitos);

			ckbxNauseas = new JCheckBox("N\u00E1useas");
			ckbxNauseas.setBounds(8, 69, 113, 25);
			panel_1.add(ckbxNauseas);

			ckbxFiebre = new JCheckBox("Fiebre");
			ckbxFiebre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CambioSintomas(ckbxFiebre.isSelected());
					habilitarBoton();
				}
			});
			ckbxFiebre.setBounds(125, 19, 113, 25);
			panel_1.add(ckbxFiebre);

			ckbxDiarrea = new JCheckBox("Diarrea");
			ckbxDiarrea.setBounds(125, 43, 113, 25);
			panel_1.add(ckbxDiarrea);

			ckbxMareo = new JCheckBox("Mareo");
			ckbxMareo.setBounds(125, 69, 113, 25);
			panel_1.add(ckbxMareo);

			ckbxDolorCor = new JCheckBox("Dolor Corporal");
			ckbxDolorCor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CambioSintomas(ckbxDolorCor.isSelected());
					habilitarBoton();
				}
			});
			ckbxDolorCor.setBounds(248, 19, 113, 25);
			panel_1.add(ckbxDolorCor);

			ckbxDolorCab = new JCheckBox("Dolor de Cabeza");
			ckbxDolorCab.setBounds(248, 43, 130, 25);
			panel_1.add(ckbxDolorCab);

			ckbxFatiga = new JCheckBox("Fatiga");
			ckbxFatiga.setBounds(248, 69, 113, 25);
			panel_1.add(ckbxFatiga);

			txtPrecauciones = new JTextField();
			txtPrecauciones.setBounds(112, 187, 299, 71);
			panel.add(txtPrecauciones);
			txtPrecauciones.setColumns(10);

			JLabel lblNewLabel_2 = new JLabel("Procedimientos:");
			lblNewLabel_2.setBounds(12, 282, 116, 16);
			panel.add(lblNewLabel_2);

			txtProcedimientos = new JTextField();
			txtProcedimientos.setBounds(112, 282, 299, 71);
			panel.add(txtProcedimientos);
			txtProcedimientos.setColumns(10);

			rdbtnVigilancia = new JRadioButton(" Enfermedad en Vigilancia");
			rdbtnVigilancia.setBounds(227, 32, 189, 25);
			panel.add(rdbtnVigilancia);

			rdbtnVerde = new JRadioButton("Verde");
			rdbtnVerde.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnVerde.setSelected(true);
					rdbtnAmarillo.setSelected(false);
					rdbtnRojo.setSelected(false);
				}
			});
			rdbtnVerde.setSelected(true);
			rdbtnVerde.setBounds(341, 375, 70, 23);
			panel.add(rdbtnVerde);

			rdbtnAmarillo = new JRadioButton("Amarillo");
			rdbtnAmarillo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnVerde.setSelected(false);
					rdbtnAmarillo.setSelected(true);
					rdbtnRojo.setSelected(false);
				}
			});
			rdbtnAmarillo.setBounds(216, 375, 84, 23);
			panel.add(rdbtnAmarillo);

			rdbtnRojo = new JRadioButton("Rojo");
			rdbtnRojo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnVerde.setSelected(false);
					rdbtnAmarillo.setSelected(false);
					rdbtnRojo.setSelected(true);
				}
			});
			rdbtnRojo.setBounds(113, 375, 56, 23);
			panel.add(rdbtnRojo);

			JLabel label = new JLabel("Prioridad Triaje:");
			label.setBounds(12, 378, 116, 16);
			panel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				if (miEnfermedad != null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (miEnfermedad == null) {
							Enfermedad laEnfermedad = new Enfermedad("E-" + Hospital.getCodigoEnfermedad(),
									txtNombre.getText(), cargarSintomas(), txtPrecauciones.getText(),
									txtProcedimientos.getText(), rdbtnVigilancia.isSelected(),
									determinarPrioridadTriaje());
							Hospital.getInstance().addEnfermedad(laEnfermedad);
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Registro",
									JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							miEnfermedad.setNombre(txtNombre.getText());
							miEnfermedad.setVigilancia(rdbtnVigilancia.isSelected());
							miEnfermedad.setSintomas(cargarSintomas());
							miEnfermedad.setPrecauciones(txtPrecauciones.getText());
							miEnfermedad.setProcedimientos(txtProcedimientos.getText());
							miEnfermedad.setPrioridadTriaje(determinarPrioridadTriaje());
							Hospital.getInstance().ActualizarEnfermedad(miEnfermedad);
							ListarEnfermedades.loadEnfermedades();
							dispose();
						}
					}
				});
				btnRegistrar.setEnabled(false);
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		loadEnfermedad();
	}

	private void CambioSintomas(boolean selected) {
		if (selected) {
			contSintomas++;
		} else {
			contSintomas--;
		}
	}

	private void habilitarBoton() {
		if (!txtNombre.getText().isEmpty() && contSintomas > 0) {
			btnRegistrar.setEnabled(true);
		} else {
			btnRegistrar.setEnabled(false);
		}
	}

	private String[] cargarSintomas() {
		String[] sintomas = new String[9];
		int i = 0;
		if (ckbxTos.isSelected()) {
			sintomas[i] = ckbxTos.getText();
			i++;
		}

		if (ckbxFiebre.isSelected()) {
			sintomas[i] = ckbxFiebre.getText();
			i++;
		}
		if (ckbxDolorCor.isSelected()) {
			sintomas[i] = ckbxDolorCor.getText();
			i++;
		}
		if (ckbxVomitos.isSelected()) {
			sintomas[i] = ckbxVomitos.getText();
			i++;
		}
		if (ckbxDiarrea.isSelected()) {
			sintomas[i] = ckbxDiarrea.getText();
			i++;
		}
		if (ckbxDolorCab.isSelected()) {
			sintomas[i] = ckbxDolorCab.getText();
			i++;
		}
		if (ckbxNauseas.isSelected()) {
			sintomas[i] = ckbxNauseas.getText();
			i++;
		}
		if (ckbxMareo.isSelected()) {
			sintomas[i] = ckbxMareo.getText();
			i++;
		}
		if (ckbxFatiga.isSelected()) {
			sintomas[i] = ckbxFatiga.getText();
			i++;
		}

		return sintomas;
	}

	private int determinarPrioridadTriaje() {
		if (rdbtnVerde.isSelected()) {
			return 3;
		}
		if (rdbtnAmarillo.isSelected()) {
			return 2;
		}
		if (rdbtnRojo.isSelected()) {
			return 1;
		}
		return 0;
	}

	private void clean() {
		txtNombre.setText("");
		rdbtnVigilancia.setSelected(true);
		txtPrecauciones.setText("");
		txtProcedimientos.setText("");
		rdbtnRojo.setSelected(true);
		desactivarAllCheckBoxes();
		rdbtnAmarillo.setSelected(false);
		rdbtnRojo.setSelected(false);
		rdbtnVerde.setSelected(true);
	}

	private void desactivarAllCheckBoxes() {
		ckbxTos.setSelected(false);
		ckbxFiebre.setSelected(false);
		ckbxDolorCor.setSelected(false);
		ckbxVomitos.setSelected(false);
		ckbxDiarrea.setSelected(false);
		ckbxDolorCab.setSelected(false);
		ckbxNauseas.setSelected(false);
		ckbxMareo.setSelected(false);
		ckbxFatiga.setSelected(false);
	}

	private void loadEnfermedad() {
		if (miEnfermedad != null) {
			rdbtnRojo.setSelected(false);
			rdbtnAmarillo.setSelected(false);
			rdbtnVerde.setSelected(false);
			txtNombre.setText(miEnfermedad.getNombre());
			txtPrecauciones.setText(miEnfermedad.getPrecauciones());
			txtProcedimientos.setText(miEnfermedad.getProcedimientos());
			rdbtnVigilancia.setSelected(miEnfermedad.isVigilancia());
			switch (miEnfermedad.getPrioridadTriaje()) {
			case 1:
				rdbtnRojo.setSelected(true);
				break;
			case 2:
				rdbtnAmarillo.setSelected(true);
				break;
			case 3:
				rdbtnVerde.setSelected(true);
				break;

			default:
				break;
			}
		}
	}
}
