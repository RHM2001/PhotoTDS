package Vistas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Usuario;
import Utilidades.ImagenRedonda;

public class CasillaUsuario extends JPanel {
	private Usuario usuario;
    private String nombre;
    private String file;

    public CasillaUsuario(Usuario usuario) {
    	this.usuario = usuario;
        this.nombre = usuario.getNombre();
        this.file = usuario.getFotoPerfil();

        setLayout(new BorderLayout(0, 0));
        
        ImageIcon imageIcon;
        
        if(file == null) {
        	imageIcon = new ImageIcon(Home.class.getResource("/Imagenes/sinFotoPerfil.png"));
        }
        else {
        	imageIcon = new ImageIcon(file);
        }
        
        Image image = imageIcon.getImage();
		Image imagenRedimensionada = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);

		ImagenRedonda imagenRedonda = new ImagenRedonda(imagenRedimensionadaIcon);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setText("   " + nombre);
        lblNewLabel_1.setIcon(imagenRedonda);
        add(lblNewLabel_1);
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
    
    
}

