package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Controlador.Controlador;
import Modelo.Usuario;

public class Descuentos {

    private JFrame frameDescuentos;
    private Usuario usuario;
    private Home home;
    
    public void muestra() {
    	frameDescuentos.setVisible(true);
    	frameDescuentos.setLocationRelativeTo(null);
	}

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Descuentos window = new Descuentos(null, null);
                    window.frameDescuentos.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Descuentos(Home home, Usuario usuario) {
    	this.home = home;
    	this.usuario = usuario;
        initialize();
    }

    private void initialize() {
    	frameDescuentos = new JFrame();
    	frameDescuentos.setResizable(false);
    	frameDescuentos.setBounds(100, 100, 302, 200);
    	frameDescuentos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frameDescuentos.getContentPane().add(panel, BorderLayout.NORTH);
        
        JLabel lblNewLabel = new JLabel("Descuentos");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        frameDescuentos.getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Cancelar");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frameDescuentos.setVisible(false);
        	}
        });
        panel_1.add(btnNewButton);
        
        JPanel panel_2 = new JPanel();
        frameDescuentos.getContentPane().add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new GridLayout(3, 1, 0, 0));

        
        JPanel panel_3 = new JPanel();
        panel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent e) {
                if (panel_3.isEnabled()) {
                	panel_3.setBackground(new Color(102, 0, 102));  
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (panel_3.isEnabled()) {
                	panel_3.setBackground(UIManager.getColor("Panel.background"));
                }
            }
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.out.println("Ahora eres Premium");
        		frameDescuentos.setVisible(false);
        		Controlador.getUnicaInstancia().serPremium(Controlador.getUnicaInstancia().getCodigoUsuarioActual());
        		home.activarBotonesPremium();
        	}
        });
        panel_2.add(panel_3, BorderLayout.NORTH);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblNewLabel_1 = new JLabel("Descuento por edad");
        panel_3.add(lblNewLabel_1);
        
        JPanel panel_4 = new JPanel();
        panel_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2.add(panel_4, BorderLayout.SOUTH);
        panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblNewLabel_2 = new JLabel("Descuento por número de \"me gusta\" recibidos");
        panel_4.add(lblNewLabel_2);
        
        JPanel panel_5 = new JPanel();
        panel_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2.add(panel_5);
        
        JLabel lblNewLabel_3 = new JLabel("Sin descuento");
        panel_5.add(lblNewLabel_3);
        
        if(!Controlador.getUnicaInstancia().descuentoEdad()) {
        	panel_3.setEnabled(false);
        }
        
        try {
			if(!Controlador.getUnicaInstancia().descuentoMeGustas(Controlador.getUnicaInstancia().getNombreUserUsuarioActual())) {
				panel_4.setEnabled(false);
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        panel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (panel_3.isEnabled()) {
                    panel_3.setBackground(new Color(102, 0, 102)); 
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (panel_3.isEnabled()) {
                    panel_3.setBackground(UIManager.getColor("Panel.background"));
                }
            }
        });

        panel_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (panel_4.isEnabled()) {
                    panel_4.setBackground(new Color(102, 0, 102));  
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (panel_4.isEnabled()) {
                    panel_4.setBackground(UIManager.getColor("Panel.background"));
                }
            }
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.out.println("Ahora eres Premium");
        		frameDescuentos.setVisible(false);
        		Controlador.getUnicaInstancia().serPremium(Controlador.getUnicaInstancia().getCodigoUsuarioActual());
        		home.activarBotonesPremium();
        	}
        });

        panel_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (panel_5.isEnabled()) {
                    panel_5.setBackground(new Color(102, 0, 102));  
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (panel_5.isEnabled()) {
                    panel_5.setBackground(UIManager.getColor("Panel.background"));
                }
            }
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.out.println("Ahora eres Premium");
        		frameDescuentos.setVisible(false);
        		Controlador.getUnicaInstancia().serPremium(Controlador.getUnicaInstancia().getCodigoUsuarioActual());
        		home.activarBotonesPremium();
        	}
        });


    }
}
