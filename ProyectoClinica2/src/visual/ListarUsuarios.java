package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Empleado;
import logico.Hospital;
import logico.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarUsuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1102109312479378439L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarUsuarios dialog = new ListarUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarUsuarios() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Usuarios", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			panel.setBounds(0, 0, 432, 218);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 28, 408, 177);
				panel.add(scrollPane);
				{
					table = new JTable();
					scrollPane.setViewportView(table);
					String[] header = {"Username","Contraseña","Privilegio","Cargo"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(header);
					table.setModel(model);
				}
			} 
		} 
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
		loadUsuarios();
	}
	
	private void loadUsuarios()
	{
		model.setRowCount(0);
		row =new Object[model.getColumnCount()];
		for(Usuario aux:Hospital.getInstance().getMisUsuarios())
		{
			row[0] = aux.getUsername();
			row[1] = aux.getPassword();
			row[3] = aux.getTipo();
			if(aux.getUsername().equalsIgnoreCase("Admin"))
				row[4] = "Admin";
			else
				row[4] = ((Empleado) aux.getPersona()).getCargo();
		}
	}

}
