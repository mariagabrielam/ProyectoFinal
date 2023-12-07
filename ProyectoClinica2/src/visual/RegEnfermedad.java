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
	private Enfermedad miEnfermedad=null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public RegEnfermedad(Enfermedad laEnfermedad) {
		miEnfermedad=laEnfermedad;
		setResizable(false);
		if(miEnfermedad==null) {
			setTitle("Registrar Enfermedad");
		}else {
			setTitle("Modificar Enfermedad");
		}
		setBounds(100, 100, 450, 478);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 11, 412, 384);
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
		lblNewLabel_3.setBounds(39, 187, 89, 16);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u00EDntomas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 73, 384, 103);
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
		ckbxDolorCab.setBounds(248, 43, 113, 25);
		panel_1.add(ckbxDolorCab);
		
		ckbxFatiga = new JCheckBox("Fatiga");
		ckbxFatiga.setBounds(248, 69, 113, 25);
		panel_1.add(ckbxFatiga);
		
		txtPrecauciones = new JTextField();
		txtPrecauciones.setBounds(112, 187, 284, 61);
		panel.add(txtPrecauciones);
		txtPrecauciones.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Procedimientos:");
		lblNewLabel_2.setBounds(26, 272, 92, 16);
		panel.add(lblNewLabel_2);
		
		txtProcedimientos = new JTextField();
		txtProcedimientos.setBounds(112, 272, 284, 61);
		panel.add(txtProcedimientos);
		txtProcedimientos.setColumns(10);
		
		rdbtnVigilancia = new JRadioButton(" Enfermedad en Vigilancia");
		rdbtnVigilancia.setBounds(227, 32, 169, 25);
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
		rdbtnVerde.setBounds(286, 354, 70, 23);
		panel.add(rdbtnVerde);
		
		rdbtnAmarillo = new JRadioButton("Amarillo");
		rdbtnAmarillo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnVerde.setSelected(false);
				rdbtnAmarillo.setSelected(true);
				rdbtnRojo.setSelected(false);
			}
		});
		rdbtnAmarillo.setBounds(187, 354, 84, 23);
		panel.add(rdbtnAmarillo);
		
		rdbtnRojo = new JRadioButton("Rojo");
		rdbtnRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnVerde.setSelected(false);
				rdbtnAmarillo.setSelected(false);
				rdbtnRojo.setSelected(true);
			}
		});
		rdbtnRojo.setBounds(112, 354, 56, 23);
		panel.add(rdbtnRojo);
		
		JLabel label = new JLabel("Prioridad Triaje:");
		label.setBounds(28, 357, 116, 16);
		panel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				if(miEnfermedad!=null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(miEnfermedad==null) {
							int prioridadTriaje=0;
							if(rdbtnRojo.isSelected())
								prioridadTriaje=1;

							if(rdbtnAmarillo.isSelected())
								prioridadTriaje=2;
							if(rdbtnVerde.isSelected())
								prioridadTriaje=3;
							
							Enfermedad laEnfermedad= new Enfermedad(txtNombre.getText(),cargarSintomas(), txtPrecauciones.getText(), txtProcedimientos.getText(),rdbtnVigilancia.isSelected(), prioridadTriaje);
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
			btnRegistrar.setEnabled(true);
		}else {
			btnRegistrar.setEnabled(false);
		}
	}
	
	private String[] cargarSintomas() {
		String[] sintomas=null;
		if(ckbxTos.isSelected())
			sintomas[0]=ckbxTos.getText();
		if(ckbxFiebre.isSelected())
			sintomas[1]=ckbxFiebre.getText();
		if(ckbxDolorCor.isSelected())
			sintomas[2]=ckbxDolorCor.getText();
		if(ckbxVomitos.isSelected())
			sintomas[3]=ckbxVomitos.getText();
		if(ckbxDiarrea.isSelected())
			sintomas[4]=ckbxDiarrea.getText();
		if(ckbxDolorCab.isSelected())
			sintomas[5]=ckbxDolorCab.getText();
		if(ckbxNauseas.isSelected())
			sintomas[6]=ckbxNauseas.getText();
		if(ckbxMareo.isSelected())
			sintomas[7]=ckbxMareo.getText();
		if(ckbxFatiga.isSelected())
			sintomas[8]=ckbxFatiga.getText();
		
		return sintomas;
	}
	
	private int determinarPrioridadTriaje(String rdbtnTriaje) {
		if(rdbtnTriaje.equalsIgnoreCase("Verde")) {
			return 3;
		}
		if(rdbtnTriaje.equalsIgnoreCase("Amarillo")) {
			return 2;
		}
		if(rdbtnTriaje.equalsIgnoreCase("Rojo")) {
			return 1;
		}
		return 0;
	}
}
