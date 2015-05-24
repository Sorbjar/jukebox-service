package be.lode.jukebox.service.output;

import java.io.File;

import net.glxn.qrgen.javase.QRCode;

/**
 * The Class QR.
 */
public class QR {

	/**
	 * Gets the QR file.
	 *
	 * @param input
	 *            the input
	 * @return the QR file
	 */
	public static File getQRFile(String input) {
		return QRCode.from(input).file();
	}

	/**
	 * Gets the QR file.
	 *
	 * @param input
	 *            the input
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the QR file
	 */
	public static File getQRFile(String input, int width, int height) {
		return QRCode.from(input).withSize(width, height).file();
	}
}
