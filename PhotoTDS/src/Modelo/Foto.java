package Modelo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Foto extends Publicacion {
	
	private File file;
	private String album;
	private List<String> comentarios;

	public Foto(String usuario, String fecha, String descripcion, List<String> hashtag, File file, String album) {
		super(usuario, fecha, descripcion, hashtag);
		this.file = file;
		this.album = album;
		this.comentarios = new LinkedList<String>();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}
	
}
