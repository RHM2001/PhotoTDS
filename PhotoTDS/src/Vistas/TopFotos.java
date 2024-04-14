package Vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
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
import Modelo.Foto;
import Modelo.Publicacion;
import Modelo.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopFotos {

	private JFrame frameTopFotos;
	private List<Publicacion> fotos;
	private DefaultListModel<JPanel> modeloTopFotos;

	public void muestra() {
		frameTopFotos.setVisible(true);
		frameTopFotos.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopFotos window = new TopFotos(null);
					window.frameTopFotos.setVisible(true);
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
	 * @throws IOException
	 */
	public TopFotos(String usuario) throws IOException, ParseException {
		modeloTopFotos = new DefaultListModel<>();
		this.fotos = Controlador.getUnicaInstancia().topFotos(usuario);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTopFotos = new JFrame();
		frameTopFotos.setResizable(false);
		frameTopFotos.setBounds(100, 100, 320, 400);
		frameTopFotos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		frameTopFotos.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Top 10 fotos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		frameTopFotos.getContentPane().add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameTopFotos.setVisible(false);
			}
		});
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		frameTopFotos.getContentPane().add(panel_2, BorderLayout.CENTER);

		JList<JPanel> topFotos = new JList<>(modeloTopFotos);
		topFotos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		topFotos.setCellRenderer(new PanelRenderer());

		DefaultListModel<JPanel> listModel = (DefaultListModel<JPanel>) topFotos.getModel();
		for (Publicacion foto : fotos) {
			System.out.println("foto del list : " + ((Foto) foto).getFile().getAbsolutePath());
			CasillaTopFoto casilla = new CasillaTopFoto((Foto) foto);
			listModel.addElement(casilla);
		}

		JScrollPane scrollPane = new JScrollPane(topFotos);
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
