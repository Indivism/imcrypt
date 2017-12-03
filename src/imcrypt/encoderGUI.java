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
public class encoderGUI implements ActionListener{
	JFrame encoderUI;
    JFileChooser imageChooser;
    JButton browseImage, showImage;
    JTextField imagePath;
    JPanel buttonPanel;
    BufferedImage image;
    JLabel imageDisplay;
	
	public encoderGUI() {
		JFrame encoderUI = new JFrame ("imcrypt Encoder");
		imageDisplay = new JLabel();
		buttonPanel = new JPanel();
		encoderUI.setBounds(500, 250, 750, 500);
		encoderUI.setPreferredSize(new Dimension(750, 500));
		encoderUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		encoderUI.setLayout(new FlowLayout());
		browseImage = new JButton("Browse");
		browseImage.addActionListener(this);
		showImage = new JButton("Encrypt");
		showImage.addActionListener(this);
		imagePath = new JTextField(20);
		buttonPanel.add(browseImage);
		buttonPanel.add(showImage);
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
		}
	}
	
	public static void main(String args[]) {
		new encoderGUI();
	}

}

	
