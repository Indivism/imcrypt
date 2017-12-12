package imcrypt;
import java.awt.image.*;
import javax.swing.*;

/*
 * The imcrypt text encryption engine.
 */

public class ImcryptText {
	
	public static BufferedImage encode (BufferedImage image, String message) {
		byte[] imageArray = imageToBytes(image);
		byte[] messageArray = message.getBytes();
		byte[] messageLength = convertToBits(message.length());
		try {
			encodeText(imageArray, messageLength, 0);
			encodeText(imageArray, messageArray, 32);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image cannot hold message.","Error",JOptionPane.ERROR_MESSAGE);
		}
		return image;
	}
	
	public static String decode (BufferedImage image) {
		byte[] decodedMessage;
		try {
			decodedMessage = decodeText(imageToBytes(image));
			return new String(decodedMessage);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There is no message.", "Error", 
					JOptionPane.ERROR_MESSAGE);
			return "";
		}
	}
	
	private static byte[] encodeText(byte[] image, byte[] message, int location) {
		for (int i = 0; i < message.length; i++) {
			int letterByte = message[i];
			for (int bit = 7; bit >= 0; bit--) {
				int bitValue = (letterByte >>> bit) & 1;
				image[location] = (byte) ((image[location] & 0xFE) | bitValue);
				location++;
			}
		}
		return image;
	}
	
	private static byte[] decodeText(byte[] image) {
		int messageLength = 0;
		int bitOffset = 32;
		for (int i = 24; i < bitOffset; i++) {
			messageLength = (messageLength << 1) | (image[i] & 1);
		}
		byte[] decodedMessage = new byte[messageLength];
		for (int i = 0; i < decodedMessage.length; i++) {
			for (int bit = 0; bit <= 7; bit++) {
				decodedMessage[i] = (byte) ((decodedMessage[i] << 1) | (image[bitOffset] & 1));
				bitOffset++;
			}
		}
		return decodedMessage;
	}
	
	private static byte[] convertToBits(int i) {
		return new byte[] {0, 0, 0, (byte) ((i & 0x000000FF))};
	}
	
	private static byte[] imageToBytes(BufferedImage image) {
		return ((DataBufferByte) (image.getRaster().getDataBuffer())).getData();
	}

}
