package Vistas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Foto;
import Modelo.Usuario;
import Utilidades.ImagenRedonda;

public class CasillaTopFoto extends JPanel {
	private Foto foto;
	private int likes;
	private String file;

	public CasillaTopFoto(Foto foto) {
		this.foto = foto;
		this.likes = foto.getMeGusta();
		this.file = foto.getFile().getAbsolutePath();

		setLayout(new BorderLayout(0, 0));

		ImageIcon imageIcon;

		imageIcon = new ImageIcon(file);

		Image image = imageIcon.getImage();
		Image imagenRedimensionada = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);

		ImagenRedonda imagenRedonda = new ImagenRedonda(imagenRedimensionadaIcon);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setText("   " + likes + " likes");
		lblNewLabel_1.setIcon(imagenRedonda);
		add(lblNewLabel_1);
	}

}
