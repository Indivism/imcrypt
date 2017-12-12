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
public class EncoderGUI implements ActionListener {
	
	JFrame encoderUI;
    JFileChooser imageChooser;
    JButton browseFirstImage, browseSecondImage, encryptText, encryptImage;
    JTextField encodedMessage;
    JPanel buttonPanel, imagePanel;
    BufferedImage firstImage, secondImage;
    JLabel firstImageDisplay, secondImageDisplay;
    String path;
	
	public EncoderGUI() {
		JFrame encoderUI = new JFrame ("imcrypt Encoder");
		encoderUI.setLayout(new FlowLayout());
		encoderUI.setBounds(500, 150, 750, 500);
		encoderUI.setPreferredSize(new Dimension(900, 750));
		
		firstImageDisplay = new JLabel();
		secondImageDisplay = new JLabel();
		buttonPanel = new JPanel();
		imagePanel = new JPanel();
		
		browseFirstImage = new JButton("Browse First Image");
		browseFirstImage.addActionListener(this);
		browseSecondImage = new JButton("Browse Second Image");
		browseSecondImage.addActionListener(this);
		encryptText = new JButton("Encrypt Text");
		encryptText.addActionListener(this);
		encryptImage = new JButton("Encrypt Image");
		encryptImage.addActionListener(this);
		encodedMessage = new JTextField(50);
		
		buttonPanel.add(browseFirstImage);
		buttonPanel.add(browseSecondImage);
		buttonPanel.add(encryptText);
		buttonPanel.add(encryptImage);
		
		imagePanel.add(firstImageDisplay);
		imagePanel.add(secondImageDisplay);
		
		encoderUI.add(buttonPanel);
		encoderUI.add(encodedMessage);
		encoderUI.add(imagePanel);
		encoderUI.pack();
		encoderUI.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == browseFirstImage) {
			imageChooser = new JFileChooser();
			imageChooser.showOpenDialog(encoderUI);
			try {
				path = imageChooser.getSelectedFile().getAbsolutePath();
				firstImage = ImageIO.read(new File(path));
				firstImageDisplay.setIcon(new ImageIcon(new ImageIcon(firstImage).getImage()));
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Image could not be read.","Error",JOptionPane.ERROR_MESSAGE);

			}
		} if(e.getSource() == browseSecondImage) {
			imageChooser = new JFileChooser();
			imageChooser.showOpenDialog(encoderUI);
			try {
				path = imageChooser.getSelectedFile().getAbsolutePath();
				secondImage = ImageIO.read(new File(path));
				secondImageDisplay.setIcon(new ImageIcon(new ImageIcon(secondImage).getImage()));
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Image could not be read.","Error",JOptionPane.ERROR_MESSAGE);

			}
		} if(e.getSource() == encryptText) {
			BufferedImage encrypted = ImcryptText.encode(firstImage, encodedMessage.getText());
			JFileChooser save = new JFileChooser();
	        save.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
	        save.showSaveDialog(null);
	        try {
	        	ImageIO.write(encrypted, "png", save.getSelectedFile());
	        } catch (IOException ex) {
	        	JOptionPane.showMessageDialog(null, "Image could not be saved.","Error",JOptionPane.ERROR_MESSAGE);
	        }
		} if (e.getSource() == encryptImage) {
			BufferedImage encrypted = ImcryptImage.hide(firstImage, secondImage);
			JFileChooser save = new JFileChooser();
	        save.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
	        save.showSaveDialog(null);
	        try {
	        	ImageIO.write(encrypted, "png", save.getSelectedFile());
	        } catch (IOException ex) {
	        	JOptionPane.showMessageDialog(null, "Image could not be saved.","Error",JOptionPane.ERROR_MESSAGE);
	        }
		}
	}
	
	public static void main(String args[]) {
		new EncoderGUI();
	}

}
	
