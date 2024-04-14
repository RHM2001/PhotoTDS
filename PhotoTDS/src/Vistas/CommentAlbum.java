package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.List;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import Controlador.Controlador;
import Modelo.Album;
import Modelo.Foto;
import Modelo.Publicacion;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class CommentAlbum {

	private JFrame frameComment;
	private AddPublicacion frmFoto;
	private Home frmHome;
	private JTextField textField;
	

	void muestra() {
		frameComment.setVisible(true);
		frameComment.setLocationRelativeTo(null);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommentAlbum window = new CommentAlbum(null);
					window.frameComment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CommentAlbum(AddPublicacion frmPhoto) {
		frmFoto = frmPhoto;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameComment = new JFrame();
		frameComment.setIconImage(Toolkit.getDefaultToolkit().getImage(CommentAlbum.class.getResource("/Imagenes/instagramx16.png")));
		frameComment.setBounds(100, 100, 915, 352);
		frameComment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameComment.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frameComment.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					muestraHome();
				} catch (ParseException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameComment.setVisible(false);
			}
		});
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		frameComment.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{420, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("NOMBRE DEL \u00C1LBUM");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Escribe un comentario (max 200 caracteres)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		//inserto imagen en El icon del panel (se puede hacer un variable de tipo String y llamarle Imagen, 
		//cogiendo esa imagen de la terminal cuando una vez hemos elegido la imagen)
		ImageIcon icono = new ImageIcon("src/Imagenes/sinFotoPerfil.png");
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		File file = frmFoto.getFotos().get(0);
		ImageIcon icon = new ImageIcon(file.getAbsolutePath());
		Image image = icon.getImage();
		int width = 300;
		int height = 300;
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		lblImagen.setIcon(new ImageIcon(scaledImage));
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.gridheight = 2;
		gbc_lblImagen.insets = new Insets(0, 0, 0, 5);
		gbc_lblImagen.gridx = 0;
		gbc_lblImagen.gridy = 2;
		panel_1.add(lblImagen, gbc_lblImagen);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.anchor = GridBagConstraints.SOUTH;
		gbc_textArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		panel_1.add(textArea, gbc_textArea);
		
		JButton btnNewButton = new JButton("Compartir");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String patron = "^#\\w{1,15}$";
				Pattern p = Pattern.compile(patron);
			    
				try {
					muestraHome();
				} catch (ParseException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				frameComment.setVisible(false);
				
				LinkedList<String> hashtags = new LinkedList<String>();
				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String cadenaToday = sdf.format(today);
				String hashtag[] = textArea.getText().split(" ");
				for (int i = 0; i < hashtag.length; i++) {
					if (p.matcher(hashtag[i]).matches()) {
						if (hashtags.size() < 4) {
							hashtags.add(hashtag[i].substring(1));
						}
					}
				}
				
			
					System.out.println("Se llama al controlador para crear album");
					Controlador.getUnicaInstancia().registrarAlbum(Controlador.getUnicaInstancia().getNombreUserUsuarioActual(), cadenaToday, textArea.getText(), hashtags, frmFoto.getFotos(), textField.getText());
				
				
				System.out.println("Fotos del usuario : ");
				
				try {
					LinkedList<Publicacion> lista = (LinkedList<Publicacion>) Controlador.getUnicaInstancia().recuperarPublicacionesUsuario(Controlador.getUnicaInstancia().getNombreUserUsuarioActual());
					for(Publicacion p1 : lista) {
						if(p1 instanceof Foto) {
							System.out.println("Foto -> " + ((Foto) p1).getFile());
						}
						else if(p1 instanceof Album) {
							LinkedList<Foto> list = (LinkedList<Foto>) ((Album) p1).getFotos();
							for(Foto f : list) {
								System.out.println("Album -> " + f.getFile());
							}
						}
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
	}

	//metodos privados
	
	private void muestraHome() throws ParseException, IOException {
		if (frmHome == null)
			frmHome = new Home(null,null,this);

		frmHome.muestra();
	}
	
}
