package edu.cnu.ascii.gui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 6022269886585017042L;

	public Main() {
		super("Base64 Encoder / Decoder");
		setLayout(new GridLayout());
		setResizable(false);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				quit();
			}

		});
		
		add(new Panel());
		
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void quit() {
		dispose();
	}

	public static void main(String[] args) {
		new Main();
	}

}
