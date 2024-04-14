package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controlador.Controlador;
import Modelo.Foto;
import Modelo.Publicacion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoFoto {

	private JFrame frameInfoFoto;
	private Foto foto;
	private Comentarios frameComentarios;
	
	void muestra(Foto foto) {
		this.foto = foto;
		initialize();
		frameInfoFoto.setVisible(true);
		frameInfoFoto.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoFoto window = new InfoFoto(null);
					window.frameInfoFoto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InfoFoto(Foto foto) {
		this.foto = foto;
		initialize();
	}
	
	public void muestraComentarios() {
		if (frameComentarios == null)
			frameComentarios = new Comentarios(foto);

		frameComentarios.muestra(foto);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameInfoFoto = new JFrame();
		frameInfoFoto.setType(Type.POPUP);
		frameInfoFoto.setResizable(false);
		frameInfoFoto.setIconImage(Toolkit.getDefaultToolkit().getImage(CommentPhoto.class.getResource("/Imagenes/instagramx16.png")));
		frameInfoFoto.setBounds(100, 100, 570, 395);
		frameInfoFoto.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frameInfoFoto.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameInfoFoto.setVisible(false);
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("OK");
		
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		frameInfoFoto.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 0, 0, 10, 0};
		gbl_panel_1.rowHeights = new int[]{10, 10, 0, 219, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setText("Usuario: " + foto.getUsuario());
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setText(foto.getDescripcion());
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 2;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Añade un comentario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel imagenFoto = new JLabel("");
		GridBagConstraints gbc_imagenFoto = new GridBagConstraints();
		gbc_imagenFoto.insets = new Insets(0, 0, 5, 5);
		gbc_imagenFoto.gridx = 1;
		gbc_imagenFoto.gridy = 3;

		ImageIcon iconoFoto = new ImageIcon(foto.getFile().getAbsolutePath());
		Image imagenOriginal = iconoFoto.getImage();

		int anchoMaximo = 250;
		int altoMaximo = 200;

		if (imagenOriginal.getWidth(null) > anchoMaximo || imagenOriginal.getHeight(null) > altoMaximo) {
		    Image imagenRedimensionada = imagenOriginal.getScaledInstance(anchoMaximo, altoMaximo, Image.SCALE_SMOOTH);
		    iconoFoto.setImage(imagenRedimensionada);
		}

		imagenFoto.setIcon(iconoFoto);
		panel_1.add(imagenFoto, gbc_imagenFoto);

		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 3;
		panel_1.add(scrollPane, gbc_scrollPane);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		panel_1.add(panel_2, gbc_panel_2);
		
		JButton btnNewButton_2 = new JButton("Like");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controlador.getUnicaInstancia().darMeGusta(foto);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_4 = new JButton("Ver comentarios");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				muestraComentarios();
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(InfoFoto.class.getResource("/Imagenes/ojo-rojo.png")));
		panel_2.add(btnNewButton_4);

		btnNewButton_2.setIcon(new ImageIcon(InfoFoto.class.getResource("/Imagenes/corazon.png")));
		panel_2.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Me Gusta: ");
		panel_2.add(lblNewLabel_2);
		System.out.println("Numero de me gusta de la foto -> " + foto.getMeGusta());
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setText(String.valueOf(foto.getMeGusta()));
		panel_2.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Eliminar Foto");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controlador.getUnicaInstancia().eliminarFoto(foto);
					frameInfoFoto.setVisible(false);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controlador.getUnicaInstancia().nuevoComentario(foto, textArea.getText());
					textArea.setText("");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_3.setIcon(new ImageIcon(InfoFoto.class.getResource("/Imagenes/error.png")));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 4;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
	}

}
