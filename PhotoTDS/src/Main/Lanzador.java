package Main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import Vistas.LoginRegister;

public class Lanzador {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme");
					LoginRegister loginRegister = new LoginRegister();
					loginRegister.muestra();
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}
		});
	}
}
