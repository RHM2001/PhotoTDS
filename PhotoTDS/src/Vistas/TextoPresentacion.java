package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.border.TitledBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class TextoPresentacion {

	private JFrame frmTextoPresentacion;
	private LoginRegister loginRegister;
	private EditarPerfil editarPerfil;
	private String presentacion = null;
	
	void muestra() {
		frmTextoPresentacion.setVisible(true);
		frmTextoPresentacion.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextoPresentacion window = new TextoPresentacion(null);
					window.frmTextoPresentacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextoPresentacion(Object object) {
		if (object instanceof LoginRegister) {
			loginRegister = (LoginRegister) object;
		}
		
		if (object instanceof EditarPerfil) {
			editarPerfil = (EditarPerfil) object;
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextoPresentacion = new JFrame();
		frmTextoPresentacion.setTitle("Texto Presentaci\u00F3n");
		frmTextoPresentacion.setIconImage(Toolkit.getDefaultToolkit().getImage(TextoPresentacion.class.getResource("/Imagenes/instagram.png")));
		frmTextoPresentacion.setBounds(100, 100, 483, 232);
		
		JPanel Encabezado = new JPanel();
		frmTextoPresentacion.getContentPane().add(Encabezado, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Escribe tu presentaci\u00F3n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		Encabezado.add(lblNewLabel);
		
		JPanel Boton = new JPanel();
		frmTextoPresentacion.getContentPane().add(Boton, BorderLayout.SOUTH);
		
		JPanel TextoPanel = new JPanel();
		TextoPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmTextoPresentacion.getContentPane().add(TextoPanel, BorderLayout.CENTER);
		GridBagLayout gbl_TextoPanel = new GridBagLayout();
		gbl_TextoPanel.columnWidths = new int[]{5, 0, 5, 0};
		gbl_TextoPanel.rowHeights = new int[]{23, 0, 5, 0};
		gbl_TextoPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_TextoPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		TextoPanel.setLayout(gbl_TextoPanel);
		
		TextArea textArea = new TextArea();
		textArea.setText("\r\n");
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 1;
		TextoPanel.add(textArea, gbc_textArea);
		textArea.setForeground(Color.BLACK);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTextoPresentacion.setVisible(false);
				presentacion = textArea.getText();
			}
		});
		Boton.add(btnNewButton);
	}
	
	public String obtenerPresentacion() {
		return presentacion;
	}

}
