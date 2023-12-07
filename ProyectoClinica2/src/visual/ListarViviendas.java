package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Hospital;
import logico.Paciente;
import logico.Vivienda;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class ListarViviendas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3136361799830269635L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private static DefaultTableModel model1;
	private static Object[] row1;
	private JTable tblPaciente;
	private Vivienda selected = null;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarViviendas dialog = new ListarViviendas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarViviendas() {
		setBounds(100, 100, 711, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Viviendas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(0, 0, 390, 218);
			contentPanel.add(panel);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 28, 366, 177);
				panel.add(scrollPane);
				{
					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index>0)
								okButton.setEnabled(true);
						}
					});
					scrollPane.setViewportView(table);
					String[] header = {"Número","Dirección"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(header);
					table.setModel(model);
					loadViviendas();
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes en la Vivienda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(402, 0, 279, 218);
			contentPanel.add(panel);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 28, 255, 177);
				panel.add(scrollPane);
				{
					tblPaciente = new JTable();
					tblPaciente.setRowSelectionAllowed(false);
					String[] header = {"Cédula","Nombre"};
					model1 = new DefaultTableModel();
					model1.setColumnIdentifiers(header);
					scrollPane.setViewportView(tblPaciente);
					tblPaciente.setModel(model1);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Seleccionar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int index = table.getSelectedRow();
						if(index>0)
						{
							selected = Hospital.getInstance().buscarViviendaByNumero(table.getValueAt(index, 0).toString());
							loadPacientes();
							okButton.setEnabled(false);
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
	private void loadViviendas() {
		model.setRowCount(0);
		row =new Object[model.getColumnCount()];
		for(Vivienda aux:Hospital.getInstance().getMisViviendas())
		{
			row[0] = aux.getNumVivienda();
			row[1] = aux.getDireccion();
			model.addRow(row);
		}
	}
	private void loadPacientes() {
		model1.setRowCount(0);
		row1 =new Object[model1.getColumnCount()];
		for(Paciente aux:selected.getMisPersonas())
		{
			row1[0] = aux.getCedula();
			row1[1] = aux.getNombre();
			model.addRow(row);
		}
	}

}
