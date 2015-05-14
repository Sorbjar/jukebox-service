package be.lode.jukebox.service.output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.server.StreamResource.StreamSource;

public class PDFStream implements StreamSource {
	private static final long serialVersionUID = -4765669039826902279L;
	private ByteArrayOutputStream baos;
	private Document document;

	public PDFStream(URL url) {
		baos = new ByteArrayOutputStream();
		document = null;
		try {
			document = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter.getInstance(document, baos);
			document.open();

			Image img = Image.getInstance(url);
			document.add(img);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	@Override
	public InputStream getStream() {
		return new ByteArrayInputStream(baos.toByteArray());
	}

}
