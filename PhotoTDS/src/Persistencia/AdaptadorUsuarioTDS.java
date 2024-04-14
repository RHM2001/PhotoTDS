package Persistencia;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Modelo.Usuario;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;

	// patron singleton

	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/* cuando se registra un cliente se le asigna un identificador �nico */
	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = null;

		// si la entidad esta registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {
		}
		if (eUsuario != null)
			return;

		// registrar primero los atributos que son objetos (serian las fotos a poner mas
		// adelante)

		// crear entidad Usuario
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("email", usuario.getEmail()),
				new Propiedad("nombre", usuario.getNombre()), new Propiedad("nombre_usuario", usuario.getNombreUser()),
				new Propiedad("fecha_nacimiento", usuario.getFecha_nacimiento()),
				new Propiedad("contrasena", usuario.getPassword()),
				new Propiedad("presentacion_perfil", usuario.getPresentacionPerfil()),
				new Propiedad("foto_perfil", usuario.getFotoPerfil()),
				new Propiedad("premium", Boolean.toString(usuario.getPremium())),
				new Propiedad("ultimaConexion", usuario.getUltimaConexion()),
				new Propiedad("seguidores", listaAString(usuario.getSeguidores())),
				new Propiedad("seguidos", listaAString(usuario.getSeguidos())))));

		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setCodigo(eUsuario.getId());
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		// No se comprueban restricciones de integridad con Venta (o FOto en nuestro
		// caso)
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		servPersistencia.borrarEntidad(eUsuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {

		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(usuario.getCodigo()));
			} else if (prop.getNombre().equals("email")) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals("nombre_usario")) {
				prop.setValor(usuario.getNombreUser());
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(usuario.getFecha_nacimiento());
			} else if (prop.getNombre().equals("contrasena")) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals("presentacion_perfil")) {
				prop.setValor(usuario.getPresentacionPerfil());
			} else if (prop.getNombre().equals("foto_perfil")) {
				prop.setValor(usuario.getFotoPerfil());
			} else if (prop.getNombre().equals("premium")) {
				prop.setValor(Boolean.toString(usuario.getPremium()));
			} else if (prop.getNombre().equals("ultimaConexion")) {
				prop.setValor(usuario.getUltimaConexion());
			} else if (prop.getNombre().equals("seguidores")) {
				prop.setValor(listaAString(usuario.getSeguidores()));
			} else if (prop.getNombre().equals("seguidos")) {
				prop.setValor(listaAString(usuario.getSeguidos()));
			}

			servPersistencia.modificarPropiedad(prop);

		}
	}

	@Override
	public Usuario recuperarUsuario(int codigo) {

		// Si la entidad est� en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos
		Entidad eUsuario;
		// List<Venta> ventas = new LinkedList<Venta>();
		String email;
		String nombre;
		String nombre_usuario;
		String contrasena;
		String fecha_nacimiento;
		String presentacion_perfil;
		String foto_perfil;
		String premium;
		String seguidores;
		String seguidos;
		String ultimaConexion;

		// recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		nombre_usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre_usuario");
		contrasena = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contrasena");
		fecha_nacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fecha_nacimiento");
		presentacion_perfil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "presentacion_perfil");
		foto_perfil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "foto_perfil");
		premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium");
		seguidores = servPersistencia.recuperarPropiedadEntidad(eUsuario, "seguidores");
		seguidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "seguidos");
		ultimaConexion = servPersistencia.recuperarPropiedadEntidad(eUsuario, "ultimaConexion");

		Usuario usuario = new Usuario(email, nombre, nombre_usuario, contrasena, fecha_nacimiento, presentacion_perfil,
				foto_perfil);
		usuario.setPremium(Boolean.valueOf(premium));
		usuario.setCodigo(codigo);
		usuario.setSeguidores(stringALista(seguidores));
		usuario.setSeguidos(stringALista(seguidos));
		usuario.setUltimaConexion(ultimaConexion);

		PoolDAO.getUnicaInstancia().addObjeto(codigo, usuario);

		return usuario;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {

		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}

	@Override
	public Usuario recuperarUsuario(String nombreUsuario) {

		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");

		for (Entidad eUsuario : eUsuarios) {
			Usuario u = recuperarUsuario(eUsuario.getId());
			if (u.getNombreUser().equals(nombreUsuario)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public List<Usuario> recuperarUsuarios(List<String> usuarios) {

		List<Usuario> aux = new LinkedList<Usuario>();

		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");

		for (String nombreUsuario : usuarios) {
			for (Entidad eUsuario : eUsuarios) {
				Usuario u = recuperarUsuario(eUsuario.getId());
				if (u.getNombreUser().equals(nombreUsuario)) {
					aux.add(u);
				}
			}
		}

		return aux;
	}

	@Override
	public List<Usuario> recuperarUsuariosBusqueda(String busqueda) {

		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			Usuario u = recuperarUsuario(eUsuario.getId());
			if (u.getNombre().contains(busqueda)) {
				usuarios.add(u);
			}
		}
		return usuarios;
	}

	@Override
	public void seguirUsuario(Usuario usuario, String u) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		Usuario aux = recuperarUsuario(eUsuario.getId());
		if (!aux.getSeguidos().contains(u)) {
			aux.getSeguidos().add(u);
			modificarUsuario(aux);
		}

		List<Entidad> eUsuarios2 = servPersistencia.recuperarEntidades("usuario");

		for (Entidad eUsuario2 : eUsuarios2) {
			Usuario aux2 = recuperarUsuario(eUsuario2.getId());
			if (aux2.getNombreUser().equals(u)) {
				if (!aux2.getSeguidores().contains(aux.getNombreUser())) {
					aux2.getSeguidores().add(aux.getNombreUser());
					modificarUsuario(aux2);
				}
				break;
			}
		}
	}

	@Override
	public void editarPerfil(int codigoUsuario, String descripcion, String password, File file) {
	    Entidad eUsuario = servPersistencia.recuperarEntidad(codigoUsuario);
	    Usuario aux = recuperarUsuario(eUsuario.getId());

	    if (descripcion != null) {
	        aux.setPresentacionPerfil(descripcion);
	    }

	    if (file != null) {
	        aux.setFotoPerfil(file.getAbsolutePath());
	    }

	    if (password != null && !password.equals("")) {
	        aux.setPassword(password);
	    }

	    modificarUsuario(aux);
	}


	@Override
	public void serPremium(int codigoUsuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(codigoUsuario);
		Usuario aux = recuperarUsuario(eUsuario.getId());
		aux.setPremium(true);

		modificarUsuario(aux);
	}
	
	@Override
	public List<String> obtenerSeguidos(int codigoUsuario){
		Entidad eUsuario = servPersistencia.recuperarEntidad(codigoUsuario);
		Usuario aux = recuperarUsuario(eUsuario.getId());
		
		List<String> seguidos = aux.getSeguidos();
//		/seguidos.add(aux.getNombreUser());
		
		return seguidos;
	}

	// -------------------Funciones auxiliares-----------------------------

	public String obtenerFechaString(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaComoCadena = sdf.format(fecha);
		return fechaComoCadena;
	}

	public Date obtenerFechaDate(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaComoDate = sdf.parse(fecha);
		return fechaComoDate;
	}

	public String listaAString(List<String> usuarios) {

		String cadena = "";
		for (String u : usuarios) {
			cadena = cadena + ";" + u;
		}
		return cadena;
	}

	public List<String> stringALista(String cadena) {
		List<String> lista = new LinkedList<String>();

		if (!cadena.isEmpty()) {
			String[] partes = cadena.split(";");

			for (String parte : partes) {
				if (!parte.isEmpty()) {
					lista.add(parte);
				}
			}
		}

		return lista;
	}

	public List<String> buscarHashtag(List<String> lista1) {
		List<String> jlist = new LinkedList<String>();

		for (String l : lista1) {
			String[] splited = l.split(";");

			Usuario u = recuperarUsuario(splited[1]);

			String aux = splited[0] + " -> " + u.getSeguidores().size();

			System.out.println("Adap. Usuario " + aux);

			jlist.add(aux);
		}

		return jlist;
	}

}
