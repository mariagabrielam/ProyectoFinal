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

import logico.Hospital;
import logico.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListarVacuna extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14848950850112453L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Vacuna selected=null;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarVacuna dialog = new ListarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarVacuna() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarVacuna.class.getResource("/Iconos/vacunaIcon.png")));
		setTitle("Listado de Vacunas");
		setBounds(100, 100, 593, 349);
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
				panel.add(scrollPane);
				{
					String[] header= {"Lote","Nombre","Enfermedad","Cantidad"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(header);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index= table.getSelectedRow();
							if(index>=0){
							  btnDelete.setEnabled(true);
							  btnUpdate.setEnabled(true);
							  selected =Hospital.getInstance().buscarVacunaByNombre(table.getValueAt(index, 0).toString());
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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnUpdate = new JButton("Actualizar");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegVacuna update= new RegVacuna(selected);
						update.setModal(true);
						update.setVisible(true);
					}
				});
				btnUpdate.setEnabled(false);
				btnUpdate.setActionCommand("btnUpdate");
				buttonPane.add(btnUpdate);
				getRootPane().setDefaultButton(btnUpdate);
			}
			{
				btnDelete = new JButton("Eliminar");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int Option = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar el queso con código: "+selected.getNombre(), "Eliminar", JOptionPane.OK_CANCEL_OPTION);
						if(Option == JOptionPane.OK_OPTION){
					    	Hospital.getInstance().eliminarVacuna(selected);
					    	loadVacunas();
					    	btnDelete.setEnabled(false);
					    	btnUpdate.setEnabled(false);	    	
					    }
					}
				});
				btnDelete.setEnabled(false);;
				buttonPane.add(btnDelete);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadVacunas();
	}
	public static void loadVacunas() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Vacuna vacunita : Hospital.getInstance().getMisVacunas()){
		  row[0] = vacunita.getLote();
		  row[1] = vacunita.getNombre();
		  row[2] = vacunita.getMisEnfermedad();
		  row[3]= vacunita.getCant();
		  model.addRow(row);
		}
	}
}
