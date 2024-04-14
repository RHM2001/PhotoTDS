import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import Controlador.Controlador;
import Modelo.Usuario;

public class testPhotoTDS {

	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}

	@Test
	public void testSeguirUsuario() throws ParseException {

		String u1 = "carlos";
		String u2 = "raul";
		
		Usuario carlos = Controlador.getUnicaInstancia().obtenerUsuario(u1);
		Usuario raul = Controlador.getUnicaInstancia().obtenerUsuario(u2);
		Controlador.getUnicaInstancia().seguirUsuario(carlos, u2);

		assertTrue(raul.getSeguidores().contains(u1));
	}
	
	@Test
	public void testSerPremium() throws ParseException {

		String u1 = "carlos";

		Usuario carlos1 = Controlador.getUnicaInstancia().obtenerUsuario(u1);
		
		Controlador.getUnicaInstancia().serPremium(carlos1.getCodigo());
		
		Usuario carlos2 = Controlador.getUnicaInstancia().obtenerUsuario(u1);

		assertTrue(carlos2.getPremium() == true);
	}
	
	@Test
	public void testBusquedaUsuario() throws ParseException {

		String u1 = "carlos";
		String u2 = "raul";

		Usuario raul = Controlador.getUnicaInstancia().obtenerUsuario(u2);
		
		Controlador.getUnicaInstancia().loginUsuario(u1, u1);
		
		List<Usuario> lista = Controlador.getUnicaInstancia().recuperarUsuariosBusqueda(u2);

		assertTrue(lista.contains(raul));
	}
	
	@Test
	public void testCambiarDescripcion() throws ParseException {

		String u1 = "carlos";

		Usuario carlos1 = Controlador.getUnicaInstancia().obtenerUsuario(u1);
		
		Controlador.getUnicaInstancia().loginUsuario(u1, u1);
		
		Controlador.getUnicaInstancia().editarPerfil(carlos1.getCodigo(), "Nueva descripción", null, null);
		
		Usuario carlos2 = Controlador.getUnicaInstancia().obtenerUsuario(u1);

		assertTrue(carlos2.getPresentacionPerfil().equals("Nueva descripción"));
	}
	
	@Test
	public void testLogin() {
		String u1 = "carlos";
		
		assertTrue(Controlador.getUnicaInstancia().loginUsuario(u1, u1) == true);
	}
	
	
	

}
