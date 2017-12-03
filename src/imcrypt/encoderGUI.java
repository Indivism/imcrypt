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
	String path;
	JFrame encoderUI;
    JFileChooser imageChooser;
    JButton browseImage, showImage;
    JTextField imagePath;
    JPanel buttonPanel;
    BufferedImage image;
    JLabel label;
	
	public encoderGUI() {
		JFrame encoderUI = new JFrame ("imcrypt Encoder");
		label = new JLabel();
		buttonPanel = new JPanel();
		encoderUI.setBounds(500, 250, 750, 500);
		encoderUI.setPreferredSize(new Dimension(750, 500));
		encoderUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		encoderUI.setLayout(new FlowLayout());
		browseImage = new JButton("Browse");
		browseImage.addActionListener(this);
		showImage = new JButton("Show Image");
		showImage.addActionListener(this);
		imagePath = new JTextField(20);
		buttonPanel.add(browseImage);
		buttonPanel.add(showImage);
		buttonPanel.add(imagePath);
		encoderUI.add(buttonPanel);
		encoderUI.add(label);
		encoderUI.pack();
		encoderUI.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == browseImage) {
			imageChooser = new JFileChooser();
			imageChooser.showOpenDialog(encoderUI);
			String path = imageChooser.getSelectedFile().getAbsolutePath();
			imagePath.setText(path);
			try {
				image = ImageIO.read(new File(path));
                ImageIcon icon = new ImageIcon(image);
                label.setIcon(icon);
                Dimension imageSize = new Dimension(icon.getIconWidth(),icon.getIconHeight());
                label.setPreferredSize(imageSize);
                label.revalidate();
                label.repaint();
			} catch(IOException error) {}
		}
	}
	
	public static void main(String args[]) {
		new encoderGUI();
	}

}

	
