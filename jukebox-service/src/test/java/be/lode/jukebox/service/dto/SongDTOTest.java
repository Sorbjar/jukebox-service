package be.lode.jukebox.service.dto;

import static org.junit.Assert.assertEquals;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class SongDTOTest {

	@Test
	public void testEquals() {
		EqualsVerifier.forClass(SongDTO.class).usingGetClass()
				.suppress(Warning.NONFINAL_FIELDS).verify();
	}

	@Test
	public void testToString() {
		SongDTO o = new SongDTO();
		String test = " - ";
		assertEquals(test, o.toString());
		test = "artistTestToString";
		o.setArtist(test);
		test = "titleTestToString";
		o.setTitle(test);
		test = "artistTestToString - titleTestToString";
		assertEquals(test, o.toString());
	}

}
