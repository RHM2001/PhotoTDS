package Persistencia;

import java.io.File;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import Modelo.Album;
import Modelo.Foto;
import Modelo.Publicacion;
import Modelo.Usuario;

public interface IAdaptadorPublicacionDAO {

	public void registrarPublicacion (Publicacion publicacion);
	public void borrarPublicacion (Publicacion publicacion);
	public void modificarPublicacion (Publicacion publicacion);
	public Publicacion recuperarPublicacion(int codigo) throws ParseException;
	public List<Publicacion> recuperarTodasPublicaciones() throws ParseException;
	public List<Publicacion> recuperarPublicacionesUsuario(String usuario) throws ParseException;
	public List<Publicacion> recuperarFotosUsuario(String usuario) throws ParseException;
	public List<Publicacion> recuperarAlbumesUsuario(String usuario) throws ParseException;
	public Publicacion recuperarPublicacionFile(String file) throws ParseException;
	public void update(Publicacion p) throws ParseException;
	public void updateFoto(Foto p) throws ParseException;
	public void updateAlbum(Album p) throws ParseException;
	public void eliminarAlbum(Publicacion publicacion) throws ParseException;
	public void darMeGusta(Publicacion publicacion) throws ParseException;
	public void eliminarFoto(Foto foto) throws ParseException;
	public void addFotoAlbum(List<File> fotos, Album album) throws ParseException;
	public List<String> buscarHashtag(List<String> busqueda) throws ParseException;
	public void nuevoComentario(Foto foto, String comentario) throws ParseException;
	public boolean descuentoMeGustas(String usuario) throws ParseException;
	public List<Publicacion> topFotos(String usuario) throws ParseException;
	public List<Publicacion> obtenerFotosHome(List<String> seguidos) throws ParseException;
	public List<Publicacion> recuperarFotosUsuario2(String usuario) throws ParseException;
	public HashMap<String, Integer> notificacion(String ultimaConexion, List<String> usuarios) throws ParseException;
	
}
