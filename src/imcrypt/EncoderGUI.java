package imcrypt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
/**
 * imcrypt encoder GUI.
 */
public class EncoderGUI implements ActionListener{
	JFrame encoderUI;
    JFileChooser imageChooser;
    JButton browseImage, encryptImage;
    JTextField imagePath;
    JPanel buttonPanel;
    BufferedImage image;
    JLabel imageDisplay;
	
	public EncoderGUI() {
		JFrame encoderUI = new JFrame ("imcrypt Encoder");
		imageDisplay = new JLabel();
		buttonPanel = new JPanel();
		
		encoderUI.setBounds(500, 250, 750, 500);
		encoderUI.setPreferredSize(new Dimension(750, 500));
		encoderUI.setLayout(new FlowLayout());
		
		browseImage = new JButton("Browse");
		browseImage.addActionListener(this);
		encryptImage = new JButton("Encrypt");
		encryptImage.addActionListener(this);
		imagePath = new JTextField(20);
		buttonPanel.add(browseImage);
		buttonPanel.add(encryptImage);
		buttonPanel.add(imagePath);
		encoderUI.add(buttonPanel);
		encoderUI.add(imageDisplay);
		encoderUI.pack();
		encoderUI.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == browseImage) {
			imageChooser = new JFileChooser();
			imageChooser.showOpenDialog(encoderUI);
			String path = imageChooser.getSelectedFile().getAbsolutePath();
			//imagePath.setText(path);
            imageDisplay.setIcon(new ImageIcon(new ImageIcon(path).getImage()));
		} else if(e.getSource() == encryptImage) {
			
		}
	}
	
	public static void main(String args[]) {
		new EncoderGUI();
	}

}
	
