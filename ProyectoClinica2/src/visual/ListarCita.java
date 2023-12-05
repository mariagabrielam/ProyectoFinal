package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarCita extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 91411914069182076L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblAgenda;
	private JComboBox<Object> comboBox;
	private JCalendar calendar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarCita dialog = new ListarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarCita() {
		setTitle("Agenda de Citas");
		setBounds(100, 100, 577, 406);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_Doctor = new JPanel();
			panel_Doctor.setBorder(new TitledBorder(null, "Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_Doctor.setBounds(10, 11, 276, 76);
			contentPanel.add(panel_Doctor);
			panel_Doctor.setLayout(null);
			
			comboBox = new JComboBox<Object>();
			comboBox.setBounds(10, 26, 256, 27);
			panel_Doctor.add(comboBox);
		}
		
		JPanel panel_Dia = new JPanel();
		panel_Dia.setBorder(new TitledBorder(null, "Calendario de Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Dia.setBounds(10, 98, 276, 225);
		contentPanel.add(panel_Dia);
		panel_Dia.setLayout(null);
		
		calendar = new JCalendar();
		calendar.setBounds(10, 25, 256, 189);
		panel_Dia.add(calendar);
		
		JPanel panel_Horas = new JPanel();
		panel_Horas.setBorder(new TitledBorder(null, "Agenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Horas.setBounds(296, 11, 255, 312);
		contentPanel.add(panel_Horas);
		panel_Horas.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 21, 235, 280);
		panel_Horas.add(scrollPane);
		
		tblAgenda = new JTable();
		tblAgenda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Hora de Inicio", "Doctor", "Paciente"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7946187111276830986L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tblAgenda);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
