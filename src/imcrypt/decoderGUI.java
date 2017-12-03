package imcrypt;

import java.awt.*; 
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.* ;

/**
 * imcrypt decoder GUI.
 */	
public class decoderGUI implements ActionListener{

	public static void main(String args[]) {
		new decoderGUI();
	}

	String path;
	JFrame decoderUI;
	JFileChooser imageChooser;
	JButton browseImage, showImage, decrypt;
	JTextField imagePath;
	JPanel buttonPanel;
	BufferedImage image;
	JLabel label;

	public decoderGUI() {
		JFrame decoderUI = new JFrame ("imcrypt Decoder");
		label = new JLabel();
		buttonPanel = new JPanel();
		decoderUI.setBounds(500, 250, 750, 500);
		decoderUI.setPreferredSize(new Dimension(750, 500));
		decoderUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		decoderUI.setLayout(new FlowLayout());
		browseImage = new JButton("Browse");
		browseImage.addActionListener(this);
		decrypt = new JButton("Decrypt");

		showImage = new JButton("Show Image");
		showImage.addActionListener(this);
		//imagePath = new JTextField(20);
		buttonPanel.add(browseImage);
		buttonPanel.add(showImage);

		//buttonPanel.add(imagePath);
		decoderUI.add(buttonPanel);
		buttonPanel.add(decrypt);
		decoderUI.add(label);
		decoderUI.pack();
		decoderUI.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == browseImage) {
			imageChooser = new JFileChooser();
			imageChooser.showOpenDialog(decoderUI);
			String path = imageChooser.getSelectedFile().getAbsolutePath();
			//imagePath.setText(path);               
			label.setIcon(new ImageIcon(new ImageIcon(path).getImage()));
		} 
	}
}

