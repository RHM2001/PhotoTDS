package Persistencia;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Modelo.Publicacion;
import Modelo.Usuario;

public class RepositorioPublicaciones {
	private Map<String, List<Publicacion>> publicaciones;
	private static RepositorioPublicaciones unicaInstancia;

	private FactoriaDAO dao;
	private IAdaptadorPublicacionDAO adaptadorPublicacion;

	private RepositorioPublicaciones() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorPublicacion = dao.getPublicacionDAO();
			publicaciones = new HashMap<String, List<Publicacion>>();
			this.cargarCatalogo();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Patrón singleton

	public static RepositorioPublicaciones getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new RepositorioPublicaciones();
		}
		return unicaInstancia;
	}
	
	/*devuelve todos las publicaciones*/
	public List<Publicacion> getPublicaciones(){
		ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
		for (List<Publicacion> listPubli : publicaciones.values()) {
			for(Publicacion publi : listPubli) {
				lista.add(publi);
			}
		}
		return lista;
	}
	
	public Publicacion getPublcacion(int codigo) {
		for(List<Publicacion> listPubli : publicaciones.values()) {
			for(Publicacion publi : listPubli) {
				if(publi.getCodigo() == codigo)
					return publi;
			}
		}
		return null;
	}
	
	public void addPublicacion(Publicacion publi) {
		if(publicaciones.containsKey(publi.getUsuario())) {
			publicaciones.get(publi.getUsuario()).add(publi);
		}
		else {
			ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
			lista.add(publi);
			publicaciones.put(publi.getUsuario(), lista);
		}
	}
	
	public void removePublicacion (Publicacion publi) {
		int i = 0;
		for(Publicacion p : publicaciones.get(publi.getUsuario())) {
			if(p == publi) {
				publicaciones.get(publi.getUsuario()).remove(i);
			}
			i++;
		}
	}
	
	public List<Publicacion> getPublicacionesUsuario(String usuario){
		return publicaciones.get(usuario);
	}

	/* Recupera todos las publicaciones para trabajar con ellos en memoria */
	private void cargarCatalogo() throws DAOException, ParseException {
		List<Publicacion> publicacionesBD = adaptadorPublicacion.recuperarTodasPublicaciones();
		for (Publicacion publi : publicacionesBD)
			addPublicacion(publi);
	}
}
