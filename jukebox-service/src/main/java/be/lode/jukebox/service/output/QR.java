package be.lode.jukebox.service.output;

import java.io.File;

import net.glxn.qrgen.javase.QRCode;

public class QR {

	public static File getQRFile(String input) {
		return QRCode.from(input).file();
	}

	public static File getQRFile(String input, int width, int height) {
		return QRCode.from(input).withSize(width, height).file();
	}
}
