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
	private JTable tblVacuna;
	private static DefaultTableModel model;
	private static Object[] row;
	private static DefaultTableModel model1;
	private static Object[] row1;
	private static Vacuna selected=null;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCancelar;
	private JTable tblEnfermedades;
	private JButton btnSelect;

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
		setBounds(100, 100, 760, 473);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				{
					String[] header= {"Lote","Nombre","Cantidad"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(header);
				}
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Enfermedades de la Vacuna", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(485, 13, 240, 339);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 23, 216, 303);
			panel_1.add(scrollPane);
			
			tblEnfermedades = new JTable();
			String[] header1= {"Nombre"};
			model1 = new DefaultTableModel();
			model1.setColumnIdentifiers(header1);
			scrollPane.setViewportView(tblEnfermedades);
			tblEnfermedades.setModel(model1);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(12, 13, 439, 339);
			panel.add(panel_2);
			panel_2.setLayout(null);
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(12, 27, 415, 299);
			panel_2.add(scrollPane_1);
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			tblVacuna = new JTable();
			tblVacuna.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index= tblVacuna.getSelectedRow();
					if(index>=0){
					  btnDelete.setEnabled(true);
					  btnUpdate.setEnabled(true);
					  selected =Hospital.getInstance().buscarVacunaByNombre(tblVacuna.getValueAt(index, 0).toString());
					  btnSelect.setEnabled(true);
					}
				}
			});
			tblVacuna.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tblVacuna.setModel(model);
			scrollPane_1.setViewportView(tblVacuna);
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
				
				btnSelect = new JButton("Seleccionar");
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loadEnfermedades();
					}
				});
				btnSelect.setEnabled(false);
				buttonPane.add(btnSelect);
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
	public static void loadEnfermedades() {
		model1.setRowCount(0);
		row1 = new Object[model1.getColumnCount()];
		for (Enfermedad aux : selected.getMisEnfermedad()){
		  row[1] = aux.getNombre();
		  model1.addRow(row1);
		}
	}
}
