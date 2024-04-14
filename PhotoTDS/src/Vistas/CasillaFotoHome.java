package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.Controlador;
import Modelo.Album;
import Modelo.Foto;
import Modelo.Publicacion;
import Modelo.Usuario;
import Utilidades.ImagenRedonda;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CasillaFotoHome extends JPanel {
	private Publicacion foto;
	private InfoFotoPerfilUsuario frameFoto;
	private InfoFotoAlbumPerfilUsuario frameAlbum;
	
	private void muestraFoto(Foto foto) {
		if (frameFoto == null)
			frameFoto = new InfoFotoPerfilUsuario(foto);
		
		frameFoto.muestra(foto);
	}
	
	private void muestraAlbum(Foto foto) {
		if (frameAlbum == null)
			frameAlbum = new InfoFotoAlbumPerfilUsuario(foto);
		
		frameAlbum.muestra(foto);
	}

	public CasillaFotoHome(Publicacion fh) {
		this.foto = fh;

		ImageIcon imageIcon;

		imageIcon = new ImageIcon(((Foto) fh).getFile().getAbsolutePath());

		Image image = imageIcon.getImage();
		Image imagenRedimensionada = image.getScaledInstance(220, 120, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);

		JLabel lblNewLabel_1_1 = new JLabel("");

		lblNewLabel_1_1.setIcon(imagenRedimensionadaIcon);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblNewLabel_1_1);

		JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(panel_1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("Me gusta");
		btnNewButton.setIcon(new ImageIcon(CasillaFotoHome.class.getResource("/Imagenes/corazon.png")));

		JButton btnNewButton_1 = new JButton("Comentar");
		btnNewButton_1.setIcon(new ImageIcon(CasillaFotoHome.class.getResource("/Imagenes/comentario.png")));
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_2.add(btnNewButton);
		panel_2.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel(foto.getMeGusta() + " Me gusta");
		panel_2.add(lblNewLabel);

		JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_1.add(panel_3, BorderLayout.CENTER);

		Usuario usuario = Controlador.getUnicaInstancia().obtenerUsuario(foto.getUsuario());
		
		ImageIcon imageIcon2;

		if(usuario.getFotoPerfil() == null) {
			imageIcon2 = new ImageIcon(Home.class.getResource("/Imagenes/sinFotoPerfil.png"));
        }
        else {
        	imageIcon2 = new ImageIcon(usuario.getFotoPerfil());
        }

		Image image2 = imageIcon2.getImage();
		Image imagenRedimensionada2 = image2.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon2 = new ImageIcon(imagenRedimensionada2);
		
		ImagenRedonda imagenRedonda2 = new ImagenRedonda(imagenRedimensionadaIcon2);

		JLabel miniUsuario = new JLabel("");
		miniUsuario.setIcon(imagenRedonda2);
		miniUsuario.setText(" " + usuario.getNombreUser());
		panel_3.add(miniUsuario);
	}

	public Publicacion getFoto() {
		return foto;
	}

	public void setFoto(Publicacion foto) {
		this.foto = foto;
	}

	public InfoFotoPerfilUsuario getFrameFoto() {
		return frameFoto;
	}

	public void setFrameFoto(InfoFotoPerfilUsuario frameFoto) {
		this.frameFoto = frameFoto;
	}

	public InfoFotoAlbumPerfilUsuario getFrameAlbum() {
		return frameAlbum;
	}

	public void setFrameAlbum(InfoFotoAlbumPerfilUsuario frameAlbum) {
		this.frameAlbum = frameAlbum;
	}
	
	
}
