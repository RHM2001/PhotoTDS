 package Persistencia;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Modelo.Album;
import Modelo.Foto;
import Modelo.Publicacion;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorPublicacionTDS implements IAdaptadorPublicacionDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorPublicacionTDS unicaInstancia = null;
	
	// patron singleton

	public static AdaptadorPublicacionTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorPublicacionTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorPublicacionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/* cuando se registra un cliente se le asigna un identificador �nico */
	@Override
	public void registrarPublicacion(Publicacion publicacion) {
		Entidad ePublicacion = null;

		// si la entidad esta registrada no la registra de nuevo
		try {
			ePublicacion = servPersistencia.recuperarEntidad(publicacion.getCodigo());
		} catch (NullPointerException e) {
		}
		if (ePublicacion != null)
			return;

		// registrar primero los atributos que son objetos (serian las fotos a poner mas
		// adelante)

		if (publicacion instanceof Foto) {
			// System.out.println("OK - Foto");
			// crear entidad Publicacion
			ePublicacion = new Entidad();
			ePublicacion.setNombre("publicacion");
			ePublicacion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
					new Propiedad("fecha", publicacion.getFecha()), new Propiedad("usuario", publicacion.getUsuario()),
					new Propiedad("descripcion", publicacion.getDescripcion()),
					new Propiedad("hashtags", obtenerHashtags(publicacion.getHashtags())),
					new Propiedad("meGusta", String.valueOf(publicacion.getMeGusta())),
					new Propiedad("file", ((Foto) publicacion).getFile().toString()),
					new Propiedad("album", ((Foto) publicacion).getAlbum()),
					new Propiedad("comentarios", obtenerComentariosString(((Foto) publicacion).getComentarios())))));

		} else if (publicacion instanceof Album) {
			// System.out.println("OK - Album");
			// crear entidad Publicacion
			ePublicacion = new Entidad();
			ePublicacion.setNombre("publicacion");
			ePublicacion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
					new Propiedad("fecha", publicacion.getFecha()), new Propiedad("usuario", publicacion.getUsuario()),
					new Propiedad("descripcion", publicacion.getDescripcion()),
					new Propiedad("hashtags", obtenerHashtags(publicacion.getHashtags())),
					new Propiedad("meGusta", String.valueOf(publicacion.getMeGusta())),
					new Propiedad("file", obtenerCodigosDeFotos(((Album) publicacion).getFotos())),
					new Propiedad("nombre", (((Album) publicacion).getNombre())))));
		}

		// registrar entidad publicacion
		ePublicacion = servPersistencia.registrarEntidad(ePublicacion);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		publicacion.setCodigo(ePublicacion.getId());
	}

	@Override
	public void borrarPublicacion(Publicacion publicacion) {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(publicacion.getCodigo());

		servPersistencia.borrarEntidad(ePublicacion);

	}

	@Override
	public void modificarPublicacion(Publicacion publicacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public Publicacion recuperarPublicacion(int codigo) throws ParseException {

		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Publicacion) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		Entidad ePublicacion;

		String usuario;
		String fecha;
		String descripcion;
		String meGusta;
		String hashtag;
		String file;
		String nombre;
		String album;
		String comentarios;

		// recuperar entidad
		ePublicacion = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		usuario = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "usuario");
		fecha = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "fecha");
		descripcion = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "descripcion");
		meGusta = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "meGusta");
		hashtag = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "hashtags");
		file = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "file");
		nombre = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "nombre");
		album = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "album");
		comentarios = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "comentarios");

		System.out.println("El valor de file : " + file);
		System.out.println("Hashtags : " + hashtag);

		String[] files = file.split("\n");
		String[] hashtags = hashtag.split(" ");

		LinkedList<String> h = new LinkedList<String>();
		for (int i = 0; i < hashtags.length; i++) {
			h.add(hashtags[i]);
		}

		int meGustas = Integer.parseInt(meGusta);

		// System.out.println("Tamaño de la recuperacion : " + files.length);

		if (files.length > 1) {

			LinkedList<Foto> fotos = new LinkedList<Foto>();

			for (int i = 0; i < files.length; i++) {
				fotos.add((Foto) recuperarPublicacion(Integer.parseInt(files[i])));
			}
			System.out.println("NOMBRE DEL ALBUM : " + nombre);
			Album albumObj = new Album(usuario, fecha, descripcion, h, fotos, nombre);
			albumObj.setCodigo(codigo);
			albumObj.setMeGusta(meGustas);
			PoolDAO.getUnicaInstancia().addObjeto(codigo, albumObj);
			return albumObj;

		} else if (files.length == 1) {
			System.out.println("LA FOTO PERTENECE AL ABLUM : " + album);
			List<String> lista = obtenerComentariosList(comentarios);
			Foto foto = new Foto(usuario, fecha, descripcion, h, new File(files[0]), album);
			foto.setCodigo(codigo);
			foto.setMeGusta(meGustas);
			foto.setComentarios(lista);
			PoolDAO.getUnicaInstancia().addObjeto(codigo, foto);
			return foto;
		}

		return null;

	}

	@Override
	public void update(Publicacion p) throws ParseException {
		if (p instanceof Foto) {
			updateFoto((Foto) p);
		} else if (p instanceof Album) {
			updateAlbum((Album) p);
		}
	}

	@Override
	public void updateFoto(Foto p) throws ParseException {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(p.getCodigo());
		if (ePublicacion != null) {
			for (Propiedad prop : ePublicacion.getPropiedades()) {
				if (prop.getNombre().equals("usuario")) {
					prop.setValor(String.valueOf(p.getUsuario()));
				} else if (prop.getNombre().equals("fecha")) {
					prop.setValor(p.getFecha());
				} else if (prop.getNombre().equals("descripcion")) {
					prop.setValor(p.getDescripcion());
				} else if (prop.getNombre().equals("meGusta")) {
					prop.setValor(String.valueOf(p.getMeGusta()));
				} else if (prop.getNombre().equals("hashtags")) {
					prop.setValor(String.valueOf(p.getHashtags()));
				} else if (prop.getNombre().equals("file")) {
					prop.setValor((p.getFile().toString()));
				} else if (prop.getNombre().equals("album")) {
					prop.setValor(p.getAlbum());
				}  else if (prop.getNombre().equals("comentarios")) {
					prop.setValor(obtenerComentariosString(p.getComentarios()));
				}

				servPersistencia.modificarPropiedad(prop);
			}
		}

	}

	@Override
	public void updateAlbum(Album p) throws ParseException {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(p.getCodigo());

		for (Propiedad prop : ePublicacion.getPropiedades()) {
			if (prop.getNombre().equals("usuario")) {
				prop.setValor(String.valueOf(p.getUsuario()));
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(p.getFecha());
			} else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(p.getDescripcion());
			} else if (prop.getNombre().equals("meGusta")) {
				prop.setValor(String.valueOf(p.getMeGusta()));
			} else if (prop.getNombre().equals("hashtags")) {
				prop.setValor(String.valueOf(p.getHashtags()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor((p.getNombre()));
			} else if (prop.getNombre().equals("file")) {
				prop.setValor(obtenerCodigosDeFotos(p.getFotos()));
			}

			Publicacion a = recuperarPublicacion(ePublicacion.getId());

			for (Foto f : ((Album) a).getFotos()) {
				updateFoto(f);
			}

			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public List<Publicacion> recuperarTodasPublicaciones() throws ParseException {
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
		List<Publicacion> publicaciones = new LinkedList<Publicacion>();

		for (Entidad ePublicacion : ePublicaciones) {
			publicaciones.add(recuperarPublicacion(ePublicacion.getId()));
		}
		return publicaciones;
	}

	@Override
	public List<Publicacion> recuperarPublicacionesUsuario(String usuario) throws ParseException {
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
		List<Publicacion> publicaciones = new LinkedList<Publicacion>();

		for (Entidad ePublicacion : ePublicaciones) {
			if (recuperarPublicacion(ePublicacion.getId()).getUsuario().equals(usuario)) {
				publicaciones.add(recuperarPublicacion(ePublicacion.getId()));
			}
		}
		return publicaciones;
	}

	@Override
	public List<Publicacion> recuperarFotosUsuario(String usuario) throws ParseException {
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
		List<Publicacion> publicaciones = new LinkedList<Publicacion>();

		for (Entidad ePublicacion : ePublicaciones) {
			Publicacion publicacion = recuperarPublicacion(ePublicacion.getId());
			if (publicacion.getUsuario().equals(usuario)) {
				if (publicacion instanceof Foto && ((Foto) publicacion).getAlbum() == null) {
					publicaciones.add(recuperarPublicacion(ePublicacion.getId()));
				}
			}
		}
		return publicaciones;
	}
	
	@Override
	public List<Publicacion> recuperarFotosUsuario2(String usuario) throws ParseException {
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
		List<Publicacion> publicaciones = new LinkedList<Publicacion>();

		for (Entidad ePublicacion : ePublicaciones) {
			Publicacion publicacion = recuperarPublicacion(ePublicacion.getId());
			if (publicacion.getUsuario().equals(usuario)) {
				if (publicacion instanceof Foto) {
					publicaciones.add(recuperarPublicacion(ePublicacion.getId()));
				}
			}
		}
		return publicaciones;
	}

	@Override
	public List<Publicacion> recuperarAlbumesUsuario(String usuario) throws ParseException {
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
		List<Publicacion> publicaciones = new LinkedList<Publicacion>();

		for (Entidad ePublicacion : ePublicaciones) {
			Publicacion publicacion = recuperarPublicacion(ePublicacion.getId());
			if (publicacion.getUsuario().equals(usuario)) {
				if (publicacion instanceof Album) {
					publicaciones.add(recuperarPublicacion(ePublicacion.getId()));
				}
			}
		}
		return publicaciones;
	}

	@Override
	public Publicacion recuperarPublicacionFile(String file) throws ParseException {
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");

		for (Entidad ePublicacion : ePublicaciones) {
			Publicacion p = recuperarPublicacion(ePublicacion.getId());
			if (p instanceof Foto) {
				if (((Foto) p).getFile().toString().equals(file)) {
					return p;
				}
			}
		}
		return null;
	}

	@Override
	public void eliminarAlbum(Publicacion publicacion) throws ParseException {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(publicacion.getCodigo());

		if (publicacion instanceof Album) {
			((Album) publicacion).getFotos().stream().forEach(foto -> {
				try {
					getUnicaInstancia().eliminarAlbum(foto);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

		servPersistencia.borrarEntidad(ePublicacion);
	}

	@Override
	public void eliminarFoto(Foto foto) throws ParseException {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(foto.getCodigo());
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");

		if (foto.getAlbum() != null) {
			for (Entidad e : ePublicaciones) {
				Publicacion a = recuperarPublicacion(e.getId());
				if (a instanceof Album && ((Album) a).getNombre().equals(foto.getAlbum())) {
					((Album) a).getFotos().remove(foto);
					servPersistencia.borrarEntidad(ePublicacion);
					update(a);
				}
			}
		} else {
			servPersistencia.borrarEntidad(ePublicacion);
		}

	}

	@Override
	public void darMeGusta(Publicacion publicacion) throws ParseException {
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
		for (Entidad ePublicacion : ePublicaciones) {
			Publicacion p = recuperarPublicacion(ePublicacion.getId());
			if (p.equals(publicacion)) {
				if (p instanceof Album) {
					p.incrementarMeGusta();
					for (Foto f : ((Album) p).getFotos()) {
						f.incrementarMeGusta();
					}
				} else {
					p.incrementarMeGusta();
				}

				update(p);
			}
		}
	}

	@Override
	public void addFotoAlbum(List<File> fotos, Album album) throws ParseException {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(album.getCodigo());
		Publicacion p = recuperarPublicacion(ePublicacion.getId());
		for (File f : fotos) {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String cadenaToday = sdf.format(today);
			Foto foto = new Foto(album.getNombre(), cadenaToday, album.getDescripcion(), null, f, album.getNombre());
			((Album) p).getFotos().add(foto);
		}
		update(p);
	}
	
	@Override
	public List<String> buscarHashtag(List<String> busqueda) throws ParseException {
		List<String> hashtags = new LinkedList<String>();
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
		
		for(Entidad e : ePublicaciones) {
			Publicacion publicacion = recuperarPublicacion(e.getId());
			if(publicacion instanceof Foto) {
				List<String> lista = publicacion.getHashtags();
				for(String h : busqueda) {
					for(String h_aux : lista) {
						if(h_aux.contains(h)) {
							String nuevo = h_aux + ";" + publicacion.getUsuario();
							System.out.println("Adap. Publicacion " + nuevo);
							hashtags.add(nuevo);
						}
					}
				}
			}
		}
		
		return hashtags;
	}
	
	@Override
	public void nuevoComentario(Foto foto, String comentario) throws ParseException {
		Entidad entidad = servPersistencia.recuperarEntidad(foto.getCodigo());
		Publicacion p = recuperarPublicacion(entidad.getId());
		((Foto) p).getComentarios().add(comentario);
		update(p);
	}
	
	@Override
	public boolean descuentoMeGustas(String usuario) throws ParseException {
		
		// Para el descuento de "me gustas", si tiene minimo dos fotos con mas de dos likes tiene el descuento.
		
		int aux = 0;
		List<Publicacion> fotos = recuperarFotosUsuario(usuario);
		
		for(Publicacion p : fotos) {
			if(p.getMeGusta() >= 2) {
				aux++;
			}
		}
		
		if(aux >= 2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public List<Publicacion> topFotos(String usuario) throws ParseException{
		List<Publicacion> fotos = recuperarFotosUsuario(usuario);
		
		
		Collections.sort(fotos);
		
		return fotos.subList(0, Math.min(fotos.size(), 10));

	}
	
	@Override
	public List<Publicacion> obtenerFotosHome(List<String> seguidos) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
	    List<Publicacion> fotosHome = new LinkedList<>();

	    for (String seguido : seguidos) {
	        List<Publicacion> fotosUsuario = recuperarFotosUsuario2(seguido);
	        fotosHome.addAll(fotosUsuario);
	    }

	    // Ordenar las publicaciones por fecha
	    Collections.sort(fotosHome, new Comparator<Publicacion>() {
	        @Override
	        public int compare(Publicacion p1, Publicacion p2) {
	        	String fechaString1 = p1.getFecha();
	            Date fechaDate1 = null;
				try {
					fechaDate1 = dateFormat.parse(fechaString1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            String fechaString2 = p2.getFecha();
	            Date fechaDate2 = null;
				try {
					fechaDate2 = dateFormat.parse(fechaString2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return fechaDate2.compareTo(fechaDate1);
	        }
	    });

	    return fotosHome;
	}
	
	@Override
	public HashMap<String, Integer> notificacion(String ultimaConexion, List<String> usuarios) throws ParseException {
		HashMap<String, Integer> notificaciones = new HashMap<String, Integer>();
		if(!ultimaConexion.equals("")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date ultimaConex = dateFormat.parse(ultimaConexion);
			int num;
			
			for(String usuario : usuarios) {
				num = 0;
				List<Publicacion> fotos = recuperarFotosUsuario2(usuario);
				for(Publicacion f : fotos) {
					Date fecha = dateFormat.parse(f.getFecha());
					if(fecha.compareTo(ultimaConex) > 0) {
						num++;
					}
				}
				if(num > 0) {
					notificaciones.put(usuario, num);
				}
			}
		}
		
		
		return notificaciones;
	}



	// -------------------Funciones auxiliares-----------------------------

	private String obtenerCodigosDeFotos(List<Foto> fotos) {
		String aux = "";
		for (Foto f : fotos) {
			aux += String.valueOf(f.getCodigo()) + "\n";
		}
		return aux.trim();
	}

	private String obtenerHashtags(List<String> hashtags) {
		String aux = "";
		for (String h : hashtags) {
			aux += h.toString() + " ";
		}
		return aux.trim();
	}
	
	private String obtenerComentariosString(List<String> comentarios) {
		String cadena = "";
		
		for(String c : comentarios) {
			cadena = cadena + ";" + c;
		}
		
		return cadena;
	}
	
	private List<String> obtenerComentariosList(String comentarios) {
		String[] array = comentarios.split(";");
		
		List<String> lista = new LinkedList<String>();
		
		for(String c : array) {
			lista.add(c);
		}
		
		return lista;
	}
}
