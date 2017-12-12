package imcrypt;

import java.awt.image.BufferedImage;

/*
 * The imcrypt image encryption engine.
 */

public class ImcryptImage {

	// Encodes the 3 most significant bits of each pixel of the second image into each pixel of the first image.
	private static int encodePixel(int firstRGB, int secondRGB) {
		return firstRGB & 0xFFF8F8F8 | (secondRGB & 0x00E0E0E0) >> 5;
	}

	// Decodes the 3 most significant bits.
	private static int decodePixel(int RGB) {
		return (RGB & 0xFF070707) << 5;
	}

	public static BufferedImage hide (BufferedImage firstImage, BufferedImage secondImage) {
		if (firstImage.getWidth() > secondImage.getWidth() && firstImage.getHeight() > secondImage.getHeight()) {
			BufferedImage hidden = new BufferedImage(firstImage.getWidth(), firstImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < firstImage.getWidth(); x++) {
				for (int y = 0; y < firstImage.getHeight(); y++) {
					if (x < secondImage.getWidth() && y < secondImage.getHeight()) {
						hidden.setRGB(x, y, encodePixel(firstImage.getRGB(x, y), secondImage.getRGB(x, y)));
					}
				}
			}
			return hidden;
		} else {
			BufferedImage hidden = new BufferedImage(firstImage.getWidth(), firstImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < firstImage.getWidth(); x++) {
				for (int y = 0; y < firstImage.getHeight(); y++) {
					hidden.setRGB(x, y, encodePixel(firstImage.getRGB(x, y), secondImage.getRGB(x, y)));
				}
			}
			return hidden;
		}
	}

	public static BufferedImage reveal (BufferedImage firstImage) {
		BufferedImage revealed = new BufferedImage(firstImage.getWidth(), firstImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < firstImage.getWidth(); x++) {
			for (int y = 0; y < firstImage.getHeight(); y++) {
				revealed.setRGB(x, y, decodePixel(firstImage.getRGB(x, y)));
			}
		}
		return revealed;
	}
}
