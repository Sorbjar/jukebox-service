package be.lode.jukebox.service.output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.server.StreamResource.StreamSource;


/**
 * The Class PDFStream.
 */
public class PDFStream implements StreamSource {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4765669039826902279L;
	
	/** The ByteArrayOutputStream. */
	private ByteArrayOutputStream baos;
	
	/** The document. */
	private Document document;

	/**
	 * Instantiates a new PDF stream.
	 *
	 * @param url the url
	 * @param jukeboxName the jukebox name
	 */
	public PDFStream(URL url, String jukeboxName) {
		baos = new ByteArrayOutputStream();
		document = null;
		try {
			document = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter.getInstance(document, baos);
			document.open();

			Image img = Image.getInstance(url);
			img.setAlignment(Element.ALIGN_CENTER);
			Paragraph explanation = new Paragraph(
					"Scan this QR code to login to your local jukebox: "
							+ jukeboxName);
			explanation.setAlignment(Element.ALIGN_CENTER);
			document.add(explanation);
			document.add(img);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.vaadin.server.StreamResource.StreamSource#getStream()
	 */
	@Override
	public InputStream getStream() {
		return new ByteArrayInputStream(baos.toByteArray());
	}

}
