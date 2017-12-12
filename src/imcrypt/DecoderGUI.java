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
public class DecoderGUI implements ActionListener {
	
	JFrame decoderUI;
	JFileChooser imageChooser;
	JButton browseImage, decryptText, decryptImage;
	JTextField decodedMessage;
	JPanel buttonPanel;
	BufferedImage image;
	JLabel imageDisplay, decodedImageDisplay;
	String path;

	public DecoderGUI() {
		JFrame decoderUI = new JFrame ("imcrypt Decoder");
		imageDisplay = new JLabel();
		decodedImageDisplay = new JLabel();
		buttonPanel = new JPanel();
		
		decoderUI.setBounds(500, 150, 750, 500);
		decoderUI.setPreferredSize(new Dimension(900, 750));
		decoderUI.setLayout(new FlowLayout());
		
		browseImage = new JButton("Browse");
		browseImage.addActionListener(this);
		decryptText = new JButton("Retrieve Text");
		decryptText.addActionListener(this);
		decryptImage = new JButton("Retreive Image");
		decryptImage.addActionListener(this);
		decodedMessage = new JTextField(20);
		buttonPanel.add(browseImage);
		buttonPanel.add(decryptText);
		buttonPanel.add(decryptImage);

		buttonPanel.add(decodedMessage);
		decoderUI.add(buttonPanel);
		decoderUI.add(imageDisplay);
		decoderUI.add(decodedImageDisplay);
		decoderUI.pack();
		decoderUI.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == browseImage) {
			imageChooser = new JFileChooser();
			imageChooser.showOpenDialog(decoderUI);
			try {
				path = imageChooser.getSelectedFile().getAbsolutePath();
				image = ImageIO.read(new File(path));
				imageDisplay.setIcon(new ImageIcon(new ImageIcon(image).getImage()));
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Image could not be read.","Error",JOptionPane.ERROR_MESSAGE);

			}
		} if(e.getSource() == decryptText) {
			decodedMessage.setText(ImcryptText.decode(image));
		} if(e.getSource() == decryptImage) {
			decodedImageDisplay.setIcon(new ImageIcon(ImcryptImage.reveal(image)));
		}
	}
	
	public static void main(String args[]) {
		new DecoderGUI();
	}
}

