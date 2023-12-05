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
	private final JPanel panel = new JPanel();
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JComboBox<Object> cbxExeq;
	private JFormattedTextField txtCedula;
	private JRadioButton rdbtnF;
	private JRadioButton rdbtnM;
	private JFormattedTextField txtTelefono;
	private JButton okButton;

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
	 * @throws ParseException 
	 */
	public RegDoctor() throws ParseException {
		setTitle("Registrar Doctor");
		setBounds(100, 100, 450, 257);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 408, 156);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		rdbtnF = new JRadioButton("F");
		rdbtnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnF.setSelected(true);
				rdbtnM.setSelected(false);
			}
		});
		rdbtnF.setBounds(273, 115, 40, 25);
		panel.add(rdbtnF);
		{
			JLabel lblNewLabel = new JLabel("ID:");
			lblNewLabel.setBounds(12, 29, 56, 16);
			panel.add(lblNewLabel);
		}
		{
			txtID = new JTextField();
			txtID.setEditable(false);
			txtID.setBounds(38, 26, 116, 22);
			panel.add(txtID);
			txtID.setColumns(10);
			txtID.setText("D-"+Hospital.getCodigoDoctor());
		}
		{
			JLabel lblNewLabel_1 = new JLabel("C\u00E9dula:");
			lblNewLabel_1.setBounds(184, 29, 56, 16);
			panel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setBounds(12, 62, 56, 16);
			panel.add(lblNewLabel_2);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					habilitarButton();
				}
			});
			txtNombre.setBounds(73, 59, 116, 22);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Tel\u00E9fono:");
			lblNewLabel_3.setBounds(196, 61, 56, 16);
			panel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Direcci\u00F3n:");
			lblNewLabel_4.setBounds(12, 92, 65, 16);
			panel.add(lblNewLabel_4);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					habilitarButton();
				}
			});
			txtDireccion.setBounds(73, 89, 116, 22);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Exequatur:");
			lblNewLabel_5.setBounds(12, 121, 65, 16);
			panel.add(lblNewLabel_5);
		}
		
		cbxExeq = new JComboBox<Object>();
		cbxExeq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarButton();
			}
		});
		cbxExeq.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Seleccione>", "Cardiologo", "Podologo"}));
		cbxExeq.setToolTipText("");
		cbxExeq.setBounds(83, 118, 106, 22);
		panel.add(cbxExeq);
		
		JLabel lblNewLabel_6 = new JLabel("Sexo:");
		lblNewLabel_6.setBounds(235, 90, 56, 16);
		panel.add(lblNewLabel_6);
		
		rdbtnM = new JRadioButton("M");
		rdbtnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnM.setSelected(true);
				rdbtnF.setSelected(false);
			}
		});
		rdbtnM.setSelected(true);
		rdbtnM.setBounds(224, 115, 40, 25);
		panel.add(rdbtnM);
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
			txtCedula.setBounds(250, 27, 116, 20);
			panel.add(txtCedula);
			{
				MaskFormatter formatterTelefono = new MaskFormatter("###-###-####");
				txtTelefono = new JFormattedTextField(formatterTelefono);
				txtTelefono.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						habilitarButton();
					}
				});
				txtTelefono.setBounds(250, 60, 116, 20);
				panel.add(txtTelefono);
			}
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
						if(cbxExeq.getSelectedIndex()!=0) {
							Doctor aux = new Doctor(txtCedula.getText(),txtNombre.getText(),txtTelefono.getText(),txtDireccion.getText(),null,txtID.getText(),cbxExeq.getSelectedItem().toString());
							if(rdbtnM.isEnabled()) {
								aux.setSexo("M");
							}
							else {
								aux.setSexo("F");
							}
							Hospital.getInstance().addPersona(aux);
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Resgistro", JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "Eliga una exequatur", "Error", JOptionPane.ERROR_MESSAGE);
						
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

	private void habilitarButton() {
		if(!txtNombre.getText().isEmpty()&&!txtCedula.getText().isEmpty()&&!txtTelefono.getText().isEmpty()&&!txtDireccion.getText().isEmpty()&&cbxExeq.getSelectedIndex()>0)
			okButton.setEnabled(true);
		
	}
}
