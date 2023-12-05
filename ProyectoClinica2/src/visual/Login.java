package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Hospital;
import logico.Usuario;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4116032584153181645L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream empresa;
				FileOutputStream empresa2;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;
				try {
					empresa = new FileInputStream ("hospital.dat");
					empresaRead = new ObjectInputStream(empresa);
					Hospital temp= (Hospital)empresaRead.readObject();
					Hospital.setElHospital(temp);
					empresa.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						empresa2 = new  FileOutputStream("empresa.dat");
						empresaWrite = new ObjectOutputStream(empresa2);
						empresaWrite.writeObject(Hospital.getInstance());
						empresa2.close();
						empresaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		if(Hospital.getInstance().getMisUsuarios()==null)
		{
			JOptionPane.showMessageDialog(null, "Aun no hay usuarios creados. Cree el usuario de administración", "Falta de Usuario", JOptionPane.ERROR_MESSAGE);
			CrearUsuario crearUsuario = new CrearUsuario(false);
			crearUsuario.setModal(true);
			crearUsuario.setVisible(true);
		}
		setTitle("Login");
		setBounds(100, 100, 410, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Ingrese sus datos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			panel.setBounds(12, 13, 364, 151);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Usuario:");
				lblNewLabel.setBounds(36, 47, 56, 16);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
				lblNewLabel_1.setBounds(36, 94, 82, 16);
				panel.add(lblNewLabel_1);
			}
			{
				txtUser = new JTextField();
				txtUser.setBounds(116, 44, 173, 22);
				panel.add(txtUser);
				txtUser.setColumns(10);
			}
			
			passwordField = new JPasswordField();
			passwordField.setEchoChar('*');
			passwordField.setBounds(116, 91, 173, 22);
			panel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLogin = new JButton("Login");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Hospital.getInstance().verificarUsuario(txtUser.getText(),passwordField.getPassword().toString())) {
							JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Login", JOptionPane.INFORMATION_MESSAGE);
							PrincipalVisual principalVisual = new PrincipalVisual(Hospital.getInstance().buscarUsuarioByName(txtUser.getText()));
							//principalVisual.setModal(true);
							principalVisual.setVisible(true);
							dispose();
						}
					}
				});
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
