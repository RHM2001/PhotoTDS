package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UsuarioRegistradoCorrectamente {
	
private JFrame frmUsuarioRegistradoCorrectamente;
	
	void muestra() {
		frmUsuarioRegistradoCorrectamente.setVisible(true);
		frmUsuarioRegistradoCorrectamente.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioRegistradoCorrectamente window = new UsuarioRegistradoCorrectamente(0, 0);
					window.frmUsuarioRegistradoCorrectamente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UsuarioRegistradoCorrectamente(int x, int y) {
		initialize(x,y);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int x, int y) {
		frmUsuarioRegistradoCorrectamente = new JFrame();
		frmUsuarioRegistradoCorrectamente.setResizable(false);
		frmUsuarioRegistradoCorrectamente.setBounds(x, y, 450, 100);
		frmUsuarioRegistradoCorrectamente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmUsuarioRegistradoCorrectamente.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Usuario registrado correctamente.");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		frmUsuarioRegistradoCorrectamente.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmUsuarioRegistradoCorrectamente.setVisible(false);
			}
		});
		panel_1.add(btnNewButton);
	}
}
