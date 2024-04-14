package Vistas;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modelo.Album;
import Modelo.Foto;
import Modelo.Publicacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import Controlador.Controlador;

import javax.swing.JButton;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class InfoAlbum {

	private JFrame frameInfoAlbum;
	private AddPublicacionAlbum frameAddPublicacionAlbum;
	private InfoFotoAlbum frmInfoFotoAlbum;
	private Album album;
	private DefaultListModel<Foto> modeloListaDeFotosAlbum = new DefaultListModel<>();
	
	void muestra(Album album) {
		this.album = album;
		initialize();
		frameInfoAlbum.setVisible(true);
		frameInfoAlbum.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoAlbum window = new InfoAlbum(null);
					window.frameInfoAlbum.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InfoAlbum(Album album) {
		this.album = album;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameInfoAlbum = new JFrame();
		frameInfoAlbum.setResizable(false);
		frameInfoAlbum.setBounds(100, 100, 571, 465);
		
		JPanel panel = new JPanel();
		frameInfoAlbum.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Álbum: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setText(album.getNombre());
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		frameInfoAlbum.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JList<Foto> listaDeFotos = new JList<Foto>();
		listaDeFotos.ensureIndexIsVisible(listaDeFotos.getHeight());
		panel_1.setLayout(new BorderLayout(0, 0));
		
		modeloListaDeFotosAlbum.clear();
		List<Foto> listaFotos = album.getFotos();
		for (Foto f : listaFotos) {
			modeloListaDeFotosAlbum.addElement(f);
		}
		
		listaDeFotos.ensureIndexIsVisible(listaDeFotos.getHeight());
		listaDeFotos.setModel(modeloListaDeFotosAlbum);
		listaDeFotos.setCellRenderer(createListRendererFoto());
		listaDeFotos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeFotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listaDeFotos.setVisibleRowCount(-1);
		
		JScrollPane scrollPane = new JScrollPane(listaDeFotos);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(listaDeFotos);
		panel_1.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		scrollPane.setColumnHeaderView(panel_3);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setText(album.getDescripcion());
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblNewLabel_5);
		
		JPanel panel_2 = new JPanel();
		frameInfoAlbum.getContentPane().add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{10, 17, 30, 46, 0, 33, 0, 81, 0, 5, 56, 10, -56, 0};
		gbl_panel_2.rowHeights = new int[]{10, 25, 10, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameInfoAlbum.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 1;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controlador.getUnicaInstancia().darMeGusta(album);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(InfoAlbum.class.getResource("/Imagenes/corazon.png")));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 1;
		panel_2.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Me gusta:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 1;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setText(String.valueOf(album.getMeGusta()));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 5;
		gbc_lblNewLabel_4.gridy = 1;
		panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Añadir foto al álbum");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 7;
		gbc_lblNewLabel_2.gridy = 1;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(album.getFotos().size() == 16) {
					System.out.println("Supera el limite de fotos del album");
				}
				else {
					muestraAddPublicacionAlbum(album);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(InfoAlbum.class.getResource("/Imagenes/signo-positivo.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 8;
		gbc_btnNewButton.gridy = 1;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Eliminar Álbum");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controlador.getUnicaInstancia().eliminarAlbum(album);
					frameInfoAlbum.setVisible(false);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 10;
		gbc_btnNewButton_3.gridy = 1;
		panel_2.add(btnNewButton_3, gbc_btnNewButton_3);
	}
	
	private void muestraInfoFotoAlbum(Foto foto) {
		if (frmInfoFotoAlbum == null)
			frmInfoFotoAlbum = new InfoFotoAlbum(foto);
		
		frmInfoFotoAlbum.muestra(foto);
	}
	
	private void muestraAddPublicacionAlbum(Album album) {
		if (frameAddPublicacionAlbum == null)
			frameAddPublicacionAlbum = new AddPublicacionAlbum(album);
		
		frameAddPublicacionAlbum.muestra(album);
	}
	
	private ListCellRenderer<? super Foto> createListRendererFoto() {
		return new DefaultListCellRenderer() {
			private Color background = new Color(0, 100, 255, 15);
			private Color defaultBackground = (Color) UIManager.get("List.background");

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Foto foto = (Foto) value;
					if (foto != null) {
						ImageIcon icon = new ImageIcon(foto.getFile().getAbsolutePath());
						Image image = icon.getImage();
						int width = 167;
						int height = 167;
						Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
 
						label.setIcon(new ImageIcon(scaledImage));
						label.setText("");
						

					}
					if (!isSelected) {
						label.setBackground(index % 2 == 0 ? background : defaultBackground);
						list.clearSelection();
					}
					else {
						list.clearSelection();
						Foto fotoSeleccionada = (Foto) value;
					    muestraInfoFotoAlbum(fotoSeleccionada);
					}
					
				}
				return c;
			}
			
		};
	}
	
	// Metodo privados

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}
