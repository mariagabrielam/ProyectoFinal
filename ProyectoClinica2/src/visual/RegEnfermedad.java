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
	private JCheckBox chbxToz;
	private JCheckBox chbxFiebre;
	private JCheckBox chbxDolor;
	private JCheckBox chbx;
	private JCheckBox chckbxDiarrea;
	private JCheckBox chckbxDolorDeCabeza;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxMareo;
	private JCheckBox chckbxFaatiga;
	private JRadioButton rdbtnVigilancia;
	private JButton okButton;
	private int contSintomas = 0;

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
		
		chbxToz = new JCheckBox("Tos");
		chbxToz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioSintomas(chbxToz.isSelected());
				habilitarBoton();
			}
		});
		chbxToz.setBounds(8, 19, 113, 25);
		panel_1.add(chbxToz);
		
		chbx = new JCheckBox("V\u00F3mitos ");
		chbx.setBounds(8, 43, 113, 25);
		panel_1.add(chbx);
		
		chckbxNewCheckBox_1 = new JCheckBox("N\u00E1useas");
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
		
		chckbxDiarrea = new JCheckBox("Diarrea");
		chckbxDiarrea.setBounds(125, 43, 113, 25);
		panel_1.add(chckbxDiarrea);
		
		chckbxMareo = new JCheckBox("Mareo");
		chckbxMareo.setBounds(125, 69, 113, 25);
		panel_1.add(chckbxMareo);
		
		chbxDolor = new JCheckBox("Dolor Corporal");
		chbxDolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioSintomas(chbxDolor.isSelected());
				habilitarBoton();
			}
		});
		chbxDolor.setBounds(248, 19, 113, 25);
		panel_1.add(chbxDolor);
		
		chckbxDolorDeCabeza = new JCheckBox("Dolor de cabeza");
		chckbxDolorDeCabeza.setBounds(248, 43, 113, 25);
		panel_1.add(chckbxDolorDeCabeza);
		
		chckbxFaatiga = new JCheckBox("Fatiga");
		chckbxFaatiga.setBounds(248, 69, 113, 25);
		panel_1.add(chckbxFaatiga);
		
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
		
		JRadioButton radioButton = new JRadioButton("Verde");
		radioButton.setSelected(true);
		radioButton.setBounds(286, 354, 70, 23);
		panel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Amarillo");
		radioButton_1.setBounds(187, 354, 84, 23);
		panel.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("Rojo");
		radioButton_2.setBounds(112, 354, 56, 23);
		panel.add(radioButton_2);
		
		JLabel label = new JLabel("Prioridad Triaje:");
		label.setBounds(28, 357, 116, 16);
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
