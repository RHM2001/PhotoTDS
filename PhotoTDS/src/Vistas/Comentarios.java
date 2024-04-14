package Vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Modelo.Foto;

public class Comentarios {

	private JFrame frameComentarios;
	private Foto foto;
	private DefaultListModel<String> modeloListaComentarios;

	void muestra(Foto foto) {
		this.foto = foto;
		initialize();
		frameComentarios.setVisible(true);
		frameComentarios.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comentarios window = new Comentarios(null);
					window.frameComentarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Comentarios(Foto foto) {
		this.foto = foto;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameComentarios = new JFrame();
		frameComentarios.setType(Type.POPUP);
		frameComentarios.setResizable(false);
		frameComentarios.setIconImage(
				Toolkit.getDefaultToolkit().getImage(CommentPhoto.class.getResource("/Imagenes/instagramx16.png")));
		frameComentarios.setBounds(100, 100, 345, 269);
		frameComentarios.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frameComentarios.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Comentarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		frameComentarios.getContentPane().add(panel_1, BorderLayout.CENTER);

		modeloListaComentarios = new DefaultListModel<>();
		JList<String> listaComentarios = new JList<>(modeloListaComentarios);
		listaComentarios.setCellRenderer(new LabelRenderer());

		JScrollPane scrollPane = new JScrollPane(listaComentarios);
		scrollPane.setPreferredSize(new Dimension(300, 200));
		panel_1.add(scrollPane);

		// Agregar los comentarios de la foto al modelo del JList
		for (String comentario : foto.getComentarios()) {
			modeloListaComentarios.addElement(comentario);
		}

		JPanel panel_2 = new JPanel();
		frameComentarios.getContentPane().add(panel_2, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameComentarios.setVisible(false);
			}
		});
		panel_2.add(btnNewButton);
	}

	private static class LabelRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			label.setText((String) value);

			return label;
		}
	}
}
