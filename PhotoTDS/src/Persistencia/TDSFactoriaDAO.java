package Persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {
	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorPublicacionDAO getPublicacionDAO() {
		return AdaptadorPublicacionTDS.getUnicaInstancia();
	}

}
