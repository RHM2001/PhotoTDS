package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErrorCamposLogin {

	private JFrame frmErrorCamposLogin;
	
	void muestra() {
		frmErrorCamposLogin.setVisible(true);
		frmErrorCamposLogin.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorCamposLogin window = new ErrorCamposLogin(0, 0);
					window.frmErrorCamposLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ErrorCamposLogin(int x, int y) {
		initialize(x,y);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int x, int y) {
		frmErrorCamposLogin = new JFrame();
		frmErrorCamposLogin.setResizable(false);
		frmErrorCamposLogin.setBounds(x, y, 450, 100);
		frmErrorCamposLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmErrorCamposLogin.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Nombre de usuario o contraseña no válido.");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		frmErrorCamposLogin.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmErrorCamposLogin.setVisible(false);
			}
		});
		panel_1.add(btnNewButton);
	}
	

}