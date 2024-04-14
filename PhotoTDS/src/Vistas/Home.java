package Vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import Controlador.Controlador;
import Modelo.Album;
import Modelo.Foto;
import Modelo.Publicacion;
import Modelo.Usuario;
import Utilidades.ImagenRedonda;
import pulsador.IEncendidoListener;
import pulsador.Luz;

public class Home implements IEncendidoListener {

	private JFrame frameHome;
	private LoginRegister loginRegister;
	private EditarPerfil editarPerfil;
	private AddPublicacion frmFoto;
	private EditarPerfil frmEdit;
	private CommentPhoto frmComentPhoto;
	private CommentAlbum frmComentAlbum;
	private InfoFoto frmInfoFoto;
	private InfoAlbum frmInfoAlbum;
	private Descuentos frmDescuentos;
	private TopFotos frmTopFotos;
	private BusquedaUsuarios frmBusquedaUsuarios;
	private BusquedaHashtags frmBusquedaHashtags;
	private Notificacion frameNotificacion;
	private DefaultListModel<Foto> modeloListaDeFotosUsuario = new DefaultListModel<>();
	private DefaultListModel<Album> modeloListaDeAlbumesUsuario = new DefaultListModel<>();
	private DefaultListModel<JPanel> modeloListaDeFotosHome = new DefaultListModel<>();

	private JButton btnNewButton_5;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	private InfoFotoPerfilUsuario frameFoto;
	private InfoFotoAlbumPerfilUsuario frameAlbum;

	private void muestraFoto(Foto foto) {
		if (frameFoto == null)
			frameFoto = new InfoFotoPerfilUsuario(foto);

		frameFoto.muestra(foto);
	}

	private void muestraAlbum(Foto foto) {
		if (frameAlbum == null)
			frameAlbum = new InfoFotoAlbumPerfilUsuario(foto);

		frameAlbum.muestra(foto);
	}

	void muestra() {
		frameHome.setVisible(true);
		frameHome.setLocationRelativeTo(null);
	}

	public void setVisible(boolean bool) {
		frameHome.setVisible(bool);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home(null, null, null);
					window.frameHome.setVisible(true);
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
	public Home(Object object, CommentPhoto commentPhoto, CommentAlbum commentAlbum)
			throws ParseException, IOException {

		if (object instanceof LoginRegister) {
			loginRegister = (LoginRegister) object;
		}

		if (object instanceof EditarPerfil) {
			editarPerfil = (EditarPerfil) object;
		}

		frmComentPhoto = commentPhoto;

		frmComentAlbum = commentAlbum;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	private void initialize() throws ParseException, IOException {

		// Comprobación de notificación
		if (!Controlador.getUnicaInstancia().notificacion().isEmpty()) {
			muestraNotificacion();
		}

		frameHome = new JFrame();
		frameHome.setResizable(false);
		frameHome.setTitle("PhotoTDS");
		frameHome.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/Imagenes/instagram.png")));
		frameHome.setBounds(100, 100, 620, 649);
		frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel TopPanel = new JPanel();
		TopPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frameHome.getContentPane().add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(0, 0));

		JPanel CardBotPanel = new JPanel();
		frameHome.getContentPane().add(CardBotPanel, BorderLayout.CENTER);
		CardBotPanel.setLayout(new CardLayout(0, 0));

		JPanel BotMainPanel = new JPanel();
		CardBotPanel.add(BotMainPanel, "BotMainPanel");
		BotMainPanel.setLayout(new BorderLayout(0, 0));

		JList<JPanel> listaFotosHome = new JList<>(modeloListaDeFotosHome);
		listaFotosHome.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaFotosHome.setCellRenderer(new PanelRenderer());

		List<Publicacion> fotosHome = null;
		try {
			fotosHome = Controlador.getUnicaInstancia().listaFotosHome();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DefaultListModel<JPanel> listModel = (DefaultListModel<JPanel>) listaFotosHome.getModel();
		for (Publicacion fh : fotosHome) {
			CasillaFotoHome casilla = new CasillaFotoHome(fh);
			listModel.addElement(casilla);
		}

		JScrollPane scrollPane = new JScrollPane(listaFotosHome);
		BotMainPanel.add(scrollPane, BorderLayout.CENTER);

		listaFotosHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) { // Detectar un solo clic
					int index = listaFotosHome.locationToIndex(e.getPoint());
					if (index >= 0) {
						JPanel selectedPanel = listaFotosHome.getModel().getElementAt(index);
						CasillaFotoHome casillaFotoHome = (CasillaFotoHome) selectedPanel;

						if (((Foto) casillaFotoHome.getFoto()).getAlbum() != null) {
							muestraFoto((Foto) casillaFotoHome.getFoto());
						} else {
							muestraAlbum((Foto) casillaFotoHome.getFoto());
						}

					}
				}
			}
		});

		// Ventana del Perfil

//		createPanelPerfil(CardBotPanel);
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
		if (Controlador.getUnicaInstancia().getFotoUsuarioActual() == null) {
			imageIcon2 = new ImageIcon(Home.class.getResource("/Imagenes/sinFotoPerfil.png"));
		} else {
			imageIcon2 = new ImageIcon(Controlador.getUnicaInstancia().getFotoUsuarioActual());
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

		JLabel lblNewLabel_4 = new JLabel(Controlador.getUnicaInstancia().getNombreUserUsuarioActual());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 1;
		PanelInfoUsuario.add(lblNewLabel_4, gbc_lblNewLabel_4);

		String usuario_actual = Controlador.getUnicaInstancia().getNombreUserUsuarioActual();
		List<Publicacion> publicaciones = null;
		try {
			publicaciones = Controlador.getUnicaInstancia().recuperarPublicacionesUsuario(usuario_actual);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JButton btnNewButton_6 = new JButton("Editar Perfil");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				muestraEditPerfil();
				frameHome.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_6.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 1;
		PanelInfoUsuario.add(btnNewButton_6, gbc_btnNewButton_6);
		JLabel lblNewLabel_5 = new JLabel(publicaciones.size() + " Publicaciones");

		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 3;
		PanelInfoUsuario.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(Controlador.getUnicaInstancia().getSeguidores() + " Seguidores");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 3;
		PanelInfoUsuario.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel(Controlador.getUnicaInstancia().getSeguidos() + " Seguidos");

		for (String c : Controlador.getUnicaInstancia().getUsuarioActual().getSeguidos()) {
			System.out.println("Seguidor : " + c);
		}

		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_7.gridx = 5;
		gbc_lblNewLabel_7.gridy = 3;
		PanelInfoUsuario.add(lblNewLabel_7, gbc_lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel(Controlador.getUnicaInstancia().getNombreUsuarioActual());
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 5;
		PanelInfoUsuario.add(lblNewLabel_8, gbc_lblNewLabel_8);

		JLabel lblNewLabel_11 = new JLabel(Controlador.getUnicaInstancia().getDescripcionUsuarioActual());
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

		JPanel panel_1 = new JPanel();
		TopPanel.add(panel_1, BorderLayout.EAST);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) (CardBotPanel.getLayout());

				cl.show(CardBotPanel, "ProfilePanel");
				try {
					modeloListaDeFotosUsuario.clear();
					List<Publicacion> listaFotos = Controlador.getUnicaInstancia()
							.recuperarFotosUsuario(Controlador.getUnicaInstancia().getNombreUserUsuarioActual());
					for (Publicacion f : listaFotos) {
						modeloListaDeFotosUsuario.addElement((Foto) f);
					}

					modeloListaDeAlbumesUsuario.clear();
					List<Publicacion> listaAlbumes = Controlador.getUnicaInstancia()
							.recuperarAlbumesUsuario(Controlador.getUnicaInstancia().getNombreUserUsuarioActual());
					for (Publicacion a : listaAlbumes) {
						modeloListaDeAlbumesUsuario.addElement((Album) a);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

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

		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		ImageIcon imageIcon;
		if (Controlador.getUnicaInstancia().getFotoUsuarioActual() == null) {
			imageIcon = new ImageIcon(Home.class.getResource("/Imagenes/sinFotoPerfil.png"));
		} else {
			imageIcon = new ImageIcon(Controlador.getUnicaInstancia().getFotoUsuarioActual());
		}

		Image image = imageIcon.getImage();
		Image imagenRedimensionada = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);

		ImagenRedonda imagenRedonda = new ImagenRedonda(imagenRedimensionadaIcon);
		lblNewLabel_1.setIcon(imagenRedonda);
		panel_1.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		TopPanel.add(panel_2, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) (CardBotPanel.getLayout());
				cl.show(CardBotPanel, "BotMainPanel");
			}
		});
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/Imagenes/instagram.png")));
		panel_2.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		TopPanel.add(panel_4, BorderLayout.CENTER);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 100, 49, 155, 49, 0, 10, 0 };
		gbl_panel_4.rowHeights = new int[] { 25, 25, 25, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muestraAddFoto();
				frameHome.setVisible(false);
			}
		});
		
		Luz luz = new Luz();
		luz.addEncendidoListener(this);
		GridBagConstraints gbc_luz = new GridBagConstraints();
		gbc_luz.insets = new Insets(0, 0, 5, 5);
		gbc_luz.gridx = 0;
		gbc_luz.gridy = 1;
		panel_4.add(luz, gbc_luz);
		luz.addEncendidoListener(this);
		btnNewButton_4.setIcon(new ImageIcon(Home.class.getResource("/Imagenes/signo-positivo.png")));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 1;
		panel_4.add(btnNewButton_4, gbc_btnNewButton_4);

		JTextField busqueda = new JTextField();
		busqueda.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_busqueda = new GridBagConstraints();
		gbc_busqueda.fill = GridBagConstraints.HORIZONTAL;
		gbc_busqueda.insets = new Insets(0, 0, 5, 5);
		gbc_busqueda.gridx = 2;
		gbc_busqueda.gridy = 1;
		panel_4.add(busqueda, gbc_busqueda);
		busqueda.setColumns(10);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (busqueda.getText().contains("#")) {
					System.out.println("Entro al ########");

					String[] array = busqueda.getText().split(" ");
					List<String> lista = new LinkedList<String>();

					for (String palabra : array) {
						if (palabra.contains("#")) {
							String palabraSinHashtag = palabra.substring(1);
							System.out.println("Home " + palabraSinHashtag);
							lista.add(palabraSinHashtag);
						}
					}

					try {
						muestraBusquedaHashtags(Controlador.getUnicaInstancia().buscarHashtags(lista));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					try {
						muestraBusquedaUsuarios(busqueda.getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Home.class.getResource("/Imagenes/buscar.png")));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 1;
		panel_4.add(btnNewButton_3, gbc_btnNewButton_3);

		JPanel panel_3 = new JPanel();
		TopPanel.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton = new JButton("Premium");
		btnNewButton.setIcon(new ImageIcon(Home.class.getResource("/Imagenes/estrella.png")));
		panel_3.add(btnNewButton);

		btnNewButton_5 = new JButton("Top Me gusta");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					muestraTopFotos(usuario_actual);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(Home.class.getResource("/Imagenes/diamante.png")));
		panel_3.add(btnNewButton_5);

		btnNewButton_1 = new JButton("Generar Excel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controlador.getUnicaInstancia().generarExcel();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Home.class.getResource("/Imagenes/sobresalir.png")));
		panel_3.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Generar PDF");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controlador.getUnicaInstancia().generarPDF();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Home.class.getResource("/Imagenes/pdf.png")));
		panel_3.add(btnNewButton_2);

		if (Controlador.getUnicaInstancia().getPremium() == false) {
			btnNewButton_5.setEnabled(false);
			btnNewButton_1.setEnabled(false);
			btnNewButton_2.setEnabled(false);
		}

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Controlador.getUnicaInstancia().getPremium() == false) {
					try {
						muestraDescuentos(Controlador.getUnicaInstancia().getUsuarioActual());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					System.out.println("Ya eras Premium");
				}
			}
		});

	}

	// Metodos privados

	public void activarBotonesPremium() {
		btnNewButton_5.setEnabled(true);
		btnNewButton_1.setEnabled(true);
		btnNewButton_2.setEnabled(true);
	}

	private void muestraAddFoto() {
		if (frmFoto == null)
			frmFoto = new AddPublicacion(this);

		frmFoto.muestra();
	}

	private void muestraEditPerfil() {
		if (frmEdit == null)
			frmEdit = new EditarPerfil(this, Controlador.getUnicaInstancia().getCodigoUsuarioActual());

		frmEdit.muestra();
	}

	private void muestraInfoFoto(Foto foto) {
		if (frmInfoFoto == null)
			frmInfoFoto = new InfoFoto(foto);

		frmInfoFoto.muestra(foto);
	}

	private void muestraInfoAlbum(Album album) {
		if (frmInfoAlbum == null)
			frmInfoAlbum = new InfoAlbum(album);

		frmInfoAlbum.muestra(album);
	}

	private void muestraBusquedaUsuarios(String busqueda) throws ParseException {
		if (frmBusquedaUsuarios == null)
			frmBusquedaUsuarios = new BusquedaUsuarios(busqueda);

		frmBusquedaUsuarios.muestra(busqueda);
	}

	private void muestraBusquedaHashtags(List<String> busqueda) throws ParseException {
		if (frmBusquedaHashtags == null)
			frmBusquedaHashtags = new BusquedaHashtags(busqueda);

		frmBusquedaHashtags.muestra(busqueda);
	}

	private void muestraDescuentos(Usuario usuario) throws ParseException {
		if (frmDescuentos == null)
			frmDescuentos = new Descuentos(this, usuario);

		frmDescuentos.muestra();
	}

	private void muestraTopFotos(String usuario) throws ParseException, IOException {
		if (frmTopFotos == null)
			frmTopFotos = new TopFotos(usuario);

		frmTopFotos.muestra();
	}

	private void muestraNotificacion() throws ParseException, IOException {
		if (frameNotificacion == null)
			frameNotificacion = new Notificacion();

		frameNotificacion.muestra();
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

	@Override
	public void enteradoCambioEncendido(EventObject arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);

		if (chooser.getSelectedFile() != null) {
			String fichero = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("Nombre del fichero : " + fichero);
			Controlador.getUnicaInstancia().uploadPhotos(fichero);
		}

	}

}
