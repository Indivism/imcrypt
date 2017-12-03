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

	JFrame decoderUI;
	JFileChooser imageChooser;
	JButton browseImage, decryptImage;
	JTextField imagePath;
	JPanel buttonPanel;
	BufferedImage image;
	JLabel imageDisplay;

	public decoderGUI() {
		JFrame decoderUI = new JFrame ("imcrypt Decoder");
		imageDisplay = new JLabel();
		buttonPanel = new JPanel();
		
		decoderUI.setBounds(500, 250, 750, 500);
		decoderUI.setPreferredSize(new Dimension(750, 500));
		decoderUI.setLayout(new FlowLayout());
		
		browseImage = new JButton("Browse");
		browseImage.addActionListener(this);
		decryptImage = new JButton("Decrypt");
		decryptImage.addActionListener(this);
		imagePath = new JTextField(20);
		buttonPanel.add(browseImage);
		buttonPanel.add(decryptImage);
		buttonPanel.add(imagePath);
		decoderUI.add(buttonPanel);
		decoderUI.add(imageDisplay);
		decoderUI.pack();
		decoderUI.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == browseImage) {
			imageChooser = new JFileChooser();
			imageChooser.showOpenDialog(decoderUI);
			String path = imageChooser.getSelectedFile().getAbsolutePath();
			//imagePath.setText(path);               
			imageDisplay.setIcon(new ImageIcon(new ImageIcon(path).getImage()));
		} 
	}
}

