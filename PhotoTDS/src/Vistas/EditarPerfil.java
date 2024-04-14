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

import com.toedter.calendar.JDateChooser;

import Controlador.Controlador;
import Modelo.Usuario;

public class EditarPerfil {

	private JFrame frmEditPerfil;
	private JTextField Field_Usuario;
	private JPasswordField Field_Contrasena;
	private JPasswordField Field_Contrasena_Register;
	private JDateChooser FechaNacim;
	
	private File selectedFile = null;

	private TextoPresentacion textPresent;
	private Home frmHome;
	private int codigoUsuario;

	void muestra() {
		frmEditPerfil.setVisible(true);
		frmEditPerfil.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarPerfil window = new EditarPerfil(null,0);
					window.frmEditPerfil.setVisible(true);
					window.frmEditPerfil.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditarPerfil(Home home, int codigo_usuario) {
		frmHome = home;
		this.codigoUsuario = codigo_usuario;
		initialize();
	}

	private void initialize() {
		// Inicializo la ventana para escribir la presentaci�n del perfil del usuario.
				textPresent = new TextoPresentacion(this);

				frmEditPerfil = new JFrame();
				frmEditPerfil.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginRegister.class.getResource("/Imagenes/instagram.png")));
				frmEditPerfil.setTitle("PhotoTDS");
				frmEditPerfil.setBounds(100, 100, 550, 380);
				frmEditPerfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmEditPerfil.getContentPane().setLayout(new BorderLayout(0, 0));

				JPanel TopPanel = new JPanel();
				TopPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				frmEditPerfil.getContentPane().add(TopPanel, BorderLayout.NORTH);
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
				
				JButton btnNewButton = new JButton("Aceptar");
				DerFlowLayout.add(btnNewButton);
				
				btnNewButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						frmEditPerfil.setVisible(false);
						try {
							muestraHome();
						} catch (ParseException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
				JButton btnNewButton_1 = new JButton("Cancelar");
				DerFlowLayout.add(btnNewButton_1);
				
				btnNewButton_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						frmEditPerfil.setVisible(false);
						try {
							muestraHome();
						} catch (ParseException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
				JPanel CardBotPanel = new JPanel();
				frmEditPerfil.getContentPane().add(CardBotPanel, BorderLayout.CENTER);
				CardBotPanel.setLayout(new CardLayout(0, 0));
				
				JPanel BotPanelRegister = new JPanel();
				CardBotPanel.add(BotPanelRegister, "Bot_Panel_Register");
				GridBagLayout gbl_BotPanelRegister = new GridBagLayout();
				gbl_BotPanelRegister.columnWidths = new int[] { 90, 0, 248, 90, 0 };
				gbl_BotPanelRegister.rowHeights = new int[] { 0, 0, 0, 0, 33, 0, 0, 0 };
				gbl_BotPanelRegister.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
				gbl_BotPanelRegister.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
				BotPanelRegister.setLayout(gbl_BotPanelRegister);
				
				JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
				GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
				gbc_lblContrasea.anchor = GridBagConstraints.EAST;
				gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
				gbc_lblContrasea.gridx = 1;
				gbc_lblContrasea.gridy = 2;
				BotPanelRegister.add(lblContrasea, gbc_lblContrasea);
				
				Field_Contrasena_Register = new JPasswordField();
				GridBagConstraints gbc_Field_Contrasena_Register = new GridBagConstraints();
				gbc_Field_Contrasena_Register.insets = new Insets(0, 0, 5, 5);
				gbc_Field_Contrasena_Register.fill = GridBagConstraints.HORIZONTAL;
				gbc_Field_Contrasena_Register.gridx = 2;
				gbc_Field_Contrasena_Register.gridy = 2;
				BotPanelRegister.add(Field_Contrasena_Register, gbc_Field_Contrasena_Register);

				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Gestor de archivos - Imagen");
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.PNG", "png");
				fc.setFileFilter(filtro);
				
				JLabel Label_Foto_Usuario = new JLabel("Foto de usuario:");
				GridBagConstraints gbc_Label_Foto_Usuario = new GridBagConstraints();
				gbc_Label_Foto_Usuario.anchor = GridBagConstraints.EAST;
				gbc_Label_Foto_Usuario.insets = new Insets(0, 0, 5, 5);
				gbc_Label_Foto_Usuario.gridx = 1;
				gbc_Label_Foto_Usuario.gridy = 3;
				BotPanelRegister.add(Label_Foto_Usuario, gbc_Label_Foto_Usuario);
				
				JButton btnNewButton_5 = new JButton("Seleccionar");
				btnNewButton_5.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						fc.showOpenDialog(fc);
						int result = fc.showOpenDialog(fc);

						if (result == JFileChooser.APPROVE_OPTION) {
						    selectedFile = fc.getSelectedFile();
						    System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
						} else if (result == JFileChooser.CANCEL_OPTION) {
						    System.out.println("Seleccion cancelada por el usuario");
						}
					}
				});
				GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
				gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton_5.gridx = 2;
				gbc_btnNewButton_5.gridy = 3;
				BotPanelRegister.add(btnNewButton_5, gbc_btnNewButton_5);
				
				JLabel Label_Presentacion = new JLabel("Presentaci\u00F3n:");
				GridBagConstraints gbc_Label_Presentacion = new GridBagConstraints();
				gbc_Label_Presentacion.anchor = GridBagConstraints.EAST;
				gbc_Label_Presentacion.insets = new Insets(0, 0, 5, 5);
				gbc_Label_Presentacion.gridx = 1;
				gbc_Label_Presentacion.gridy = 4;
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
				gbc_btnNewButton_4.gridy = 4;
				BotPanelRegister.add(btnNewButton_4, gbc_btnNewButton_4);
				
			
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Falta las fotos de los usuarios
						String auxPassword = new String(Field_Contrasena_Register.getPassword()).trim();
						String presentacionPerfil = null;
						presentacionPerfil = textPresent.obtenerPresentacion();
						Controlador.getUnicaInstancia().editarPerfil(getCodigoUsuario(), presentacionPerfil, auxPassword, selectedFile);
					}
				});	
	}

	private void muestraTextoPresent() {
		textPresent.muestra();
	}
	
	private Integer getCodigoUsuario() {
		return this.codigoUsuario;
	}

	private void muestraHome() throws ParseException, IOException {
		if (frmHome == null)
			frmHome = new Home(this, null, null);

		frmHome.muestra();
	}

}
