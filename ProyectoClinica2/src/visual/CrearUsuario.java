package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Doctor;
import logico.Empleado;
import logico.Hospital;
import logico.Usuario;
import java.awt.Toolkit;

public class CrearUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6356805486808585626L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPanel panel;
	private JTextField txtPassword;
	private JComboBox<Object> cbxTipo;
	private static DefaultTableModel model;
	private static Object[] row;
	private Empleado selected = null;
	private JButton okButton;
	private JScrollPane panelScroll;
	private JTable tblPersona;
	private JPanel panEmpleado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearUsuario dialog = new CrearUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearUsuario.class.getResource("/Iconos/userIcon.png")));
		
		setTitle("Crear Usuario");
		setBounds(100, 100, 450, 485);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Ingrese sus datos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 408, 128);
		contentPanel.add(panel);
		
		JLabel label = new JLabel("Usuario:");
		label.setBounds(10, 32, 56, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Contrase\u00F1a:");
		label_1.setBounds(10, 64, 89, 16);
		panel.add(label_1);
		
		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarButton();
			}
		});
		txtUsername.setColumns(10);
		txtUsername.setBounds(91, 29, 173, 22);
		panel.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarButton();
			}
		});
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					okButton.setEnabled(true);
			}
		});
		txtPassword.setColumns(10);
		txtPassword.setBounds(91, 61, 173, 22);
		panel.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		lblNewLabel.setBounds(10, 93, 66, 16);
		panel.add(lblNewLabel);
		
		cbxTipo = new JComboBox<Object>();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxTipo.getSelectedIndex()==1) {
					loadDoctor();
					panEmpleado.setVisible(true);
				}
				else if(cbxTipo.getSelectedIndex()==2)
				{
					loadEmpleado();
					panEmpleado.setVisible(true);
				}
				habilitarButton();
			}
		});
		cbxTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxTipo.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Seleccione>", "Doctor", "Secretario"}));
		cbxTipo.setBounds(58, 90, 106, 22);
		panel.add(cbxTipo);
		
		model = new DefaultTableModel();
		
		panEmpleado = new JPanel();
		panEmpleado.setBorder(new TitledBorder(null, "Seleccione un Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panEmpleado.setBounds(12, 154, 408, 236);
		contentPanel.add(panEmpleado);
		panEmpleado.setLayout(null);
		panEmpleado.setVisible(false);
		
		panelScroll = new JScrollPane();
		panelScroll.setBounds(12, 26, 384, 197);
		panEmpleado.add(panelScroll);
		panelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tblPersona = new JTable();
		tblPersona.setModel(model);
		tblPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblPersona.getSelectedRow();
				   if(index>=0){
					   okButton.setEnabled(true);
					   if(cbxTipo.getSelectedIndex()>0)
						   selected = Hospital.getInstance().buscarDoctorById(tblPersona.getValueAt(index, 0).toString());
				   }
				   habilitarButton();
			}
		});
		tblPersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelScroll.setViewportView(tblPersona);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tipo = "Privilegiado";
						if(cbxTipo.getSelectedIndex()==2)
							tipo = "Basico";
						if(txtUsername.getText()!=null&&txtPassword.getText()!=null)
						{
							Usuario aux = new Usuario(txtUsername.getText(),txtPassword.getText(), selected, tipo);
							Hospital.getInstance().addUsuario(aux);
							JOptionPane.showMessageDialog(null, "Operaci�n Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
							clear();
						}
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
	private void habilitarButton() {
		if(!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty() && cbxTipo.getSelectedIndex()!=0 && selected!=null&&tblPersona.getSelectedRow()>0)
			okButton.setEnabled(true);
	}

	private void loadDoctor()
	{
		model.setRowCount(0);
		String[] header = {"C�digo","Nombre","C�dula","Especialidad"};
		model.setColumnIdentifiers(header);
		row = new Object[model.getColumnCount()];
		for (Empleado aux: Hospital.getInstance().getMisEmpleados()) {
		  if(aux instanceof Doctor) {
			  row[0] = aux.getId();
			  row[1] = aux.getNombre();
			  row[2] = aux.getCedula();
			  row[3] = ((Doctor) aux).getEspecialidad();
			  model.addRow(row);
		  }
		}	
	}
	private void loadEmpleado()
	{
		model.setRowCount(0);
		String[] header = {"C�digo","Nombre","C�dula"};
		model.setColumnIdentifiers(header);
		row = new Object[model.getColumnCount()];
		for (Empleado aux: Hospital.getInstance().getMisEmpleados()) {
		  if(!(aux instanceof Doctor)) {
			  row[0] = aux.getId();
			  row[1] = aux.getNombre();
			  row[2] = aux.getCedula();
			  model.addRow(row);
		  }
		}
	}
	private void clear()
	{
		txtUsername.setText("");
		txtPassword.setText("");
		cbxTipo.setSelectedIndex(0);
		panEmpleado.setVisible(false);
		okButton.setEnabled(false);
	}
}
