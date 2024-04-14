package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErrorCamposRegistro {

	private JFrame frmErrorCamposRegistro;
	
	void muestra() {
		frmErrorCamposRegistro.setVisible(true);
		frmErrorCamposRegistro.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorCamposRegistro window = new ErrorCamposRegistro();
					window.frmErrorCamposRegistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ErrorCamposRegistro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmErrorCamposRegistro = new JFrame();
		frmErrorCamposRegistro.setResizable(false);
		frmErrorCamposRegistro.setBounds(100, 100, 450, 100);
		frmErrorCamposRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmErrorCamposRegistro.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Hay campos obligatorios sin rellenar.");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		frmErrorCamposRegistro.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmErrorCamposRegistro.setVisible(false);
			}
		});
		panel_1.add(btnNewButton);
	}

}
