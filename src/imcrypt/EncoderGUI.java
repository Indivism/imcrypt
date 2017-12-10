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
    String path;
	
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
			try {
				path = imageChooser.getSelectedFile().getAbsolutePath();
				image = ImageIO.read(new File(path));
				imageDisplay.setIcon(new ImageIcon(new ImageIcon(image).getImage()));
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Image could not be read.","Error",JOptionPane.ERROR_MESSAGE);

			}
			System.out.println(path);
		} if(e.getSource() == encryptImage) {
			System.out.println(path);
		}
	}
	
	public static void main(String args[]) {
		new EncoderGUI();
	}

}
	
