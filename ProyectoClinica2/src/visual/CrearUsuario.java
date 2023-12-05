package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Doctor;
import logico.Hospital;
import logico.Paciente;
import logico.Persona;
import logico.Usuario;
import javax.swing.ScrollPaneConstants;

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
	private Persona selected = null;
	private JButton okButton;
	private JScrollPane panPersona;
	private JTable tblPersona;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearUsuario dialog = new CrearUsuario(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearUsuario(boolean adminCreado) {
		if(!adminCreado)
			crearAdmin();
		panPersona.setVisible(false);
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
		txtUsername.setColumns(10);
		txtUsername.setBounds(91, 29, 173, 22);
		panel.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!adminCreado)
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
				if(cbxTipo.getSelectedIndex()<1) {
					panPersona.setVisible(true);
					loadPersonas();
				}
			}
		});
		cbxTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxTipo.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Seleccione>", "Admin", "Doctor", "Secretario"}));
		cbxTipo.setBounds(58, 90, 106, 22);
		panel.add(cbxTipo);
		
		panPersona = new JScrollPane();
		panPersona.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panPersona.setViewportBorder(new TitledBorder(null, "Seleccione Persona", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panPersona.setBounds(12, 152, 408, 250);
		contentPanel.add(panPersona);
		
		String[] header = {"Código","Nombre"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		tblPersona = new JTable();
		tblPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblPersona.getSelectedRow();
				   if(index>=0){
					   okButton.setEnabled(true);
					   if(cbxTipo.getSelectedIndex()==2)
						   selected = Hospital.getInstance().buscarDoctorById(tblPersona.getValueAt(index, 0).toString());
					   else
						   selected = Hospital.getInstance().buscarPacienteByNHC(tblPersona.getValueAt(index, 0).toString());
				   }
			}
		});
		tblPersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panPersona.setViewportView(tblPersona);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtUsername.getText()!=null&&txtPassword.getText()!=null)
						{
							if(!adminCreado)
							{
								Usuario aux = new Usuario(txtUsername.getText(),txtPassword.getText(),null);
								Hospital.getInstance().addUsuario(aux);
								JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Resgistro", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								Usuario aux = new Usuario(txtUsername.getText(),txtPassword.getText(),selected);
								Hospital.getInstance().addUsuario(aux);
								JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Resgistro", JOptionPane.INFORMATION_MESSAGE);
							}
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void crearAdmin()
	{
		txtUsername.setText("Admin");
		txtUsername.setEditable(false);
		cbxTipo.setSelectedIndex(1);
		cbxTipo.setEnabled(false);
		
	}
	private void loadPersonas()
	{
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Persona aux: Hospital.getInstance().getMisPersonas()) {
		  if(cbxTipo.getSelectedIndex()==2&&aux instanceof Doctor) {
			  row[0] = ((Doctor) aux).getId();
			  row[1] = aux.getNombre();
			  model.addRow(row);
		  }
		  else {
			  row[0] = ((Paciente) aux).getNhc();
			  row[1] = aux.getNombre();
			  model.addRow(row);
		  }
		}
	}
}
