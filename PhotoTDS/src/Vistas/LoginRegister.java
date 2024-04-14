package Vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import Controlador.Controlador;
import Modelo.Usuario;
import Persistencia.RepositorioUsuarios;

@SuppressWarnings("serial")
public class LoginRegister {

	private JFrame frmPhototds;
	private JTextField Field_Usuario;
	private JPasswordField Field_Contrasena;
	private JTextField Field_Email;
	private JTextField Field_Nombre_Completo;
	private JTextField Field_Nombre_Usuario;
	private JPasswordField Field_Contrasena_Register;
	private JDateChooser FechaNacim;

	private TextoPresentacion textPresent;
	private ErrorCamposRegistro errorCamposRegistro;
	private ErrorCamposLogin errorCamposLogin;
	private ErrorUsuarioYaRegistrado errorUsuarioYaRegistrado;
	private UsuarioRegistradoCorrectamente usuarioRegistradoCorrectamente;
	private Home home;
	
	private File selectedFile = null;

	public void muestra() {
		frmPhototds.setVisible(true);
		frmPhototds.setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginRegister window = new LoginRegister();
					window.frmPhototds.setVisible(true);
					window.frmPhototds.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Inicializo la ventana para escribir la presentaci�n del perfil del usuario.
		textPresent = new TextoPresentacion(this);

		frmPhototds = new JFrame();
		frmPhototds.setIconImage(
				Toolkit.getDefaultToolkit().getImage(LoginRegister.class.getResource("/Imagenes/instagram.png")));
		frmPhototds.setTitle("PhotoTDS");
		frmPhototds.setBounds(100, 100, 550, 380);
		frmPhototds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhototds.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel TopPanel = new JPanel();
		TopPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmPhototds.getContentPane().add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(28, 0));

		JPanel IzqFlowLayout = new JPanel();
		TopPanel.add(IzqFlowLayout, BorderLayout.WEST);
		IzqFlowLayout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginRegister.class.getResource("/Imagenes/instagram.png")));
		IzqFlowLayout.add(lblNewLabel_1);

		JPanel CenFlowLayout = new JPanel();
		CenFlowLayout.setAutoscrolls(true);
		TopPanel.add(CenFlowLayout, BorderLayout.CENTER);
		CenFlowLayout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblPhototds = new JLabel("PhotoTDS");
		lblPhototds.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPhototds.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 45));
		lblPhototds.setForeground(new Color(153, 50, 204));
		lblPhototds.setHorizontalAlignment(SwingConstants.CENTER);
		CenFlowLayout.add(lblPhototds);

		JPanel DerFlowLayout = new JPanel();
		TopPanel.add(DerFlowLayout, BorderLayout.EAST);
		DerFlowLayout.setLayout(new BoxLayout(DerFlowLayout, BoxLayout.X_AXIS));

		JPanel CardBotPanel = new JPanel();
		frmPhototds.getContentPane().add(CardBotPanel, BorderLayout.CENTER);
		CardBotPanel.setLayout(new CardLayout(0, 0));

		// VENTANA DE LOGIN

		JPanel BotPanelLogin = new JPanel();
		CardBotPanel.add(BotPanelLogin, "Bot_Panel_Login");
		GridBagLayout gbl_BotPanelLogin = new GridBagLayout();
		gbl_BotPanelLogin.columnWidths = new int[] { 90, 76, 201, 90, 0 };
		gbl_BotPanelLogin.rowHeights = new int[] { 50, 0, 0, 0, 0, 0, 60, 50, 0 };
		gbl_BotPanelLogin.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_BotPanelLogin.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		BotPanelLogin.setLayout(gbl_BotPanelLogin);

		JLabel Label_Usuario = new JLabel("Usuario: ");
		GridBagConstraints gbc_Label_Usuario = new GridBagConstraints();
		gbc_Label_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Usuario.gridx = 1;
		gbc_Label_Usuario.gridy = 1;
		BotPanelLogin.add(Label_Usuario, gbc_Label_Usuario);

		Field_Usuario = new JTextField();
		Field_Usuario.setColumns(10);
		GridBagConstraints gbc_Field_Usuario = new GridBagConstraints();
		gbc_Field_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Field_Usuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_Field_Usuario.gridx = 2;
		gbc_Field_Usuario.gridy = 1;
		BotPanelLogin.add(Field_Usuario, gbc_Field_Usuario);

		JLabel Label_Contrasena = new JLabel("Contrase\u00F1a: ");
		GridBagConstraints gbc_Label_Contrasena = new GridBagConstraints();
		gbc_Label_Contrasena.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Contrasena.gridx = 1;
		gbc_Label_Contrasena.gridy = 2;
		BotPanelLogin.add(Label_Contrasena, gbc_Label_Contrasena);

		Field_Contrasena = new JPasswordField();
		GridBagConstraints gbc_Field_Contrasena = new GridBagConstraints();
		gbc_Field_Contrasena.insets = new Insets(0, 0, 5, 5);
		gbc_Field_Contrasena.fill = GridBagConstraints.HORIZONTAL;
		gbc_Field_Contrasena.gridx = 2;
		gbc_Field_Contrasena.gridy = 2;
		BotPanelLogin.add(Field_Contrasena, gbc_Field_Contrasena);

		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean error = false;

				if (Field_Usuario.getText().trim().isEmpty()) {
					Field_Usuario.setBackground(new Color(125, 50, 50));
					error = true;
				} else {
					Field_Usuario.setBackground(new Color(58, 61, 76));
				}

				String auxPassword = new String(Field_Contrasena.getPassword());
				if (auxPassword.equals("")) {
					Field_Contrasena.setBackground(new Color(125, 50, 50));
					error = true;
				} else {
					Field_Contrasena.setBackground(new Color(58, 61, 76));
				}

				if (error == true) {
					if (errorCamposLogin == null)
						errorCamposLogin = new ErrorCamposLogin(frmPhototds.getX(), frmPhototds.getX());
					errorCamposLogin.muestra();
				} else {
					// primero compruebo que el usuario existe
					if (Controlador.getUnicaInstancia().loginUsuario(Field_Usuario.getText(), auxPassword) == false) {
						if (errorCamposLogin == null)
							errorCamposLogin = new ErrorCamposLogin(frmPhototds.getX(), frmPhototds.getY());
						errorCamposLogin.muestra();
					} else {
						// cambio de ventana
						frmPhototds.setVisible(false);
						try {
							muestraHome();
							Date today = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							String cadenaToday = sdf.format(today);
							System.out.println("Conexion : " + cadenaToday);
							Controlador.getUnicaInstancia().cambiarUltimaConexion(cadenaToday);
						} catch (ParseException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 4;
		BotPanelLogin.add(btnLogin, gbc_btnLogin);

		JLabel Label_Tienes_Cuenta = new JLabel("\u00BFNo tienes cuenta?");
		Label_Tienes_Cuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Label_Tienes_Cuenta.setForeground(Color.decode("#9932cc"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Label_Tienes_Cuenta.setForeground(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) (CardBotPanel.getLayout());
				cl.show(CardBotPanel, "Bot_Panel_Register");
			}
		});
		GridBagConstraints gbc_Label_Tienes_Cuenta = new GridBagConstraints();
		gbc_Label_Tienes_Cuenta.anchor = GridBagConstraints.WEST;
		gbc_Label_Tienes_Cuenta.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Tienes_Cuenta.gridx = 2;
		gbc_Label_Tienes_Cuenta.gridy = 6;
		BotPanelLogin.add(Label_Tienes_Cuenta, gbc_Label_Tienes_Cuenta);

		// VENTANA DE REGISTRO

		JPanel BotPanelRegister = new JPanel();
		CardBotPanel.add(BotPanelRegister, "Bot_Panel_Register");
		GridBagLayout gbl_BotPanelRegister = new GridBagLayout();
		gbl_BotPanelRegister.columnWidths = new int[] { 90, 0, 248, 90, 0 };
		gbl_BotPanelRegister.rowHeights = new int[] { 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 0 };
		gbl_BotPanelRegister.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_BotPanelRegister.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		BotPanelRegister.setLayout(gbl_BotPanelRegister);

		JLabel Label_Email = new JLabel("Email: *");
		GridBagConstraints gbc_Label_Email = new GridBagConstraints();
		gbc_Label_Email.anchor = GridBagConstraints.EAST;
		gbc_Label_Email.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Email.gridx = 1;
		gbc_Label_Email.gridy = 1;
		BotPanelRegister.add(Label_Email, gbc_Label_Email);

		Field_Email = new JTextField();
		GridBagConstraints gbc_Field_Email = new GridBagConstraints();
		gbc_Field_Email.insets = new Insets(0, 0, 5, 5);
		gbc_Field_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_Field_Email.gridx = 2;
		gbc_Field_Email.gridy = 1;
		BotPanelRegister.add(Field_Email, gbc_Field_Email);
		Field_Email.setColumns(10);

		JLabel Label_Nombre_Completo = new JLabel("Nombre completo: *");
		GridBagConstraints gbc_Label_Nombre_Completo = new GridBagConstraints();
		gbc_Label_Nombre_Completo.anchor = GridBagConstraints.EAST;
		gbc_Label_Nombre_Completo.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Nombre_Completo.gridx = 1;
		gbc_Label_Nombre_Completo.gridy = 2;
		BotPanelRegister.add(Label_Nombre_Completo, gbc_Label_Nombre_Completo);

		Field_Nombre_Completo = new JTextField();
		GridBagConstraints gbc_Field_Nombre_Completo = new GridBagConstraints();
		gbc_Field_Nombre_Completo.insets = new Insets(0, 0, 5, 5);
		gbc_Field_Nombre_Completo.fill = GridBagConstraints.HORIZONTAL;
		gbc_Field_Nombre_Completo.gridx = 2;
		gbc_Field_Nombre_Completo.gridy = 2;
		BotPanelRegister.add(Field_Nombre_Completo, gbc_Field_Nombre_Completo);
		Field_Nombre_Completo.setColumns(10);

		JLabel Label_Nombre_Usuario = new JLabel("Nombre de usuario: *");
		GridBagConstraints gbc_Label_Nombre_Usuario = new GridBagConstraints();
		gbc_Label_Nombre_Usuario.anchor = GridBagConstraints.EAST;
		gbc_Label_Nombre_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Nombre_Usuario.gridx = 1;
		gbc_Label_Nombre_Usuario.gridy = 3;
		BotPanelRegister.add(Label_Nombre_Usuario, gbc_Label_Nombre_Usuario);

		Field_Nombre_Usuario = new JTextField();
		GridBagConstraints gbc_Field_Nombre_Usuario = new GridBagConstraints();
		gbc_Field_Nombre_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Field_Nombre_Usuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_Field_Nombre_Usuario.gridx = 2;
		gbc_Field_Nombre_Usuario.gridy = 3;
		BotPanelRegister.add(Field_Nombre_Usuario, gbc_Field_Nombre_Usuario);
		Field_Nombre_Usuario.setColumns(10);

		JLabel Label_Contrasena_Register = new JLabel("Contrase\u00F1a: *");
		GridBagConstraints gbc_Label_Contrasena_Register = new GridBagConstraints();
		gbc_Label_Contrasena_Register.anchor = GridBagConstraints.EAST;
		gbc_Label_Contrasena_Register.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Contrasena_Register.gridx = 1;
		gbc_Label_Contrasena_Register.gridy = 4;
		BotPanelRegister.add(Label_Contrasena_Register, gbc_Label_Contrasena_Register);

		Field_Contrasena_Register = new JPasswordField();
		GridBagConstraints gbc_Field_Contrasena_Register = new GridBagConstraints();
		gbc_Field_Contrasena_Register.insets = new Insets(0, 0, 5, 5);
		gbc_Field_Contrasena_Register.fill = GridBagConstraints.HORIZONTAL;
		gbc_Field_Contrasena_Register.gridx = 2;
		gbc_Field_Contrasena_Register.gridy = 4;
		BotPanelRegister.add(Field_Contrasena_Register, gbc_Field_Contrasena_Register);

		JLabel Label_Fecha_Nacim = new JLabel("Fecha de nacimiento: *");
		GridBagConstraints gbc_Label_Fecha_Nacim = new GridBagConstraints();
		gbc_Label_Fecha_Nacim.anchor = GridBagConstraints.EAST;
		gbc_Label_Fecha_Nacim.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Fecha_Nacim.gridx = 1;
		gbc_Label_Fecha_Nacim.gridy = 5;
		BotPanelRegister.add(Label_Fecha_Nacim, gbc_Label_Fecha_Nacim);

		JDateChooser Field_Calendario = new JDateChooser();
		Field_Calendario.getCalendarButton()
				.setIcon(new ImageIcon(LoginRegister.class.getResource("/Imagenes/calendario_blanco_24.png")));
		IDateEditor dateEditor = Field_Calendario.getDateEditor();
		JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dateEditor;
		txtFld.addPropertyChangeListener(e -> txtFld.setForeground(Color.WHITE));
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.gridx = 2;
		gbc_calendar.gridy = 5;
		BotPanelRegister.add(Field_Calendario, gbc_calendar);

		JLabel Label_Foto_Usuario = new JLabel("Foto de usuario:");
		GridBagConstraints gbc_Label_Foto_Usuario = new GridBagConstraints();
		gbc_Label_Foto_Usuario.anchor = GridBagConstraints.EAST;
		gbc_Label_Foto_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Foto_Usuario.gridx = 1;
		gbc_Label_Foto_Usuario.gridy = 6;
		BotPanelRegister.add(Label_Foto_Usuario, gbc_Label_Foto_Usuario);

		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Gestor de archivos - Imagen");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.JPG", "jpg");
		fc.setFileFilter(filtro);
		JButton btnNewButton_5 = new JButton("Seleccionar");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = fc.getSelectedFile();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 2;
		gbc_btnNewButton_5.gridy = 6;
		BotPanelRegister.add(btnNewButton_5, gbc_btnNewButton_5);

		JLabel Label_Presentacion = new JLabel("Presentaci\u00F3n:");
		GridBagConstraints gbc_Label_Presentacion = new GridBagConstraints();
		gbc_Label_Presentacion.anchor = GridBagConstraints.EAST;
		gbc_Label_Presentacion.insets = new Insets(0, 0, 5, 5);
		gbc_Label_Presentacion.gridx = 1;
		gbc_Label_Presentacion.gridy = 7;
		BotPanelRegister.add(Label_Presentacion, gbc_Label_Presentacion);

		JButton btnNewButton_4 = new JButton("Escribir");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				muestraTextoPresent();
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 2;
		gbc_btnNewButton_4.gridy = 7;
		BotPanelRegister.add(btnNewButton_4, gbc_btnNewButton_4);

		JButton btnRegistrar = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.anchor = GridBagConstraints.EAST;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 2;
		gbc_btnRegistrar.gridy = 9;
		BotPanelRegister.add(btnRegistrar, gbc_btnRegistrar);

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setIcon(new ImageIcon(LoginRegister.class.getResource("/Imagenes/iniciar-sesion.png")));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) (CardBotPanel.getLayout());
				cl.show(CardBotPanel, "Bot_Panel_Login");
			}

		});
		DerFlowLayout.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.setIcon(new ImageIcon(LoginRegister.class.getResource("/Imagenes/usuario.png")));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) (CardBotPanel.getLayout());
				cl.show(CardBotPanel, "Bot_Panel_Register");
			}
		});
		DerFlowLayout.add(btnNewButton);

		// Manejadores

		btnRegistrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Boolean checkCampos = true;
				// Obtenemos los datos del registro del usuario obligatorios.
				// Faltan las fotos de los usuarios
				String auxEmail = Field_Email.getText().trim();
				String auxNombreCompleto = Field_Nombre_Completo.getText().trim();
				String auxNombreUser = Field_Nombre_Usuario.getText().trim();
				String auxPassword = new String(Field_Contrasena_Register.getPassword()).trim();
				Date auxFechaNacimiento = Field_Calendario.getDate();
				String auxfechaComoCadena = null;
				if (auxFechaNacimiento != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					auxfechaComoCadena = sdf.format(auxFechaNacimiento);
				}
				// Obtenemos los datos del registro del usuario no obligatorios.
				String presentacionPerfil = null;
				presentacionPerfil = textPresent.obtenerPresentacion();
				if (auxEmail.equals("")) {
					checkCampos = false;
					Field_Email.setBackground(new Color(125, 50, 50));
				} else {
					checkCampos = true;
					Field_Email.setBackground(new Color(58, 61, 76));
				}

				if (auxNombreCompleto.equals("")) {
					checkCampos = false;
					Field_Nombre_Completo.setBackground(new Color(125, 50, 50));
				} else {
					checkCampos = true;
					Field_Nombre_Completo.setBackground(new Color(58, 61, 76));
				}

				if (auxNombreUser.equals("")) {
					checkCampos = false;
					Field_Nombre_Usuario.setBackground(new Color(125, 50, 50));
				} else {
					checkCampos = true;
					Field_Nombre_Usuario.setBackground(new Color(58, 61, 76));
				}

				if (auxPassword.equals("")) {
					checkCampos = false;
					Field_Contrasena_Register.setBackground(new Color(125, 50, 50));
				} else {
					checkCampos = true;
					Field_Nombre_Usuario.setBackground(new Color(58, 61, 76));
				}

				if (auxfechaComoCadena == null) {
					checkCampos = false;
					txtFld.addPropertyChangeListener(e -> txtFld.setBackground(new Color(125, 50, 50)));
				} else {
					checkCampos = true;
					txtFld.addPropertyChangeListener(e -> txtFld.setBackground(new Color(58, 61, 76)));
				}

				if (checkCampos == true) {
					// Primero hay que comprobar que el nombre del usuario no esta registrado ya
					if (Controlador.getUnicaInstancia().compruebaUsuarioRegistro(auxNombreUser, auxEmail) != 0) {
						muestraErrorUsuarioYaRegistrado();
					} else {
						String pathFoto;
						if(selectedFile == null) {
							pathFoto = null;
						}
						else {
							pathFoto = selectedFile.getAbsolutePath();
						}
						Controlador.getUnicaInstancia().registrarUsuario(auxEmail, auxNombreCompleto, auxNombreUser,
								auxPassword, auxfechaComoCadena, presentacionPerfil, pathFoto);
						muestraUsuarioRegistradoCorrectamente();

					}
				} else {
					muestraErrorCamposRegistro();
				}

			}
		});
	}

	// M�todos para mostrar las ventanas que son accesibles desde esta ventana

	private void muestraTextoPresent() {
		textPresent.muestra();
	}

	private void muestraHome() throws ParseException, IOException {
		if (home == null)
			home = new Home(this, null, null);

		home.muestra();
	}

	private void muestraErrorCamposRegistro() {
		if (errorCamposRegistro == null)
			errorCamposRegistro = new ErrorCamposRegistro();

		errorCamposRegistro.muestra();
	}

	private void muestraErrorUsuarioYaRegistrado() {
		if (errorUsuarioYaRegistrado == null)
			errorUsuarioYaRegistrado = new ErrorUsuarioYaRegistrado(frmPhototds.getX(), frmPhototds.getY());

		errorUsuarioYaRegistrado.muestra();
	}

	private void muestraUsuarioRegistradoCorrectamente() {
		if (usuarioRegistradoCorrectamente == null)
			usuarioRegistradoCorrectamente = new UsuarioRegistradoCorrectamente(frmPhototds.getX(), frmPhototds.getY());

		usuarioRegistradoCorrectamente.muestra();
	}

}
