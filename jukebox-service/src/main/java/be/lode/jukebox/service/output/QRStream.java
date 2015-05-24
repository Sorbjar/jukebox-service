package be.lode.jukebox.service.output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import net.glxn.qrgen.javase.QRCode;

import com.vaadin.server.StreamResource.StreamSource;

/**
 * The Class QRStream.
 */
public class QRStream implements StreamSource {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6231219446683755279L;

	/** The stream. */
	private ByteArrayOutputStream stream;

	/**
	 * Instantiates a new QR stream.
	 *
	 * @param input
	 *            the input
	 */
	public QRStream(String input) {
		stream = QRCode.from(input).stream();
	}

	/**
	 * Instantiates a new QR stream.
	 *
	 * @param input
	 *            the input
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public QRStream(String input, int width, int height) {
		stream = QRCode.from(input).withSize(width, height).stream();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.server.StreamResource.StreamSource#getStream()
	 */
	@Override
	public InputStream getStream() {
		return new ByteArrayInputStream(stream.toByteArray());
	}

}
