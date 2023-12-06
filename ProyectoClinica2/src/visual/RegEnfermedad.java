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
	public RegEnfermedad(Enfermedad enfermedad) {
		
		setTitle("Registrar Enfermedad");
		setBounds(100, 100, 450, 478);
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
		rdbtnVerde.setSelected(true);
		rdbtnVerde.setBounds(286, 354, 70, 23);
		panel.add(rdbtnVerde);
		
		rdbtnAmarillo = new JRadioButton("Amarillo");
		rdbtnAmarillo.setBounds(187, 354, 84, 23);
		panel.add(rdbtnAmarillo);
		
		rdbtnRojo = new JRadioButton("Rojo");
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
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
}
