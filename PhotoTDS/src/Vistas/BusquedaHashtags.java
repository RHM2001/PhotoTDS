package Vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Controlador.Controlador;
import Modelo.Usuario;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BusquedaHashtags {

	private JFrame frameBusquedaHashtags;
	private List<String> busqueda;
	private DefaultListModel<String> modeloListaHashtags;

	void muestra(List<String> busqueda) throws ParseException {
		this.busqueda = busqueda;
		modeloListaHashtags = new DefaultListModel<>();
		initialize();
		frameBusquedaHashtags.setVisible(true);
		frameBusquedaHashtags.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaHashtags window = new BusquedaHashtags(null);
					window.frameBusquedaHashtags.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BusquedaHashtags(List<String> busqueda) throws ParseException {
		this.busqueda = busqueda;
		modeloListaHashtags = new DefaultListModel<>();
		initialize();
	}

	private void initialize() throws ParseException {
		frameBusquedaHashtags = new JFrame();
		frameBusquedaHashtags.setResizable(false);
		frameBusquedaHashtags.setBounds(100, 100, 160, 285);

		JPanel panel = new JPanel();
		frameBusquedaHashtags.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameBusquedaHashtags.setVisible(false);
			}
		});
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		frameBusquedaHashtags.getContentPane().add(panel_1, BorderLayout.CENTER);

		JList<String> listaHashtags = new JList<>(modeloListaHashtags);
		listaHashtags.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaHashtags.setCellRenderer(new LabelRenderer());

		JScrollPane scrollPane = new JScrollPane(listaHashtags);
		panel_1.add(scrollPane);

		JPanel panel_2 = new JPanel();
		frameBusquedaHashtags.getContentPane().add(panel_2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Lista de hashtags");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblNewLabel);
		
		// Agregar los elementos de la lista "busqueda" al modelo del JList
		for (String hashtag : busqueda) {
			System.out.println("Dentro : " + hashtag);
			modeloListaHashtags.addElement(hashtag);
		}
	}

	private static class LabelRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			// Establecer el texto del JLabel con el valor de cada elemento en la lista
			label.setText((String) value);
			
			return label;
		}
	}
}
