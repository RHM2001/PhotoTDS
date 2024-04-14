package Vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Controlador.Controlador;
import Modelo.Album;
import Modelo.Foto;
import Modelo.Publicacion;
import Modelo.Usuario;
import Utilidades.ImagenRedonda;
import javax.swing.JButton;

public class PerfilUsuario {

	private JFrame framePerfilUsuario;
	private Usuario usuario;
	private InfoFotoPerfilUsuario frmInfoFoto;
	private InfoAlbumPerfilUsuario frmInfoAlbum;
	private DefaultListModel<Foto> modeloListaDeFotosUsuario = new DefaultListModel<>();
	private DefaultListModel<Album> modeloListaDeAlbumesUsuario = new DefaultListModel<>();

	void muestra(Usuario usuario) throws ParseException {
		this.usuario = usuario;
		initialize();
		framePerfilUsuario.setVisible(true);
		framePerfilUsuario.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilUsuario window = new PerfilUsuario(null);
					window.framePerfilUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PerfilUsuario(Usuario usuario) {
		this.usuario = usuario;
		initialize();
	}

	public void initialize() {
		framePerfilUsuario = new JFrame();
		framePerfilUsuario.setResizable(false);
		framePerfilUsuario.setBounds(100, 100, 545, 465);

		JPanel CardBotPanel = new JPanel();
		framePerfilUsuario.getContentPane().add(CardBotPanel, BorderLayout.CENTER);
		CardBotPanel.setLayout(new CardLayout(0, 0));

		JPanel ProfilePanel = new JPanel();
		CardBotPanel.add(ProfilePanel, "ProfilePanel");
		ProfilePanel.setLayout(new BorderLayout(0, 0));

		JPanel TopPanelCard = new JPanel();
		ProfilePanel.add(TopPanelCard, BorderLayout.NORTH);
		TopPanelCard.setLayout(new BorderLayout(0, 0));

		JPanel PanelFotoPerfil = new JPanel();
		TopPanelCard.add(PanelFotoPerfil, BorderLayout.WEST);
		PanelFotoPerfil.setLayout(new BoxLayout(PanelFotoPerfil, BoxLayout.X_AXIS));

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);

		ImageIcon imageIcon2;
		if (usuario.getFotoPerfil() == null) {
			imageIcon2 = new ImageIcon(Home.class.getResource("/Imagenes/sinFotoPerfil.png"));
		} else {
			imageIcon2 = new ImageIcon(usuario.getFotoPerfil());
		}

		Image image2 = imageIcon2.getImage();
		Image imagenRedimensionada2 = image2.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon2 = new ImageIcon(imagenRedimensionada2);

		ImagenRedonda imagenRedonda2 = new ImagenRedonda(imagenRedimensionadaIcon2);
		lblNewLabel_3.setIcon(imagenRedonda2);
		PanelFotoPerfil.add(lblNewLabel_3);

		JPanel PanelInfoUsuario = new JPanel();
		TopPanelCard.add(PanelInfoUsuario, BorderLayout.CENTER);
		GridBagLayout gbl_PanelInfoUsuario = new GridBagLayout();
		gbl_PanelInfoUsuario.columnWidths = new int[] { 30, 0, 10, 0, 10, 0, 0, 0, 0, 0, 0, 0 };
		gbl_PanelInfoUsuario.rowHeights = new int[] { 25, 0, 10, 0, 10, 0, 25, 0 };
		gbl_PanelInfoUsuario.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_PanelInfoUsuario.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		PanelInfoUsuario.setLayout(gbl_PanelInfoUsuario);

		JLabel lblNewLabel_4 = new JLabel(usuario.getNombre());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 1;
		PanelInfoUsuario.add(lblNewLabel_4, gbc_lblNewLabel_4);

		String usuario_actual = usuario.getNombreUser();
		List<Publicacion> publicaciones = null;
		try {
			publicaciones = Controlador.getUnicaInstancia().recuperarPublicacionesUsuario(usuario_actual);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_6.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 1;
		
		JButton btnNewButton = new JButton("Seguir");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controlador.getUnicaInstancia().seguirUsuario(Controlador.getUnicaInstancia().getUsuarioActual(), usuario.getNombreUser());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(PerfilUsuario.class.getResource("/Imagenes/signo-positivo.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		PanelInfoUsuario.add(btnNewButton, gbc_btnNewButton);
		JLabel lblNewLabel_5 = new JLabel(publicaciones.size() + " Publicaciones");

		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 3;
		PanelInfoUsuario.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(usuario.getSeguidores().size() + " Seguidores");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 3;
		PanelInfoUsuario.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel(usuario.getSeguidos().size() + " Seguidos");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_7.gridx = 5;
		gbc_lblNewLabel_7.gridy = 3;
		PanelInfoUsuario.add(lblNewLabel_7, gbc_lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel(usuario.getNombreUser());
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 5;
		PanelInfoUsuario.add(lblNewLabel_8, gbc_lblNewLabel_8);

		JLabel lblNewLabel_11 = new JLabel(usuario.getPresentacionPerfil());
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_11.gridx = 1;
		gbc_lblNewLabel_11.gridy = 6;
		PanelInfoUsuario.add(lblNewLabel_11, gbc_lblNewLabel_11);

		JPanel PanelSeleccionPublicaciones = new JPanel();
		TopPanelCard.add(PanelSeleccionPublicaciones, BorderLayout.SOUTH);

		JLabel lblNewLabel_2 = new JLabel("Fotos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setForeground(Color.decode("#9932cc"));
		PanelSeleccionPublicaciones.add(lblNewLabel_2);

		JLabel lblNewLabel_10 = new JLabel("      ");
		PanelSeleccionPublicaciones.add(lblNewLabel_10);

		JLabel lblNewLabel_9 = new JLabel("\u00C1lbumes");

		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		PanelSeleccionPublicaciones.add(lblNewLabel_9);

		JPanel CardLayoutPublicaciones = new JPanel();
		ProfilePanel.add(CardLayoutPublicaciones, BorderLayout.CENTER);
		CardLayoutPublicaciones.setLayout(new CardLayout(0, 0));

		// CARD DE FOTOS

		JPanel CardFotos = new JPanel();
		CardLayoutPublicaciones.add(CardFotos, "cardFotos");

		JList<Foto> listaDeFotos = new JList<Foto>();
		listaDeFotos.ensureIndexIsVisible(listaDeFotos.getHeight());
		CardFotos.setLayout(new BorderLayout(0, 0));

		// CARD DE ALBUMES

		JPanel CardAlbumes = new JPanel();
		CardLayoutPublicaciones.add(CardAlbumes, "cardAlbumes");

		JList<Album> listaDeAlbumes = new JList<Album>();
		listaDeAlbumes.ensureIndexIsVisible(listaDeAlbumes.getHeight());
		CardFotos.setLayout(new BorderLayout(0, 0));

		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_2.setForeground(Color.decode("#9932cc"));
				lblNewLabel_9.setForeground(null);

				CardLayout cl = (CardLayout) (CardLayoutPublicaciones.getLayout());
				cl.show(CardLayoutPublicaciones, "cardFotos");
			}
		});

		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_9.setForeground(Color.decode("#9932cc"));
				lblNewLabel_2.setForeground(null);

				CardLayout cl = (CardLayout) (CardLayoutPublicaciones.getLayout());
				cl.show(CardLayoutPublicaciones, "cardAlbumes");
			}
		});

		modeloListaDeFotosUsuario.clear();
		List<Publicacion> listaFotos = null;
		try {
			listaFotos = Controlador.getUnicaInstancia().recuperarFotosUsuario(usuario.getNombreUser());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Publicacion f : listaFotos) {
			System.out.println("Se mete - FOTO");
			modeloListaDeFotosUsuario.addElement((Foto) f);
		}

		modeloListaDeAlbumesUsuario.clear();
		List<Publicacion> listaAlbumes = null;
		try {
			listaAlbumes = Controlador.getUnicaInstancia()
					.recuperarAlbumesUsuario(usuario.getNombreUser());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Publicacion a : listaAlbumes) {
			System.out.println("Se mete - ALBUM");
			modeloListaDeAlbumesUsuario.addElement((Album) a);
		}

		// Muestra las fotos dentro del panelScroll de FOTOS

		listaDeFotos.ensureIndexIsVisible(listaDeFotos.getHeight());
		listaDeFotos.setModel(modeloListaDeFotosUsuario);
		listaDeFotos.setCellRenderer(createListRendererFoto());
		listaDeFotos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeFotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listaDeFotos.setVisibleRowCount(-1);

		JScrollPane panelScrollFotosUsuario = new JScrollPane(listaDeFotos);
		panelScrollFotosUsuario.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelScrollFotosUsuario.setViewportView(listaDeFotos);
		CardFotos.add(panelScrollFotosUsuario);

		// Muestra las fotos dentro del panelScroll de ALBUM

		listaDeAlbumes.ensureIndexIsVisible(listaDeFotos.getHeight());
		listaDeAlbumes.setModel(modeloListaDeAlbumesUsuario);
		listaDeAlbumes.setCellRenderer(createListRendererAlbum());
		listaDeAlbumes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDeAlbumes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listaDeAlbumes.setVisibleRowCount(-1);

		JScrollPane scrollPanelAlbumesUsuario = new JScrollPane(listaDeAlbumes);
		scrollPanelAlbumesUsuario.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanelAlbumesUsuario.setViewportView(listaDeAlbumes);
		CardAlbumes.add(scrollPanelAlbumesUsuario);

	}

	private void muestraInfoFoto(Foto foto) {
		if (frmInfoFoto == null)
			frmInfoFoto = new InfoFotoPerfilUsuario(foto);

		frmInfoFoto.muestra(foto);
	}

	private void muestraInfoAlbum(Album album) {
		if (frmInfoAlbum == null)
			frmInfoAlbum = new InfoAlbumPerfilUsuario(album);

		frmInfoAlbum.muestra(album);
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
						int width = 187;
						int height = 187;
						Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

						label.setIcon(new ImageIcon(scaledImage));
						label.setText("");

					}
					if (!isSelected) {
						label.setBackground(index % 2 == 0 ? background : defaultBackground);
						list.clearSelection();
					} else {
						list.clearSelection();
						Foto fotoSeleccionada = (Foto) value;
						muestraInfoFoto(fotoSeleccionada);
					}

				}
				return c;
			}

		};
	}

	private ListCellRenderer<? super Album> createListRendererAlbum() {
		return new DefaultListCellRenderer() {
			private Color background = new Color(0, 100, 255, 15);
			private Color defaultBackground = (Color) UIManager.get("List.background");

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Album album = (Album) value;
					if (album != null) {
						ImageIcon icon = new ImageIcon(album.getFotos().get(0).getFile().getAbsolutePath());
						Image image = icon.getImage();
						int width = 172;
						int height = 172;
						Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

						label.setIcon(new ImageIcon(scaledImage));
						label.setText("");

					}
					if (!isSelected) {
						label.setBackground(index % 2 == 0 ? background : defaultBackground);
						list.clearSelection();
					} else {
						list.clearSelection();
						Album albumSeleccionado = (Album) value;
						muestraInfoAlbum(albumSeleccionado);
					}

				}
				return c;
			}

		};
	}

}
