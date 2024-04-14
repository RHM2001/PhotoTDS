package Persistencia;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import Modelo.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario (Usuario usuario);
	public void borrarUsuario (Usuario usuario);
	public void modificarUsuario (Usuario usuario);
	public Usuario recuperarUsuario(int codigo);
	public List<Usuario> recuperarTodosUsuarios() throws ParseException;
	public List<Usuario> recuperarUsuariosBusqueda(String busqueda) throws ParseException;
	public void seguirUsuario(Usuario usuario, String u);
	public Usuario recuperarUsuario(String nombreUsuario);
	public List<String> buscarHashtag(List<String> lista1);
	public void editarPerfil(int codigoUsuario, String descripcion, String password, File file);
	public void serPremium(int codigoUsuario);
	public List<Usuario> recuperarUsuarios(List<String> usuarios);
	public List<String> obtenerSeguidos(int codigoUsuario);
	
}
