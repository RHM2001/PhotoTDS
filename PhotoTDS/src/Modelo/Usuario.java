package Modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {

	private int codigo;
	private String email;
	private String nombre;
	private String nombre_user;
	private String fecha_nacimiento;
	private String password;
	private Boolean premium;
	private String presentacion_perfil;
	private String foto_perfil;
	private List<Publicacion> publicaciones;
	private List<String> seguidores;
	private List<String> seguidos;
	private String ultimaConexion;

	public Usuario(String email, String nombre, String nombre_user, String password, String fecha_nacimiento, String presentacion_perfil, String foto_perfil) {
		codigo = 0;
		this.email = email;
		this.nombre = nombre;
		this.nombre_user = nombre_user;
		this.password = password;
		this.fecha_nacimiento = fecha_nacimiento;
		this.premium = false;
		this.publicaciones = new LinkedList<Publicacion>();
		this.presentacion_perfil = presentacion_perfil;
		this.foto_perfil = foto_perfil;
		this.seguidores = new LinkedList<String>();
		this.seguidos = new LinkedList<String>();
		this.ultimaConexion = null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUser() {
		return nombre_user;
	}

	public void setNombreUser(String nombre_user) {
		this.nombre_user = nombre_user;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Boolean getPremium() {
		return premium;
	}
	
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}
	
	public String getFotoPerfil() {
		return foto_perfil;
	}
	
	public void setFotoPerfil(String foto_perfil) {
		this.foto_perfil = foto_perfil;
	}
	
	public String getPresentacionPerfil() {
		return this.presentacion_perfil;
	}
	
	public void setPresentacionPerfil(String presentacion_perfil) {
		this.presentacion_perfil = presentacion_perfil; 
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	
	public List<String> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<String> seguidores) {
		this.seguidores = seguidores;
	}

	public List<String> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(List<String> seguidos) {
		this.seguidos = seguidos;
	}

	// Propiedad calculada
	public Integer getNumPublicaciones() {
		return this.publicaciones.size();
	}

	public String getUltimaConexion() {
		return ultimaConexion;
	}

	public void setUltimaConexion(String ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
	}
	
	

}
