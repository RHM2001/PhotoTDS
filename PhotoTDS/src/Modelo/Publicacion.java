package Modelo;

import java.util.Date;
import java.util.List;

public class Publicacion implements Comparable<Publicacion> {

	private int codigo;
	private String usuario;
	private String fecha;
	private String descripcion;
	private Integer meGusta;
	private List<String> hashtags;
	
	public Publicacion(String usuario, String fecha, String descripcion, List<String> hashtags) {
		this.codigo = 0;
		this.usuario = usuario;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.meGusta = 0;
		this.hashtags = hashtags;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getMeGusta() {
		return meGusta;
	}

	public void setMeGusta(Integer meGusta) {
		this.meGusta = meGusta;
	}
	
	// Metodos 
	
	public void incrementarMeGusta() {
		this.meGusta++;
	}
	
	@Override
	public int compareTo(Publicacion o) {
		return Integer.compare(o.getMeGusta(), this.getMeGusta());
	}
}
