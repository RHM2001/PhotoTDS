package Vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Controlador.Controlador;
import Modelo.Usuario;

public class BusquedaUsuarios {

	private JFrame frameBusquedaUsuarios;
	private PerfilUsuario framePerfilUsuario;
	private String busqueda;
	private DefaultListModel<JPanel> modeloListaUsuarios;

	void muestra(String busqueda) throws ParseException {
		this.busqueda = busqueda;
		modeloListaUsuarios = new DefaultListModel<>();
		initialize();
		frameBusquedaUsuarios.setVisible(true);
		frameBusquedaUsuarios.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaUsuarios window = new BusquedaUsuarios(null);
					window.frameBusquedaUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BusquedaUsuarios(String busqueda) throws ParseException {
		this.busqueda = busqueda;
		modeloListaUsuarios = new DefaultListModel<>();
		initialize();
	}

	private void initialize() throws ParseException {
		frameBusquedaUsuarios = new JFrame();
		frameBusquedaUsuarios.setResizable(false);
		frameBusquedaUsuarios.setBounds(100, 100, 310, 465);

		JPanel panel = new JPanel();
		frameBusquedaUsuarios.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameBusquedaUsuarios.setVisible(false);
			}
		});
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		frameBusquedaUsuarios.getContentPane().add(panel_1, BorderLayout.CENTER);

		List<Usuario> usuarios = Controlador.getUnicaInstancia().recuperarUsuariosBusqueda(busqueda);
		usuarios.remove(Controlador.getUnicaInstancia().getUsuarioActual());

		JList<JPanel> listaUsuarios = new JList<>(modeloListaUsuarios);
		listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaUsuarios.setCellRenderer(new PanelRenderer());

		DefaultListModel<JPanel> listModel = (DefaultListModel<JPanel>) listaUsuarios.getModel();
		for (Usuario usuario : usuarios) {
			CasillaUsuario casilla = new CasillaUsuario(usuario);
			listModel.addElement(casilla);
		}

		JScrollPane scrollPane = new JScrollPane(listaUsuarios);
		panel_1.add(scrollPane);

		JPanel panel_2 = new JPanel();
		frameBusquedaUsuarios.getContentPane().add(panel_2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Lista de usuarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblNewLabel);
		
		listaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) { // Detectar un solo clic
					int index = listaUsuarios.locationToIndex(e.getPoint());
					if (index >= 0) {
						JPanel selectedPanel = listaUsuarios.getModel().getElementAt(index);
						CasillaUsuario casillaUsuario = (CasillaUsuario) selectedPanel;
						Usuario usuario = casillaUsuario.getUsuario();
						
						try {
							System.out.println("Nombre del usuario : " + usuario.getNombre());
							muestraBusquedaUsuarios(usuario);
						} catch (ParseException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
	}
	
	private void muestraBusquedaUsuarios(Usuario usuario) throws ParseException {
		if (framePerfilUsuario == null)
			framePerfilUsuario = new PerfilUsuario(usuario);
		
		framePerfilUsuario.muestra(usuario);
	}

	private static class PanelRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JPanel panel = (JPanel) value;
			panel.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
			panel.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
			
			panel.setPreferredSize(new Dimension(200, panel.getPreferredSize().height));
			
			return panel;
		}
	}
}
