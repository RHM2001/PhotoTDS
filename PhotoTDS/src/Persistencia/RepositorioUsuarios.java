package Persistencia;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Modelo.Usuario;

public class RepositorioUsuarios {
	private Map<String,Usuario> usuarios; 
	private static RepositorioUsuarios unicaInstancia;
	
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	
	private RepositorioUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuario = dao.getUsuarioDAO();
			usuarios = new HashMap<String, Usuario>();
			this.cargarCatalogo();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Patrón singleton
	
	public static RepositorioUsuarios getUnicaInstancia() {
		if(unicaInstancia == null) {
			unicaInstancia = new RepositorioUsuarios();
		}
		return unicaInstancia;
	}
	
	/*devuelve todos los usuarios*/
	public List<Usuario> getUsuarios(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario u:usuarios.values())
			lista.add(u);
		return lista;
	}
	
	public Usuario getUsuario(int codigo) {
		for(Usuario u : usuarios.values()) {
			if(u.getCodigo()==codigo)
				return u;
		}
		return null;
	}
	
	public Usuario getUsuario(String user) {
		return usuarios.get(user);
	}
	
	public void addUsuario(Usuario user) {
		usuarios.put(user.getNombreUser(), user);
	}
	
	public void removeUsuario (Usuario user) {
		usuarios.remove(user.getNombreUser());
	}
	
	/*Recupera todos los clientes para trabajar con ellos en memoria*/
	private void cargarCatalogo() throws DAOException, ParseException {
		 List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		 for (Usuario user: usuariosBD) 
			     usuarios.put(user.getNombreUser(),user);
	}
	
}
