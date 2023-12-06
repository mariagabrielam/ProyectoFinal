package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Enfermedad;
import logico.Hospital;
import logico.Vacuna;

public class RegVacuna extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460001246198462049L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtLote;
	private JTextField txtNombre;
	private Enfermedad selectedAllEnfermedad;
	private Enfermedad selectedEnfermedadVacuna;
	private JTable table_allEnfermedades;
	private JTable table_enfermedadesVacuna;
	private static DefaultTableModel modelAllEnferm;
	private static DefaultTableModel modelEnfermVacuna;
	private static Object[] rowAllEnferm;
	private static Object[] rowEnfermVacuna;
	private static ArrayList<Enfermedad>allEnferm;
	private static ArrayList<Enfermedad>enfermVacuna;
	private JButton btnMove;
	private Vacuna miVacuna=null;
	private JSpinner spnCantVacuna;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public RegVacuna(Vacuna laVacuna) {
		miVacuna=laVacuna;
		setResizable(false);
		if(miVacuna==null) {
			setTitle("Registrar Vacuna");
		}else {
			setTitle("Modificar Vacuna");
		}
		setBounds(100, 100, 507, 458);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 479, 121);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Lote:");
			lblNewLabel.setBounds(40, 32, 46, 14);
			panel.add(lblNewLabel);
			
			txtLote = new JTextField();
			txtLote.setBounds(80, 29, 86, 20);
			panel.add(txtLote);
			txtLote.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(24, 82, 46, 14);
			panel.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(80, 79, 187, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		
		
			JLabel lblNewLabel_2 = new JLabel("Cantidad de Vacuna:");
			lblNewLabel_2.setBounds(272, 32, 122, 14);
			panel.add(lblNewLabel_2);
			
			spnCantVacuna = new JSpinner();
			spnCantVacuna.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
			spnCantVacuna.setBounds(389, 29, 68, 20);
			panel.add(spnCantVacuna);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 143, 479, 243);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(19, 49, 167, 155);
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table_allEnfermedades);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		{
		
			String[] header= {"Nombre"};
			modelAllEnferm= new DefaultTableModel();
			modelAllEnferm.setColumnIdentifiers(header);
			table_allEnfermedades = new JTable();
			table_allEnfermedades.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = table_allEnfermedades.getSelectedRow();
					if(index>=0) {
						selectedAllEnfermedad=allEnferm.get(index);
						selectedEnfermedadVacuna=null;
						btnMove.setText(">>");
						btnMove.setEnabled(true);
					}
				}
			});
			table_allEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_allEnfermedades.setModel(modelAllEnferm);
			scrollPane.setViewportView(table_allEnfermedades);
		}
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(289, 49, 167, 155);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		{
		
			table_enfermedadesVacuna = new JTable();
			String[] header= {"Nombre"};
			modelEnfermVacuna = new DefaultTableModel();
			modelEnfermVacuna.setColumnIdentifiers(header);
			table_enfermedadesVacuna.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index=table_enfermedadesVacuna.getSelectedRow();
					if(index>=0) {
						selectedEnfermedadVacuna=enfermVacuna.get(index);
						selectedAllEnfermedad=null;
						btnMove.setText("<<");
						btnMove.setEnabled(true);
					}
				}
			});
			table_enfermedadesVacuna.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_enfermedadesVacuna.setModel(modelEnfermVacuna);
			scrollPane_1.setViewportView(table_enfermedadesVacuna);
		}
		
		btnMove = new JButton(">>");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMove.setEnabled(false);
				btnMove.setText("");
				if(selectedAllEnfermedad!=null) {
					enfermVacuna.add(selectedAllEnfermedad);
					allEnferm.remove(selectedAllEnfermedad);
				}else {
					allEnferm.add(selectedEnfermedadVacuna);
					enfermVacuna.remove(selectedEnfermedadVacuna);
				}
			}
		});
		btnMove.setEnabled(false);
		btnMove.setBounds(196, 114, 83, 23);
		panel_1.add(btnMove);
		{
			JLabel lblNewLabel_3 = new JLabel("Enfermedades:");
			lblNewLabel_3.setBounds(19, 22, 105, 16);
			panel_1.add(lblNewLabel_3);
		}
		
		JLabel lblNewLabel_4 = new JLabel("Enfermedad/es Vacuna:");
		lblNewLabel_4.setBounds(292, 23, 144, 14);
		panel_1.add(lblNewLabel_4);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				if(miVacuna!=null) {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(miVacuna==null) {
							Vacuna laVacuna= new Vacuna(txtLote.getText(), txtNombre.getText(),allEnferm,new Integer(spnCantVacuna.getValue().toString()));
							Hospital.getInstance().addVacuna(laVacuna);
							ListarEnfermedades.loadEnfermedades();
							dispose();
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
		loadAllEnfermedades();
		loadEnfermedadVacuna();
	}
	private static void loadAllEnfermedades() {
		modelAllEnferm.setRowCount(0);
		rowAllEnferm = new Object[modelAllEnferm.getColumnCount()];
		for (Enfermedad enferm : Hospital.getInstance().getMisEnfermedades()){
		  rowAllEnferm[0] = enferm.getNombre();
		  rowAllEnferm[1] = enferm.isVigilancia();
		  rowAllEnferm[2] = enferm.getPrioridadTriaje();
		  modelAllEnferm.addRow(rowAllEnferm);
		}
		
	}
	
	private static void loadEnfermedadVacuna() {
		modelEnfermVacuna.setRowCount(0);
		rowEnfermVacuna = new Object[modelEnfermVacuna.getColumnCount()];
		for (Enfermedad enferm : Hospital.getInstance().getMisEnfermedades()){
		  rowEnfermVacuna[0] = enferm.getNombre();
		  rowEnfermVacuna[1] = enferm.isVigilancia();
		  rowEnfermVacuna[2] = enferm.getPrioridadTriaje();
		  modelEnfermVacuna.addRow(rowEnfermVacuna);
		}
		
	}
	
	
}
