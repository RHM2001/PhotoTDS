package Controlador;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Modelo.Album;
import Modelo.Foto;
import Modelo.Publicacion;
import Modelo.Usuario;
import Persistencia.DAOException;
import Persistencia.FactoriaDAO;
import Persistencia.IAdaptadorPublicacionDAO;
import Persistencia.IAdaptadorUsuarioDAO;
import Persistencia.RepositorioPublicaciones;
import Persistencia.RepositorioUsuarios;
import Utilidades.GenerarExcel;
import Utilidades.GenerarPDF;
import umu.tds.fotos.Cargador;
import umu.tds.fotos.FotosEvent;
import umu.tds.fotos.FotosListener;

public class Controlador implements FotosListener{

	private static Controlador unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private RepositorioUsuarios catalogoUsuarios;
	private IAdaptadorPublicacionDAO adaptadorPublicacion;
	private RepositorioPublicaciones catalogoPublicaciones;
	
	private Cargador cargadorFotos;

	// Usuario actual de la instancia
	private Usuario usuarioActual;

	private Controlador() {
		cargadorFotos = umu.tds.fotos.Cargador.getUnicaInstancia();
		inicializarAdaptadores();
		inicializarCatalogos();
	}

	// Patr�n singleton

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}

	public void registrarUsuario(String email, String nombreCompleto, String nombreUser, String password,
			String fechaNacimiento, String presentacionPerfil, String foto) {
		System.out.println("Foto en controlador : " + foto);
		Usuario usuario = new Usuario(email, nombreCompleto, nombreUser, password, fechaNacimiento, presentacionPerfil,
				foto);
		usuario.setUltimaConexion("");
		adaptadorUsuario.registrarUsuario(usuario);
	}

	// Comprobaci�n del registro de un usuario, podemos ver distintos errores al
	// registrar al usuario

	public int compruebaUsuarioRegistro(String usuario, String email) {
		for (Usuario usu : RepositorioUsuarios.getUnicaInstancia().getUsuarios()) {
			if (usu.getNombreUser().equals(usuario) && usu.getEmail().equals(email)) {
				// coinciden ambos
				return 3;
			} else if (usu.getNombreUser().equals(usuario)) {
				// usuario ya usado
				return 1;
			} else if (usu.getEmail().equals(email)) {
				// correo ya usado
				return 2;
			}
		}
		return 0;
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorPublicacion = factoria.getPublicacionDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = RepositorioUsuarios.getUnicaInstancia();
		catalogoPublicaciones = RepositorioPublicaciones.getUnicaInstancia();
	}

	public boolean existeUsuario(String user) {
		return RepositorioUsuarios.getUnicaInstancia().getUsuario(user) != null;
	}

	public List<Usuario> getClientes() {
		return catalogoUsuarios.getUsuarios();
	}

	// Loguea un usuario, asignando un usuario al usuario actual
	public boolean loginUsuario(String usuario, String contrasena) {
		for (Usuario usu : RepositorioUsuarios.getUnicaInstancia().getUsuarios()) {
			if (usu.getNombreUser().equals(usuario) && usu.getPassword().equals(contrasena)) {
				usuarioActual = usu;
				return true;
			}
		}
		return false;

	}

	// Obtener los atributos de un usuario

	public String getNombreUsuarioActual() {
		return usuarioActual.getNombre();
	}

	public String getNombreUserUsuarioActual() {
		return usuarioActual.getNombreUser();
	}

	public Integer getNumPublicacionesUsuarioActual() {
		return usuarioActual.getNumPublicaciones();
	}

	public String getDescripcionUsuarioActual() {
		return usuarioActual.getPresentacionPerfil();
	}

	public Integer getCodigoUsuarioActual() {
		return usuarioActual.getCodigo();
	}

	public String getFotoUsuarioActual() {
		return usuarioActual.getFotoPerfil();
	}

	public boolean getPremium() {
		return usuarioActual.getPremium();
	}

	public int getSeguidores() {
		return usuarioActual.getSeguidores().size();
	}

	public String getUltiaConexion() {
		return usuarioActual.getUltimaConexion();
	}

	public List<String> getSeguidoresList() {
		return usuarioActual.getSeguidores();
	}

	public int getSeguidos() {
		return usuarioActual.getSeguidos().size();
	}

	public String getFechaNacimUsuario() {
		return usuarioActual.getFecha_nacimiento();
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	// Acciones para las publicaciones

	public void registrarFoto(String usuario, String fecha, String descripcion, List<String> hashtag, File file) {
		Foto foto = new Foto(usuario, fecha, descripcion, hashtag, file, null);
		adaptadorPublicacion.registrarPublicacion(foto);
	}

	public void registrarAlbum(String usuario, String fecha, String descripcion, List<String> hashtag, List<File> files,
			String nombre) {
		LinkedList<Foto> fotos = new LinkedList<Foto>();
		for (File file : files) {
			Foto foto = new Foto(usuario, fecha, descripcion, hashtag, file, nombre);
			fotos.add(foto);
			adaptadorPublicacion.registrarPublicacion(foto);
		}
		Album album = new Album(usuario, fecha, descripcion, hashtag, fotos, nombre);
		adaptadorPublicacion.registrarPublicacion(album);
	}

	public List<Publicacion> recuperarPublicacionesUsuario(String usuario) throws ParseException {
		return adaptadorPublicacion.recuperarPublicacionesUsuario(usuario);
	}

	public List<Publicacion> recuperarFotosUsuario(String usurio) throws ParseException {
		return adaptadorPublicacion.recuperarFotosUsuario(usurio);
	}

	public List<Publicacion> recuperarAlbumesUsuario(String usurio) throws ParseException {
		return adaptadorPublicacion.recuperarAlbumesUsuario(usurio);
	}

	public void darMeGusta(Publicacion publicacion) throws ParseException {
		adaptadorPublicacion.darMeGusta(publicacion);
	}

	public void eliminarAlbum(Publicacion publicion) throws ParseException {
		adaptadorPublicacion.eliminarAlbum(publicion);
	}

	public void eliminarFoto(Foto foto) throws ParseException {
		adaptadorPublicacion.eliminarFoto(foto);
	}

	public void addFotoAlbum(List<File> fotos, Album album) throws ParseException {
		adaptadorPublicacion.addFotoAlbum(fotos, album);
	}

	public List<Usuario> recuperarUsuariosBusqueda(String busqueda) throws ParseException {
		return adaptadorUsuario.recuperarUsuariosBusqueda(busqueda);
	}

	public void seguirUsuario(Usuario usuario, String u) throws ParseException {
		adaptadorUsuario.seguirUsuario(usuario, u);
	}

	public List<String> buscarHashtags(List<String> hastags) throws ParseException {
		List<String> lista1 = adaptadorPublicacion.buscarHashtag(hastags);
		return adaptadorUsuario.buscarHashtag(lista1);
	}

	public void nuevoComentario(Foto foto, String comentario) throws ParseException {
		adaptadorPublicacion.nuevoComentario(foto, comentario);
	}

	public void editarPerfil(int codigoUsuario, String descripcion, String password, File file) {
		adaptadorUsuario.editarPerfil(codigoUsuario, descripcion, password, file);
	}

	public void serPremium(int codigoUsuario) {
		adaptadorUsuario.serPremium(codigoUsuario);
	}

	public Usuario obtenerUsuario(String usuario) {
		return adaptadorUsuario.recuperarUsuario(usuario);
	}

	public boolean descuentoEdad() {

		// El descuento de la edad va a ser:
		// - Joven -> entre 18 y 24 años
		// - Mayor de 65 años

		String fechaNacim = getFechaNacimUsuario();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNacimiento = LocalDate.parse(fechaNacim, formatter);

		LocalDate fechaActual = LocalDate.now();
		int edad = Period.between(fechaNacimiento, fechaActual).getYears();

		if ((edad >= 18 && edad <= 24) || edad > 65) {
			return true;
		} else {
			return false;
		}
	}

	public boolean descuentoMeGustas(String usuario) throws ParseException {
		return adaptadorPublicacion.descuentoMeGustas(usuario);
	}

	public void generarExcel() {
		List<Usuario> lista = adaptadorUsuario.recuperarUsuarios(getSeguidoresList());
		GenerarExcel.generarExcel(lista);
	}

	public void generarPDF() throws IOException {
		List<Usuario> lista = adaptadorUsuario.recuperarUsuarios(getSeguidoresList());
		GenerarPDF.generatePDF(lista);
	}

	public List<Publicacion> topFotos(String usuario) throws IOException, ParseException {
		return adaptadorPublicacion.topFotos(usuario);
	}

	public List<Publicacion> listaFotosHome() throws ParseException {
		int codigo = getCodigoUsuarioActual();
		List<String> seguidos = adaptadorUsuario.obtenerSeguidos(codigo);
		List<Publicacion> fotosSeguidos = adaptadorPublicacion.obtenerFotosHome(seguidos);

		int maxFotos = Math.min(20, fotosSeguidos.size());

		return fotosSeguidos.subList(0, maxFotos);
	}

	public HashMap<String, Integer> notificacion() throws ParseException {
		int codigo = getCodigoUsuarioActual();

		List<String> seguidos = adaptadorUsuario.obtenerSeguidos(codigo);

		return adaptadorPublicacion.notificacion(getUltiaConexion(), seguidos);
	}

	public void cambiarUltimaConexion(String fecha) {
		usuarioActual.setUltimaConexion(fecha);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}

	public void uploadPhotos(String archivo) {

		cargadorFotos.setArchivoFotos(archivo);

	}

	@Override
	public void notificarNuevasFotos(FotosEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}