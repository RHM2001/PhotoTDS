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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class InfoFotoAlbum {

	private JFrame frameFotoAlbum;
	private Foto foto;
	
	void muestra(Foto foto) {
		this.foto = foto;
		initialize();
		frameFotoAlbum.setVisible(true);
		frameFotoAlbum.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoFotoAlbum window = new InfoFotoAlbum(null);
					window.frameFotoAlbum.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public InfoFotoAlbum(Foto foto) {
		this.foto = foto;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameFotoAlbum = new JFrame();
		frameFotoAlbum.setType(Type.POPUP);
		frameFotoAlbum.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameFotoAlbum.setResizable(false);
		frameFotoAlbum.setIconImage(Toolkit.getDefaultToolkit().getImage(CommentPhoto.class.getResource("/Imagenes/instagramx16.png")));
		frameFotoAlbum.setBounds(100, 100, 570, 420);
		frameFotoAlbum.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frameFotoAlbum.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameFotoAlbum.setVisible(false);
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("OK");
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frameFotoAlbum.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 0, 0, 0, 10, 0};
		gbl_panel_1.rowHeights = new int[]{10, 0, 0, 219, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setText("Usuario : " + foto.getUsuario());
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 1;
		panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setText(foto.getDescripcion());
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 2;
		panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel = new JLabel("Añade un comentario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);

		JLabel fotoAlbum = new JLabel("");
		GridBagConstraints gbc_fotoAlbum = new GridBagConstraints();
		gbc_fotoAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_fotoAlbum.gridx = 1;
		gbc_fotoAlbum.gridy = 3;
		panel_1.add(fotoAlbum, gbc_fotoAlbum);
		
		ImageIcon iconoFoto = new ImageIcon(foto.getFile().getAbsolutePath());
		Image imagenOriginal = iconoFoto.getImage();

		int anchoMaximo = 250;
		int altoMaximo = 200;

		if (imagenOriginal.getWidth(null) > anchoMaximo || imagenOriginal.getHeight(null) > altoMaximo) {
		    Image imagenRedimensionada = imagenOriginal.getScaledInstance(anchoMaximo, altoMaximo, Image.SCALE_SMOOTH);
		    iconoFoto.setImage(imagenRedimensionada);
		}
		
		fotoAlbum.setIcon(iconoFoto);
		panel_1.add(fotoAlbum, gbc_fotoAlbum);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 3;
		panel_1.add(scrollPane, gbc_scrollPane);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		panel_1.add(panel_2, gbc_panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Me Gusta: ");
		panel_2.add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setText(String.valueOf(foto.getMeGusta()));
		panel_2.add(lblNewLabel_3);
		
		JButton btnNewButton_2 = new JButton("Eliminar foto de álbum ");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controlador.getUnicaInstancia().eliminarFoto(foto);
					frameFotoAlbum.setVisible(false);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(InfoFotoAlbum.class.getResource("/Imagenes/error.png")));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 4;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		frameFotoAlbum.getContentPane().add(panel_3, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Foto del álbum:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setText(foto.getAlbum());
		panel_3.add(lblNewLabel_4);
	}

}
