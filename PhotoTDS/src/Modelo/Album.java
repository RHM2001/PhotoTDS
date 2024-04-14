package Modelo;

import java.util.Date;
import java.util.List;

public class Album extends Publicacion{

	private String nombre;
	private List<Foto> fotos;
	
	public Album(String usuario, String fecha, String descripcion, List<String> hashtag, List<Foto> fotos, String nombre) {
		super(usuario, fecha, descripcion, hashtag);
		this.nombre = nombre;
		this.fotos = fotos;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
}
