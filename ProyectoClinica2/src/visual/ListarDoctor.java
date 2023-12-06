package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ListarDoctor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7937591526581336190L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblDoctores;
	private JComboBox<Object> cbxEspecialidad;
	private JComboBox<Object> cbxSexo;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarDoctor dialog = new ListarDoctor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarDoctor() {
		setTitle("Lista de Doctores");
		setBounds(100, 100, 624, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_Doctores = new JPanel();
			panel_Doctores.setBorder(new TitledBorder(null, "Doctores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_Doctores.setBounds(163, 11, 435, 350);
			contentPanel.add(panel_Doctores);
			panel_Doctores.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 21, 415, 318);
			panel_Doctores.add(scrollPane);
			
			tblDoctores = new JTable();
			tblDoctores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tblDoctores);
		}
		
		JPanel panel_Filtros = new JPanel();
		panel_Filtros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Filtros.setBounds(10, 188, 154, 173);
		contentPanel.add(panel_Filtros);
		panel_Filtros.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Especialidad: ");
		lblNewLabel.setBounds(10, 28, 72, 14);
		panel_Filtros.add(lblNewLabel);
		
		cbxEspecialidad = new JComboBox<Object>();
		cbxEspecialidad.setBounds(10, 44, 134, 20);
		panel_Filtros.add(cbxEspecialidad);
		
		JLabel lblNewLabel_1 = new JLabel("Sexo: ");
		lblNewLabel_1.setBounds(10, 90, 46, 14);
		panel_Filtros.add(lblNewLabel_1);
		
		cbxSexo = new JComboBox<Object>();
		cbxSexo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Masculino", "Femenino"}));
		cbxSexo.setBounds(10, 108, 134, 20);
		panel_Filtros.add(cbxSexo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ordenar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 154, 166);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JButton btnAlfa = new JButton("Alfabetico");
		btnAlfa.setBounds(10, 24, 134, 23);
		panel.add(btnAlfa);
		
		JButton btnId = new JButton("Identificaci\u00F3n");
		btnId.setBounds(10, 71, 134, 23);
		panel.add(btnId);
		
		JButton btnEspecialidad = new JButton("Especialidad");
		btnEspecialidad.setBounds(10, 118, 134, 23);
		panel.add(btnEspecialidad);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Modificar");
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
}
