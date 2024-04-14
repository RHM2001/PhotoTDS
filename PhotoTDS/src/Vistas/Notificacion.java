package Vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.util.HashMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Controlador.Controlador;
import Modelo.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Notificacion {

	private JFrame frame;
	private DefaultListModel<JPanel> modeloListaUsuarios = new DefaultListModel<>();

	public void muestra() {
		frame.setVisible(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notificacion window = new Notificacion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public Notificacion() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 310, 260);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("NOTIFICACIÓN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Notificacion.class.getResource("/Imagenes/ojo-rojo.png")));
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);

		HashMap<String, Integer> usuarios = Controlador.getUnicaInstancia().notificacion();

		JList<JPanel> listaUsuarios = new JList<>(modeloListaUsuarios);
		listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaUsuarios.setCellRenderer(new PanelRenderer());

		DefaultListModel<JPanel> listModel = (DefaultListModel<JPanel>) listaUsuarios.getModel();
		for (String usuario : usuarios.keySet()) {
			Usuario u = Controlador.getUnicaInstancia().obtenerUsuario(usuario);
			CasillaNotificacion casilla = new CasillaNotificacion(u, usuarios.get(usuario));
			listModel.addElement(casilla);
		}

		JScrollPane scrollPane = new JScrollPane(listaUsuarios);
		panel_2.add(scrollPane);
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
