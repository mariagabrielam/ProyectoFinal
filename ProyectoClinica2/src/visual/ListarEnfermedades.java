package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Enfermedad;
import logico.Fabrica;
import logico.Hospital;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Enfermedad selected = null;
	private JButton btnUpdate_1;
	private JButton btnDelete_1;
	private JButton btnCancel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEnfermedades dialog = new ListarEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarEnfermedades() {
		setTitle("Listado de Enfermedades");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] header = {"Nombre","Enfermedad en Vigilacia","Prioridad de Triaje"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(header);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
						   int index = table.getSelectedRow();
						   if(index>=0){
							  btnDelete_1.setEnabled(true);
							  btnUpdate_1.setEnabled(true);
							  selected =Hospital.getInstance().buscarEnfermedadByNombre(table.getValueAt(index, 0).toString());
						   }
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnUpdate_1 = new JButton("Actualizar");
				btnUpdate_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegEnfermedad update= new RegEnfermedad(selected);
						update.setModal(true);
						update.setVisible(true);
					}
				});
				btnUpdate_1.setEnabled(false);
				btnUpdate_1.setActionCommand("btnUpdate");
				buttonPane.add(btnUpdate_1);
				getRootPane().setDefaultButton(btnUpdate_1);
			}
			{
				btnDelete_1 = new JButton("Eliminar");
				btnDelete_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int Option = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar el queso con c�digo: "+selected.getCodigo(), "Eliminar", JOptionPane.OK_CANCEL_OPTION);
						if(Option == JOptionPane.OK_OPTION){
					    	Hospital.getInstance().EliminarQueso(selected);
					    	//loadQuesos();
					    	btnDelete_1.setEnabled(false);
					    	btnUpdate_1.setEnabled(false);	    	
					    }
					}
				});
				btnDelete_1.setEnabled(false);;
				buttonPane.add(btnDelete_1);
			}
			{
				btnCancel_1 = new JButton("Cancelar");
				btnCancel_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel_1.setActionCommand("Cancel");
				buttonPane.add(btnCancel_1);
			}
		}
	}

}
