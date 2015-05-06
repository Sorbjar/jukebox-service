package be.lode.jukebox.service.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import net.glxn.qrgen.javase.QRCode;

import com.vaadin.server.StreamResource.StreamSource;

public class QRStream implements StreamSource {
	private static final long serialVersionUID = 6231219446683755279L;
	private ByteArrayOutputStream stream;

	public QRStream(String input) {
		stream = QRCode.from(input).withSize(200, 200).stream();
	}

	@Override
	public InputStream getStream() {
		return new ByteArrayInputStream(stream.toByteArray());
	}

}
