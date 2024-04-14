package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorUsuarioYaRegistrado {
	
private JFrame frmErrorUsuarioYaRegistrado;
	
	void muestra() {
		frmErrorUsuarioYaRegistrado.setVisible(true);
		frmErrorUsuarioYaRegistrado.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorUsuarioYaRegistrado window = new ErrorUsuarioYaRegistrado(0, 0);
					window.frmErrorUsuarioYaRegistrado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ErrorUsuarioYaRegistrado(int x, int y) {
		initialize(x,y);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int x, int y) {
		frmErrorUsuarioYaRegistrado = new JFrame();
		frmErrorUsuarioYaRegistrado.setResizable(false);
		frmErrorUsuarioYaRegistrado.setBounds(x, y, 450, 100);
		frmErrorUsuarioYaRegistrado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmErrorUsuarioYaRegistrado.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Nombre de usuario o email ya registrado.");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		frmErrorUsuarioYaRegistrado.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmErrorUsuarioYaRegistrado.setVisible(false);
			}
		});
		panel_1.add(btnNewButton);
	}
}
