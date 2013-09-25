package edu.cnu.ascii.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.cnu.ascii.base.Base64;
import edu.cnu.ascii.resource.Resource;

public class Panel extends JPanel {
	
	private static final long serialVersionUID = -6748043252911023545L;
	
	private JTextField fieldEncode;
	private JTextField fieldDecode;
	
	private JButton buttonEncode;
	private JButton buttonDecode;
	
	public Panel(){
		setLayout(new FlowLayout());
		setUp();
	}
	
	private void setUp(){
		fieldDecode = new JTextField(25);
		fieldEncode = new JTextField(25);
		
		int newHeight = fieldDecode.getPreferredSize().height + 5;
		int newWidth = fieldDecode.getPreferredSize().height;
		
		fieldDecode.setPreferredSize(new Dimension(newWidth, newHeight));
		fieldEncode.setPreferredSize(new Dimension(newWidth, newHeight));
		
		buttonDecode = new JButton("Decode");
		buttonDecode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String words = fieldDecode.getText();
				String binary = Base64.fromBase64(words);
				fieldEncode.setText(binary);
			}
			
		});
		
		buttonEncode = new JButton("Encode");
		buttonEncode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String words = fieldEncode.getText();
				String base = Base64.toBase64(words);
				fieldDecode.setText(base);
			}
			
		});
		
		add(fieldEncode);
		add(buttonEncode);
		add(fieldDecode);
		add(buttonDecode);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Resource.Width, Resource.Height);
	}

}
