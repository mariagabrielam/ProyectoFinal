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
import javax.swing.JOptionPane;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class RegVacuna extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460001246198462049L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtLote;
	private JTextField txtNombre;
	private JTable table_allEnfermedades;
	private JTable table_enfermedadesVacuna;
	private static DefaultTableModel modelAllEnferm;
	private static DefaultTableModel modelEnfermVacuna;
	private static Object[] rowAllEnferm;
	private static Object[] rowEnfermVacuna;
	private static ArrayList<Enfermedad>allEnferm = new ArrayList<>();
	private static ArrayList<Enfermedad>enfermVacuna = new ArrayList<>();
	private Enfermedad selectedGlobal = null;
	private Enfermedad selectedLocal = null;
	private JButton btnMove;
	private Vacuna miVacuna=null;
	private JSpinner spnCantVacuna;
	private JButton btnRegistrar;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacuna dialog = new RegVacuna(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		
		setBounds(100, 100, 515, 474);
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
			lblNewLabel.setBounds(24, 32, 46, 14);
			panel.add(lblNewLabel);
			
			txtLote = new JTextField();
			txtLote.setEditable(false);
			txtLote.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					habilitarBoton();
				}
			});
			txtLote.setBounds(80, 29, 106, 20);
			txtLote.setText("V-"+Hospital.getCodigoVacuna());
			panel.add(txtLote);
			txtLote.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(24, 79, 68, 17);
			panel.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					habilitarBoton();
				}
			});
			txtNombre.setBounds(80, 79, 187, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		
		
			JLabel lblNewLabel_2 = new JLabel("Cantidad de Vacuna:");
			lblNewLabel_2.setBounds(257, 32, 122, 14);
			panel.add(lblNewLabel_2);
			
			spnCantVacuna = new JSpinner();
			spnCantVacuna.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
			spnCantVacuna.setBounds(377, 29, 68, 20);
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
						selectedGlobal=allEnferm.get(index);
						selectedLocal = null;
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
						selectedLocal = enfermVacuna.get(index);
						selectedGlobal=null;
						btnMove.setText("<<");
						btnMove.setEnabled(true);
					}
					habilitarBoton();
				}
			});
			table_enfermedadesVacuna.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_enfermedadesVacuna.setModel(modelEnfermVacuna);
			scrollPane_1.setViewportView(table_enfermedadesVacuna);
		}
		
		btnMove = new JButton("");
		btnMove.setEnabled(false);
		btnMove.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBoton();
			}
		});
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMove.setEnabled(false);
				btnMove.setText("");
				if(selectedGlobal!=null) {
					enfermVacuna.add(selectedGlobal);
					allEnferm.remove(selectedGlobal);
				}else {
					allEnferm.add(selectedLocal);
					enfermVacuna.remove(selectedLocal);
				}
				loadAllEnfermedades();
				loadEnfermedadVacuna();
			}
		});
		btnMove.setBounds(196, 114, 83, 23);
		panel_1.add(btnMove);
		{
			JLabel lblNewLabel_3 = new JLabel("Enfermedad/es:");
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
				btnRegistrar = new JButton("Registrar");
				if(miVacuna!=null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(miVacuna==null) {
							Vacuna laVacuna= new Vacuna(txtLote.getText(), txtNombre.getText(),allEnferm,new Integer(spnCantVacuna.getValue().toString()));
							Hospital.getInstance().addVacuna(laVacuna);
							ListarEnfermedades.loadEnfermedades();
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}else {
							miVacuna.setNombre(txtNombre.getText());
							miVacuna.setCant(new Integer(spnCantVacuna.getValue().toString()));
							miVacuna.setMisEnfermedad(enfermVacuna);
						}
						enfermVacuna = new ArrayList<>();
						clonarEnfermedades();
						loadAllEnfermedades();
						loadEnfermedadVacuna();
					}
				});
				btnRegistrar.setEnabled(false);
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		clonarEnfermedades();
		loadAllEnfermedades();
		loadEnfermedadVacuna();
	}
	private static void loadAllEnfermedades() {
		modelAllEnferm.setRowCount(0);
		rowAllEnferm = new Object[modelAllEnferm.getColumnCount()];
		for (Enfermedad enferm : allEnferm){
		  rowAllEnferm[0] = enferm.getNombre();
		  modelAllEnferm.addRow(rowAllEnferm);
		}
	}
	
	private static void loadEnfermedadVacuna() {
		modelEnfermVacuna.setRowCount(0);
		rowEnfermVacuna = new Object[modelEnfermVacuna.getColumnCount()];
		for (Enfermedad enferm : enfermVacuna){
		  rowEnfermVacuna[0] = enferm.getNombre();
		  modelEnfermVacuna.addRow(rowEnfermVacuna);
		}
		
	}
	
	private void habilitarBoton() {
		if(!txtLote.getText().isEmpty() && !txtNombre.getText().isEmpty( )&& enfermVacuna.size()>0) {
			btnRegistrar.setEnabled(true);
		}else {
			btnRegistrar.setEnabled(false);
		}
	}
	
	private void clean() {
		txtLote.setText("V-"+Hospital.getCodigoVacuna());
		spnCantVacuna.setValue(new Integer(1));
		txtNombre.setText("");
	}
	private void clonarEnfermedades()
	{
		allEnferm = (ArrayList<Enfermedad>) Hospital.getInstance().getMisEnfermedades().clone();
	}
}
