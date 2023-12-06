package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4116032584153181645L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	
	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream hospital;
				FileOutputStream hospital2;
				ObjectInputStream hospitalRead;
				ObjectOutputStream hospitalWrite;
				try {
					hospital = new FileInputStream("hospital.dat");
					hospitalRead = new ObjectInputStream(hospital);
					Hospital temp = (Hospital) hospitalRead.readObject();
					Hospital.setElHospital(temp);
					hospital.close();
					hospitalRead.close();
				} catch (FileNotFoundException e) {
					try {
						hospital2 = new FileOutputStream("hospital.dat");
						hospitalWrite = new ObjectOutputStream(hospital2);
						Usuario admin = new Usuario("Admin", "Admin", null, "Administrador");
						Hospital.getInstance().addUsuario(admin);
						hospitalWrite.writeObject(Hospital.getInstance());
						hospital2.close();
						hospitalWrite.close();
					} catch (FileNotFoundException e1) {
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream hospital2;
				ObjectOutputStream hospitalWrite;
				try {
					hospital2 = new FileOutputStream("hospital.dat");
					hospitalWrite = new ObjectOutputStream(hospital2);
					hospitalWrite.writeObject(Hospital.getInstance());
					hospital2.close();
					hospitalWrite.close();
				} catch (FileNotFoundException e1) {

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		setTitle("Login");
		setBounds(100, 100, 395, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(
					new TitledBorder(null, "Ingrese sus datos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
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
				txtUser.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						
					}
				});
				txtUser.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						habilitarButton();
					}
				});
				txtUser.setBounds(116, 44, 173, 22);
				panel.add(txtUser);
				txtUser.setColumns(10);
			}

			txtPassword = new JPasswordField();
			txtPassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					habilitarButton();
				}
			});
			txtPassword.setEchoChar('*');
			txtPassword.setBounds(116, 91, 173, 22);
			panel.add(txtPassword);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnLogin = new JButton("Login");
				btnLogin.setEnabled(false);
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//System.out.println("USER ["+txtUser.getText()+"] PASS ["+String.valueOf(txtPassword.getPassword())+"]");
						if (Hospital.getInstance().verificarUsuario(txtUser.getText(),
								String.valueOf(txtPassword.getPassword()))) {
							
							PrincipalVisual frame = new PrincipalVisual();
							frame.setVisible(true);
							dispose();
						} else
							JOptionPane.showMessageDialog(null, "Datos Erroneos", "Login", JOptionPane.ERROR_MESSAGE);
					}
				});
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
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

	private void habilitarButton() {
		if (!txtUser.getText().isEmpty() && !txtPassword.getPassword().toString().isEmpty())
			btnLogin.setEnabled(true);
	}
}