package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;

public class RegConsulta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5167795281497040413L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNHC;
	private JTextField txtNombre;
	private JTextField txtMotivo;
	private JTextField txtPeso;
	private JTextField txtEstatura;
	private JRadioButton rbtnYes;
	private JRadioButton rbtnNo;
	private JRadioButton rbtnRojo;
	private JRadioButton rbtnAmarillo;
	private JRadioButton rbtnVerde;
	private JComboBox<Object> cbxSangre;
	private JSpinner spnFchConsulta;
	private JButton btnCambiar;
	private JTable tblVacunasDisponibles;
	private JTable tblVacunasPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta() {
		setTitle("Registrar Consulta");
		setBounds(100, 100, 721, 535);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 11, 423, 132);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("NHC:");
				label.setBounds(12, 35, 56, 16);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setBounds(180, 35, 56, 16);
				panel.add(label);
			}
			{
				txtNHC = new JTextField();
				txtNHC.setEditable(false);
				txtNHC.setColumns(10);
				txtNHC.setBounds(52, 32, 116, 22);
				panel.add(txtNHC);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEnabled(false);
				txtNombre.setColumns(10);
				txtNombre.setBounds(235, 32, 176, 22);
				panel.add(txtNombre);
			}

			JLabel lblNewLabel_1 = new JLabel("Peso: ");
			lblNewLabel_1.setBounds(12, 66, 46, 14);
			panel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("Estatura: ");
			lblNewLabel_2.setBounds(176, 66, 60, 14);
			panel.add(lblNewLabel_2);

			txtPeso = new JTextField();
			txtPeso.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
				}

				public void keyTyped(KeyEvent e) {
					char caracter = e.getKeyChar();
					if ((caracter < '0' || caracter > '9') && (caracter != KeyEvent.VK_BACK_SPACE)
							&& (caracter != '.' || txtPeso.getText().contains("."))) {
						e.consume();
					}
				}
			});
			txtPeso.setBounds(52, 62, 116, 20);
			panel.add(txtPeso);
			txtPeso.setColumns(10);

			txtEstatura = new JTextField();
			txtEstatura.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
				}

				public void keyTyped(KeyEvent e) {
					char caracter = e.getKeyChar();
					if ((caracter < '0' || caracter > '9') && (caracter != KeyEvent.VK_BACK_SPACE)
							&& (caracter != '.' || txtEstatura.getText().contains("."))) {
						e.consume();
					}

				}
			});
			txtEstatura.setBounds(235, 63, 176, 20);
			panel.add(txtEstatura);
			txtEstatura.setColumns(10);
			{
				JLabel lblNewLabel_3 = new JLabel("Tipo de Sangre: ");
				lblNewLabel_3.setBounds(12, 93, 87, 14);
				panel.add(lblNewLabel_3);
			}
			
			cbxSangre = new JComboBox<Object>();
			cbxSangre.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Seleccione>", "AB+", "AB-", "A+", "A-", "B+", "B-", "O+", "O-"}));
			cbxSangre.setBounds(109, 93, 127, 20);
			panel.add(cbxSangre);
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(
					new TitledBorder(null, "Datos de la Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 154, 423, 290);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Fecha Realizada:");
				label.setBounds(29, 29, 116, 16);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Motivo de Consulta:");
				label.setBounds(29, 58, 116, 16);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Prioridad Triaje:");
				label.setBounds(29, 87, 116, 16);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Descripci\u00F3n:");
				label.setBounds(29, 114, 84, 16);
				panel.add(label);
			}
			{
				txtMotivo = new JTextField();
				txtMotivo.setColumns(10);
				txtMotivo.setBounds(146, 55, 267, 22);
				panel.add(txtMotivo);
			}
			{
				spnFchConsulta = new JSpinner();
				spnFchConsulta.setEnabled(false);
				spnFchConsulta
						.setModel(new SpinnerDateModel(new Date(1701403200000L), null, null, Calendar.DAY_OF_YEAR));
				spnFchConsulta.setBounds(129, 26, 127, 22);
				panel.add(spnFchConsulta);
			}
			{
				JLabel lblNewLabel = new JLabel("\u00BFFue importante la consulta?");
				lblNewLabel.setBounds(29, 255, 175, 16);
				panel.add(lblNewLabel);
			}
			{
				rbtnNo = new JRadioButton("no");
				rbtnNo.setBounds(247, 251, 48, 25);
				panel.add(rbtnNo);
			}

			rbtnYes = new JRadioButton("s\u00ED");
			rbtnYes.setSelected(true);
			rbtnYes.setBounds(203, 251, 41, 25);
			panel.add(rbtnYes);

			rbtnRojo = new JRadioButton("Rojo");
			rbtnRojo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnAmarillo.setSelected(false);
					rbtnVerde.setSelected(false);
				}
			});
			rbtnRojo.setBounds(129, 84, 56, 23);
			panel.add(rbtnRojo);

			rbtnAmarillo = new JRadioButton("Amarillo");
			rbtnAmarillo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnVerde.setSelected(false);
					rbtnRojo.setSelected(false);
				}
			});
			rbtnAmarillo.setBounds(203, 84, 84, 23);
			panel.add(rbtnAmarillo);

			rbtnVerde = new JRadioButton("Verde");
			rbtnVerde.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnAmarillo.setSelected(false);
					rbtnRojo.setSelected(false);
				}
			});
			rbtnVerde.setSelected(true);
			rbtnVerde.setBounds(291, 84, 70, 23);
			panel.add(rbtnVerde);

			JTextArea txtDescripcion = new JTextArea();
			txtDescripcion.setBounds(29, 141, 384, 103);
			panel.add(txtDescripcion);
		}
		
		JPanel panel_VacunasGlobales = new JPanel();
		panel_VacunasGlobales.setBorder(new TitledBorder(null, "Vacunas Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VacunasGlobales.setBounds(445, 11, 250, 193);
		contentPanel.add(panel_VacunasGlobales);
		panel_VacunasGlobales.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 22, 230, 160);
			panel_VacunasGlobales.add(scrollPane);
			
			tblVacunasDisponibles = new JTable();
			scrollPane.setViewportView(tblVacunasDisponibles);
		}
		
		JPanel panel_VacunasLocales = new JPanel();
		panel_VacunasLocales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vacunas a Suministrar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_VacunasLocales.setBounds(445, 251, 250, 193);
		contentPanel.add(panel_VacunasLocales);
		panel_VacunasLocales.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 22, 230, 160);
			panel_VacunasLocales.add(scrollPane);
			
			tblVacunasPaciente = new JTable();
			scrollPane.setViewportView(tblVacunasPaciente);
		}
		
		btnCambiar = new JButton("");
		btnCambiar.setBounds(524, 215, 89, 23);
		contentPanel.add(btnCambiar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
}
