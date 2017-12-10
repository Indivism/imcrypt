package imcrypt;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;


public class Imcrypt {
	
	public static boolean encode (String path, String original, String extension, String outputName, String message) {
		return true;
	}
	
	public static boolean decode (String path, String name) {
		return true;
	}
	
	private byte[] encodeText(byte[] image, byte[]message, int location) {
		if (message.length + location > image.length) {
			throw new IllegalArgumentException("Message is too long for the image to handle!");
		}
		for (int i = 0; i < message.length; i++) {
			int letterByte = message[i];
			for (int bit = 7; bit >= 0; --bit) {
				int bitValue = (letterByte >>> bit) & 1;
				image[location] = (byte) ((image[location] & 0xFE) | bitValue);
				location++;
			}
		}
		return image;
	}
	
	private byte[] decodeText(byte[] image) {
		int messageLength = 0;
		int bitOffset = 32;
		for (int i = 0; i < bitOffset; i++) {
			messageLength = (messageLength << 1) | (image[i] & 1);
		}
		byte[] decodedMessage = new byte[messageLength];
		for (int i = 0; i < decodedMessage.length; i++) {
			for (int bit = 0; bit <= 7; ++bit) {
				decodedMessage[bit] = (byte) ((decodedMessage[bit] << 1) | (image[bitOffset] & 1));
			}
		}
		return decodedMessage;
	}
	
	
	private BufferedImage createCanvas (BufferedImage image) {
		BufferedImage modified = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graphics = modified.createGraphics();
		graphics.drawRenderedImage(image, null);
		graphics.dispose();
		return modified;
	}
	
	private byte[] convertToBit(int i) {
		return new byte[] {0, 0, 0, (byte) ((i & 0x000000FF))};
	}
	
	private byte[] imageToBytes(BufferedImage image) {
		WritableRaster raster = image.getRaster();
		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
		return buffer.getData();
	}

}
